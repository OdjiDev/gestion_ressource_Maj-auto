# fix-imports.ps1

$root = "src\app"
$count = 0

Write-Host "=== Correction des imports ===" -ForegroundColor Cyan

Get-ChildItem -Path $root -Recurse -Include "*.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $original = $content

    # Calculer la profondeur relative à src/app
    $relativePath = $file.Substring((Resolve-Path $root).Path.Length + 1)
    $depth = ($relativePath -split '\\').Count - 1
    $prefix = ("..\" * $depth).TrimEnd('\')

    # Correction des imports src/app/classes/ → core/models/
    $content = $content -replace "from\s*'src/app/classes/", "from '$prefix/core/models/"
    $content = $content -replace 'from\s*"src/app/classes/', "from `"$prefix/core/models/"

    # Correction des imports src/app/services/ → core/services/
    $content = $content -replace "from\s*'src/app/services/", "from '$prefix/core/services/"
    $content = $content -replace 'from\s*"src/app/services/', "from `"$prefix/core/services/"

    # Correction des imports relatifs ../classes/ → core/models/
    $content = $content -replace "from\s*'(\.\./)+classes/", "from '$prefix/core/models/"
    $content = $content -replace 'from\s*"(\.\./)+classes/', "from `"$prefix/core/models/"

    # Correction des imports relatifs ../services/ → core/services/
    $content = $content -replace "from\s*'(\.\./)+services/", "from '$prefix/core/services/"
    $content = $content -replace 'from\s*"(\.\./)+services/', "from `"$prefix/core/services/"

    # Écrire seulement si modifié
    if ($content -ne $original) {
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "[FIX] $relativePath" -ForegroundColor Green
        $count++
    }
}

Write-Host ""
Write-Host "Total corrigé : $count fichiers" -ForegroundColor Cyan