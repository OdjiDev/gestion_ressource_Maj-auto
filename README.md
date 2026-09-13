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
git clone https://https://github.com/OdjiDev/gestion_ressource_Maj-auto
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

    Planification des mises à jour
Le système utilise Spring Scheduler pour exécuter les mises à jour pendant les heures creuses :

java
@Configuration
@EnableScheduling
public class UpdateSchedulerConfig {
    
    @Scheduled(cron = "0 0 2 * * ?") // Tous les jours à 2h du matin
    public void installUpdates() {
        updateService.installAvailableUpdates();
    }
}
🛠️ Utilisation
API REST - Endpoints principaux
Méthode	Endpoint	Description
GET	/api/ressources	Liste des ressources
POST	/api/ressources	Créer une ressource
PUT	/api/ressources/{id}	Modifier une ressource
DELETE	/api/ressources/{id}	Supprimer une ressource
GET	/api/updates/check	Vérifier les MAJ
POST	/api/updates/install	Installer les MAJ
GET	/api/updates/history	Historique des MAJ
Exemple d'appel API
bash
# Vérifier les mises à jour disponibles
curl -X GET http://localhost:8080/api/updates/check \
  -H "Authorization: Bearer VOTRE_TOKEN"

# Installer les mises à jour
curl -X POST http://localhost:8080/api/updates/install \
  -H "Authorization: Bearer VOTRE_TOKEN"
📸 Captures d'écran
Dashboard	Gestion des ressources
https://docs/screenshots/dashboard.png	https://docs/screenshots/ressources.png
Système de mise à jour	Journal des MAJ
https://docs/screenshots/updates.png	https://docs/screenshots/logs.png
🧪 Tests
Backend
bash
cd backend
./mvnw test                    # Tous les tests
./mvnw test -Dtest=UserServiceTest  # Un test spécifique
./mvnw verify                  # Tests + intégration
Frontend
bash
cd frontend
ng test                        # Tests unitaires
ng test --code-coverage        # Avec couverture
ng e2e                         # Tests end-to-end
🔒 Sécurité
Authentification JWT

Spring Security 6

Chiffrement des données sensibles

Validation stricte des entrées

Protection CSRF/XSS/SQL Injection

Vérification de signature des paquets de mise à jour

🤝 Contribution
Les contributions sont les bienvenues !

Fork le projet

Créer une branche (git checkout -b feature/amelioration)

Commit (git commit -m 'Ajout d'une fonctionnalité')

Push (git push origin feature/amelioration)

Ouvrir une Pull Request

Voir CONTRIBUTING.md pour plus de détails.

📋 Roadmap
☑ Gestion des ressources de base
☑ Système de mise à jour automatique
☑ Backend Spring Boot 4.1.0
☑ Frontend Angular 19
□ Application mobile (Flutter)
□ Intégration avec les systèmes existants (Scolarité, RH)
□ Intelligence artificielle pour l'optimisation des emplois du temps
🐛 Signaler un bug
Utilisez les Issues GitHub.

📄 Licence
Ce projet est sous licence MIT. Voir LICENSE pour plus d'informations.

👨‍💻 Auteur
Votre Nom

GitHub : @odjidev

Email : Ouzairoudjire67@gmail.com

🙏 Remerciements
Spring Boot - Framework Java

Angular - Framework frontend

PostgreSQL - Base de données

Tous les contributeurs du projet

⭐ Si ce projet vous aide, n'hésitez pas à laisser une étoile ! ⭐

