# add-standalone-imports.ps1

$root = "src\app"
$count = 0

Write-Host "=== Ajout des imports Angular standalone ===" -ForegroundColor Cyan

Get-ChildItem -Path $root -Recurse -Filter "*.component.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $original = $content

    # Détecter les besoins
    $needsCommon = $content -match "\*ngIf|\*ngFor|@if|@for|@switch|DatePipe|CurrencyPipe"
    $needsForms = $content -match "ngModel|FormBuilder|FormGroup|ReactiveFormsModule"
    $needsRouter = $content -match "routerLink|router-outlet|RouterModule|ActivatedRoute"

    # Vérifier si déjà importés
    $hasCommon = $content -match "CommonModule"
    $hasForms = $content -match "FormsModule"
    $hasRouter = $content -match "RouterModule"

    # Construire les imports à ajouter
    $importsToAdd = @()

    if ($needsCommon -and -not $hasCommon) {
        $importsToAdd += "import { CommonModule } from '@angular/common';"
    }
    if ($needsForms -and -not $hasForms) {
        $importsToAdd += "import { FormsModule, ReactiveFormsModule } from '@angular/forms';"
    }
    if ($needsRouter -and -not $hasRouter) {
        $importsToAdd += "import { RouterModule } from '@angular/router';"
    }

    if ($importsToAdd.Count -gt 0) {
        # Insérer après l'import de @angular/core
        $importsBlock = $importsToAdd -join "`n"
        $content = $content -replace "(import\s*\{[^}]+\}\s*from\s*'@angular/core';)", "`$1`n$importsBlock"
    }

    # Ajouter dans le tableau imports: [] du @Component
    $importsList = @()
    if ($needsCommon) { $importsList += "CommonModule" }
    if ($needsForms) { $importsList += "FormsModule" ; $importsList += "ReactiveFormsModule" }
    if ($needsRouter) { $importsList += "RouterModule" }

    if ($importsList.Count -gt 0) {
        $importsString = $importsList -join ", "

        # Vérifier si imports: [] existe déjà
        if ($content -match "imports:\s*\[") {
            # Ajouter dans la liste existante
            $content = $content -replace "imports:\s*\[([^\]]*)\]", "imports: [`$1, $importsString]"
        } else {
            # Créer imports: [] après standalone: true
            $content = $content -replace "(standalone:\s*true,)", "`$1`n  imports: [$importsString],"
        }
    }

    if ($content -ne $original) {
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "[IMPORTS] $file" -ForegroundColor Green
        $count++
    }
}

Write-Host ""
Write-Host "Total modifié : $count fichiers" -ForegroundColor Cyan