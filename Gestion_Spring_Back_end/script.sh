#!/bin/bash

# Naviguer vers le répertoire du dépôt Git
cd /chemin/vers/votre/repo

# Exécuter la commande git status
git status

# Afficher un message en fonction du statut du dépôt
if [ $? -eq 0 ]; then
  echo "Votre dépôt Git est propre."
else
  echo "Votre dépôt Git n'est pas propre. Veuillez exécuter git add et git commit pour enregistrer vos modifications."

