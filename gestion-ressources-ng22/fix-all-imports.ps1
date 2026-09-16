# fix-all-imports.ps1

$root = "src\app"
$count = 0

Write-Host "=== Réécriture des imports en @app/* ===" -ForegroundColor Cyan

Get-ChildItem -Path $root -Recurse -Include "*.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $original = $content

    # 1. Remplacer les backslashes par des forward slashes
    $content = $content -replace "\\\\", "/"

    # 2. Réécrire les imports core/models
    $content = $content -replace "from\s*'(\.\./)+core/models/", "from '@app/core/models/"
    $content = $content -replace 'from\s*"(\.\./)+core/models/', 'from "@app/core/models/'

    # 3. Réécrire les imports core/services
    $content = $content -replace "from\s*'(\.\./)+core/services/", "from '@app/core/services/"
    $content = $content -replace 'from\s*"(\.\./)+core/services/', 'from "@app/core/services/'

    # 4. Réécrire les imports classes → core/models
    $content = $content -replace "from\s*'(\.\./)*classes/", "from '@app/core/models/"
    $content = $content -replace 'from\s*"(\.\./)*classes/', 'from "@app/core/models/'

    # 5. Réécrire les imports services → core/services
    $content = $content -replace "from\s*'(\.\./)+services/", "from '@app/core/services/"
    $content = $content -replace 'from\s*"(\.\./)+services/', 'from "@app/core/services/'

    # 6. Réécrire src/app/... en @app/...
    $content = $content -replace "from\s*'src/app/", "from '@app/"
    $content = $content -replace 'from\s*"src/app/', 'from "@app/'

    # 7. Réécrire src/environments/... en @env/...
    $content = $content -replace "from\s*'src/environments/", "from '@env/"
    $content = $content -replace 'from\s*"src/environments/', 'from "@env/'

    # 8. Réécrire les imports @angular (rien à faire)

    # 9. Nettoyer les slashs multiples
    $content = $content -replace "(\.\./)+", "@app/"

    # Écrire seulement si modifié
    if ($content -ne $original) {
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "[FIX] $relativePath" -ForegroundColor Green
        $count++
    }
}

Write-Host ""
Write-Host "Total corrigé : $count fichiers" -ForegroundColor Cyan