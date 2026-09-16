# fix-backslashes.ps1
$root = "src\app"
$count = 0

Write-Host "=== Nettoyage des imports ===" -ForegroundColor Cyan

Get-ChildItem -Path $root -Recurse -Include "*.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $original = $content

    # 1. Remplacer les backslashes dans les imports (répété pour gérer les multi-backslash)
    do {
        $before = $content
        $content = $content -replace "(import[^']*'[^']*)\\\([^']*')", '$1/$2'
        $content = $content -replace '(import[^"]*"[^"]*)\\([^"]*")', '$1/$2'
    } while ($content -ne $before)

    # 2. Nettoyer les chemins cassés : ../@app/ → @app/
    $content = $content -replace "(\.\./)+@app/", "@app/"
    $content = $content -replace "(\.\./)+@core/", "@core/"
    $content = $content -replace "(\.\./)+@features/", "@features/"
    $content = $content -replace "(\.\./)+@shared/", "@shared/"
    $content = $content -replace "(\.\./)+@env/", "@env/"

    # 3. Nettoyer les ./@app/
    $content = $content -replace "\./@app/", "@app/"
    $content = $content -replace "\./@core/", "@core/"

    # 4. Nettoyer les ....@app/ (points collés)
    $content = $content -replace "\.{2,}@app/", "@app/"
    $content = $content -replace "\.{2,}@core/", "@core/"
    $content = $content -replace "\.{2,}@features/", "@features/"
    $content = $content -replace "\.{2,}@env/", "@env/"

    # 5. Cas spécial : @app/ qui pointe vers core depuis core/
    # Si fichier dans core/models/ et import @app/core/models/, remplacer par chemin relatif
    $relativePath = $file.Substring((Resolve-Path $root).Path.Length + 1)
    
    if ($relativePath -like "core\models\*") {
        $content = $content -replace "@app/core/models/", "./"
    }
    if ($relativePath -like "core\services\*") {
        $content = $content -replace "@app/core/services/", "./"
        $content = $content -replace "@app/core/models/", "../models/"
    }
    if ($relativePath -like "core\guards\*" -or $relativePath -like "core\interceptors\*") {
        $content = $content -replace "@app/core/services/", "../services/"
        $content = $content -replace "@app/core/models/", "../models/"
    }

    if ($content -ne $original) {
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "[FIX] $relativePath" -ForegroundColor Green
        $count++
    }
}

Write-Host ""
Write-Host "Total corrigé : $count fichiers" -ForegroundColor Cyan