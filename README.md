# 🎓 Gestion des Ressources Universitaires

> Application full-stack de gestion des ressources universitaires (Spring Boot 4.1.0 + Angular 19) avec système de mise à jour automatique intelligent, planifié aux heures creuses pour ne jamais impacter les utilisateurs.



![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-19-red?logo=angular)
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-blue?logo=typescript)
![License](https://img.shields.io/badge/license-MIT-green)

## 🎯 À propos

Cette application permet aux établissements universitaires de gérer efficacement leurs ressources (salles, équipements, personnel, emplois du temps) tout en garantissant une **disponibilité continue** grâce à un système de mise à jour automatique qui s'exécute pendant les heures de faible activité.

### Le problème résolu

- ❌ Les mises à jour interrompent le travail des utilisateurs
- ❌ Les serveurs sont surchargés aux heures de pointe
- ❌ Gestion manuelle et dispersée des ressources
- ❌ Pas de traçabilité des modifications

### Notre solution

- ✅ Mises à jour automatiques planifiées aux heures creuses
- ✅ Aucune interruption de service
- ✅ Gestion centralisée des ressources
- ✅ Historique complet des modifications
- ✅ Rollback automatique en cas d'échec

## ✨ Fonctionnalités

### 📚 Gestion des ressources
- Gestion des salles et amphithéâtres
- Gestion des équipements (informatique, audiovisuel, etc.)
- Gestion du personnel enseignant et administratif
- Gestion des emplois du temps

### 🔄 Système de mise à jour intelligent
- **Détection automatique** des nouvelles versions sur le serveur de mise à jour
- **Planification intelligente** : exécution pendant les heures creuses (définissables)
- **Mise à jour à chaud** : aucune interruption de service
- **Rollback automatique** en cas d'échec
- **Notifications** aux administrateurs avant/après chaque mise à jour
- **Journal complet** des mises à jour (version, date, statut, durée)

### 📊 Tableau de bord
- Vue temps réel de l'état du système
- Statistiques d'utilisation des ressources
- Alertes et notifications
- Rapports exportables (PDF, Excel)

### 👥 Gestion des utilisateurs
- Rôles et permissions (Admin, Gestionnaire, Utilisateur)
- Authentification sécurisée (JWT)
- Historique des actions par utilisateur

## 🏗️ Architecture
# 🎓 Gestion des Ressources Universitaires

> Application full-stack de gestion des ressources universitaires (Spring Boot 4.1.0 + Angular 19) avec système de mise à jour automatique intelligent, planifié aux heures creuses pour ne jamais impacter les utilisateurs.



![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-brightgreen?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-19-red?logo=angular)
![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-blue?logo=typescript)
![License](https://img.shields.io/badge/license-MIT-green)

## 🎯 À propos

Cette application permet aux établissements universitaires de gérer efficacement leurs ressources (salles, équipements, personnel, emplois du temps) tout en garantissant une **disponibilité continue** grâce à un système de mise à jour automatique qui s'exécute pendant les heures de faible activité.

### Le problème résolu

- ❌ Les mises à jour interrompent le travail des utilisateurs
- ❌ Les serveurs sont surchargés aux heures de pointe
- ❌ Gestion manuelle et dispersée des ressources
- ❌ Pas de traçabilité des modifications

### Notre solution

- ✅ Mises à jour automatiques planifiées aux heures creuses
- ✅ Aucune interruption de service
- ✅ Gestion centralisée des ressources
- ✅ Historique complet des modifications
- ✅ Rollback automatique en cas d'échec

## ✨ Fonctionnalités

### 📚 Gestion des ressources
- Gestion des salles et amphithéâtres
- Gestion des équipements (informatique, audiovisuel, etc.)
- Gestion du personnel enseignant et administratif
- Gestion des emplois du temps

### 🔄 Système de mise à jour intelligent
- **Détection automatique** des nouvelles versions sur le serveur de mise à jour
- **Planification intelligente** : exécution pendant les heures creuses (définissables)
- **Mise à jour à chaud** : aucune interruption de service
- **Rollback automatique** en cas d'échec
- **Notifications** aux administrateurs avant/après chaque mise à jour
- **Journal complet** des mises à jour (version, date, statut, durée)

### 📊 Tableau de bord
- Vue temps réel de l'état du système
- Statistiques d'utilisation des ressources
- Alertes et notifications
- Rapports exportables (PDF, Excel)

### 👥 Gestion des utilisateurs
- Rôles et permissions (Admin, Gestionnaire, Utilisateur)
- Authentification sécurisée (JWT)
- Historique des actions par utilisateur

## 🏗️ Architecture

<img width="877" height="535" alt="image" src="https://github.com/user-attachments/assets/9e4cf9bf-ea8b-486b-bb11-2342c379ead2" />


## 🚀 Installation

### Prérequis

- **Java 21+** ([Télécharger](https://adoptium.net))
- **Node.js 20+** ([Télécharger](https://nodejs.org))
- **Angular CLI 19** (`npm install -g @angular/cli@19`)
- **Maven 3.9+** ou **Gradle 8+**
- **PostgreSQL 16+** ou **MySQL 8+**

### 1️⃣ Backend (Spring Boot)


# Cloner le projet
git clone https://github.com/votre-username/gestion-ressources-universitaires.git
cd gestion-ressources-universitaires/backend

# Configurer la base de données
cp src/main/resources/application.yml.example src/main/resources/application.yml
# Modifier application.yml avec vos identifiants

# Compiler et lancer
./mvnw clean install
./mvnw spring-boot:run
Le backend est accessible sur http://localhost:8080

2️⃣ Frontend (Angular 19)

# Aller dans le dossier frontend
cd ../frontend

# Installer les dépendances
npm install

# Lancer en développement
ng serve

# Ou en production
ng build --configuration production
Le frontend est accessible sur http://localhost:4200

⚙️ Configuration du système de mise à jour
Fichier application.yml
yaml
app:
  update:
    # Serveur de mise à jour
    server-url: https://updates.universite.com
    api-key: ${UPDATE_SERVER_API_KEY}
    
    # Heures creuses (format 24h)
    window:
      start: "02:00"
      end: "05:00"
    
    # Comportement
    auto-check: true
    auto-install: true
    rollback-on-failure: true
    
    # Notifications
    notify-email: admin@universite.com
