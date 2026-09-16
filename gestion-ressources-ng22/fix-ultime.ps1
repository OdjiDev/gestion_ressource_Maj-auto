# fix-ultime.ps1
$root = "src\app"
$count = 0

Write-Host "=== Nettoyage ULTIME ===" -ForegroundColor Cyan

Get-ChildItem -Path $root -Recurse -Include "*.ts" | ForEach-Object {
    $file = $_.FullName
    $content = Get-Content $file -Raw
    $original = $content

    # 1. Remplacer TOUS les backslashes par forward slashes
    $content = $content.Replace('\', '/')

    # 2. Réécrire les chemins relatifs cassés en @core/ etc
    # Remplacer toute séquence de ../ suivie de @core, @app, etc
    $content = [regex]::Replace($content, "(\.\./)+@core/", "@core/")
    $content = [regex]::Replace($content, "(\.\./)+@app/", "@app/")
    $content = [regex]::Replace($content, "(\.\./)+@features/", "@features/")
    $content = [regex]::Replace($content, "(\.\./)+@shared/", "@shared/")
    $content = [regex]::Replace($content, "(\.\./)+@env/", "@env/")

    # 3. Nettoyer les points collés : ....@core/ → @core/
    $content = [regex]::Replace($content, "\.+@core/", "@core/")
    $content = [regex]::Replace($content, "\.+@app/", "@app/")
    $content = [regex]::Replace($content, "\.+@features/", "@features/")
    $content = [regex]::Replace($content, "\.+@env/", "@env/")

    # 4. Remplacer les chemins relatifs simples par @core/ depuis features/
    $relativePath = $file.Substring((Resolve-Path $root).Path.Length + 1)

    # Pour les fichiers dans features/
    if ($relativePath -like "features/*") {
        $content = [regex]::Replace($content, "from\s*'((\.\./)+)(core/services)/", "from '@`$3/")
        $content = [regex]::Replace($content, "from\s*'((\.\./)+)(core/models)/", "from '@`$3/")
    }

    # 5. Cas des fichiers dans core/ : utiliser chemins relatifs corrects
    if ($relativePath -like "core/models/*") {
        $content = $content -replace "@core/models/", "./"
        $content = $content -replace "@core/services/", "../services/"
    }
    elseif ($relativePath -like "core/services/*") {
        $content = $content -replace "@core/services/", "./"
        $content = $content -replace "@core/models/", "../models/"
    }
    elseif ($relativePath -like "core/guards/*") {
        $content = $content -replace "@core/services/", "../services/"
        $content = $content -replace "@core/models/", "../models/"
    }
    elseif ($relativePath -like "core/interceptors/*") {
        $content = $content -replace "@core/services/", "../services/"
        $content = $content -replace "@core/models/", "../models/"
    }

    # 6. Cas spéciaux pour les guards et interceptors qui pointent vers auth.service
    if ($relativePath -like "core/guards/*" -or $relativePath -like "core/interceptors/*") {
        $content = $content -replace "from '@app/core/services/auth.service'", "from '../services/auth.service'"
    }

    # 7. Nettoyer les / multiples : /// → /
    $content = [regex]::Replace($content, "/{2,}", "/")
    # Sauf pour http:// et https://
    $content = $content -replace "http:/", "http://"
    $content = $content -replace "https:/", "https://"

    if ($content -ne $original) {
        Set-Content -Path $file -Value $content -NoNewline
        Write-Host "[FIX] $relativePath" -ForegroundColor Green
        $count++
    }
}

Write-Host ""
Write-Host "Total corrigé : $count fichiers" -ForegroundColor Cyan