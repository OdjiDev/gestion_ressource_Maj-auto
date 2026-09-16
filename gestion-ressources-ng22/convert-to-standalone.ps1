# convert-to-standalone.ps1
# Ajoute standalone: true à tous les composants

$root = "src\app"
$count = 0

Get-ChildItem -Path $root -Recurse -Filter "*.component.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw

    # Vérifier si standalone: true existe déjà
    if ($content -match "standalone\s*:\s*true") {
        Write-Host "[SKIP] $file (déjà standalone)" -ForegroundColor DarkGray
        return
    }

    # Vérifier si c'est un composant avec @Component
    if ($content -notmatch "@Component\s*\(") {
        Write-Host "[SKIP] $file (pas un composant)" -ForegroundColor DarkGray
        return
    }

    # Ajouter standalone: true après @Component(
    $newContent = $content -replace "(@Component\s*\(\s*\{)", "`$1`n  standalone: true,"

    Set-Content -Path $file -Value $newContent -NoNewline
    Write-Host "[OK] $file" -ForegroundColor Green
    $count++
}

Write-Host ""
Write-Host "Total converti : $count fichiers" -ForegroundColor Cyan
