#!/bin/bash

# Naviguer vers le répertoire du dépôt Git
# cd /chemin/vers/votre/repo
 npm install ngx-progressbar --save

# Change the current working directory to the script's directory
cd "$(dirname "$0")"

# Exécuter la commande git status
git status

#List modified files:
# git status | grep 'modified:'

# Display the current working directory
#echo "Répertoire d'exécution actuel : $(pwd)"
git add *
  git commit -m "Sauvegarde de la version actuelle du local"
git pull origin main

# Afficher un message en fonction du statut du dépôt
if [ $? -eq 0 ]; then
  echo "Votre dépôt Git est propre."
else
  # echo "Votre dépôt Git n'est pas propre. Veuillez exécuter git add et git commit pour enregistrer vos modifications."
    echo"Vous n'etes pas a jour"
  # Ajouter tous les fichiers modifiés à la zone d'indexation Git
  git add .

  # Ajouter tous les fichiers modifiés à la zone d'indexation Git
  git add .

  # Exécuter la commande git commit avec le message spécifié
  git commit -m "Sauvegarde de la version actuelle"

#git pull origin main

#Display commit message:
  git log -1 --pretty=format:"%s"

#List modified files:
git status | grep 'modified:'


  # Afficher un message de confirmation
  echo "Modifications sauvegardées avec succès !"
fi






# #!/usr/bin/env bash

# # Function to check for Git updates

#   # Execute git status and capture output
#   git_status_output=$(git status)

#    git add *
#    git commit -m "Sauvegarde de la version actuelle du local"
# # git pull origin main

#   # Check for keywords indicating updates
#   update_keywords=("behind" "diverged" "integrate" "pull")

#   # Create a regular expression pattern (case-insensitive)
#   pattern="/\b(${update_keywords[*]})\b/i"

#   # Check if updates are present
#   has_updates=$(grep -q "$pattern" <<< "$git_status_output")

#   if [ $has_updates -eq 1 ]; then
#     echo "**Une mise à jour est disponible !**"
#     echo "**Voulez-vous lancer la mise à jour ?**"
#   else
#     echo "**Aucune mise à jour n'est disponible.**"
#   fi


# # Function to perform Git pull
# perform_git_pull() {
#   # Add modified files
#   git_add_output=$(git add * 2>&1)

#   # Commit current changes (optional)
#   git_commit_output=$(git commit -a -m "Mise à jour Git" 2>&1)

#   # Perform Git pull
#   git_pull_output=$(git pull origin 2>&1)

#   if [ $git_commit_output -eq 0 ] && [ $git_pull_output -eq 0 ]; then
#     echo "**Mise à jour Git effectuée avec succès !**"
#     echo "**Vous êtes à jour maintenant.**"
#   else
#     echo "**Échec de la mise à jour Git !**"
#     echo "**Erreurs rencontrées :**"
#     echo -e "$git_add_output\n$git_commit_output\n$git_pull_output"
#   fi
# }

# # Check for updates on script execution
# if [ "$1" = "check" ]; then
#   check_updates
# elif [ "$1" = "pull" ]; then
#   perform_git_pull
# else
#   echo "**Utilisation :**"
#   echo "script.sh check  - Vérifier les mises à jour"
#   echo "script.sh pull    - Effectuer la mise à jour"
# fi
