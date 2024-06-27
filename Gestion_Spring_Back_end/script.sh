#!/bin/bash

# Install ngx-progressbar package
npm install ngx-progressbar --save

# Change directory to script's location
cd "$(dirname "$0")"

# Check Git status
git status

# Add modified files
git add *

# Commit changes with message
git commit -m "Sauvegarde de la version actuelle"

# Pull updates from remote repository
git pull origin main

# Check repository cleanliness
if [ $? -eq 0 ]; then
  echo "Votre dépôt Git est propre."
else
  echo "Votre dépôt Git n'est pas propre. Veuillez exécuter git add et git commit pour enregistrer vos modifications."

  # Add all modified files to Git staging area
  git add *

  # Commit changes with specified message
  git commit -m "Sauvegarde de la version actuelle"
fi

# Display the most recent commit message
git log -1 --pretty=format:"%s"

# List modified files
git status | grep 'modified:'

# Confirm successful commit
echo "Modifications sauvegardées avec succès !"

# Optional Git update checking and pull functionality
# Function to check for Git updates
check_updates() {
  # Execute git status and capture output
  git_status_output=$(git status)

  # Check for keywords indicating updates
  update_keywords=("behind" "diverged" "integrate" "pull")

  # Create a regular expression pattern (case-insensitive)
  pattern="/\b(${update_keywords[*]})\b/i"

  # Check if updates are present
  has_updates=$(grep -q "$pattern" <<< "$git_status_output")

  if [ $has_updates -eq 1 ]; then
    echo "**Une mise à jour est disponible !**"
    echo "**Voulez-vous lancer la mise à jour ?**"
    echo "**Tapez 'oui' pour confirmer ou 'non' pour annuler.**"
    read -p "Votre choix : " choice

    if [ "$choice" = "oui" ]; then
      perform_git_pull
    else
      echo "**Mise à jour annulée.**"
    fi
  else
    echo "**Aucune mise à jour n'est disponible.**"
  fi
}

# Function to perform Git pull
perform_git_pull() {
  # Add modified files
  git_add_output=$(git add * 2>&1)

  # Commit current changes (optional)
  git_commit_output=$(git commit -a -m "Mise à jour Git" 2>&1)

  # Perform Git pull
  git_pull_output=$(git pull origin 2>&1)

  if [ $git_commit_output -eq 0 ] && [ $git_pull_output -eq 0 ]; then
    echo "**Mise à jour Git effectuée avec succès !**"
    echo "**Vous êtes à jour maintenant.**"
  else
    echo "**Échec de la mise à jour Git !**"
    echo "**Erreurs rencontrées :**"
    echo -e "$git_add_output\n$git_commit_output\n$git_pull_output"
  fi
}

# Check for updates on script execution
if [ "$1" = "check" ]; then
  check_updates
elif [ "$1" = "pull" ]; then
  perform_git_pull
else
  echo "**Utilisation :**"
  echo "script.sh check  - Vérifier les mises à jour"
  echo "script.sh pull    - Effectuer la mise à jour"
fi
