#!/bin/bash
set -e

BASE="src/main/java/com/odji/spring_back_end"

echo "=========================================="
echo "1. Sauvegarde git"
echo "=========================================="
git add . || true
git commit -m "wip: backend avant refacto feature-based" || true
git tag v1.0-backend-avant-refacto || true

echo "=========================================="
echo "2. Création arborescence"
echo "=========================================="
mkdir -p $BASE/common/{exception,audit,dto,utils}
for d in auth produit categorie user facture avarie affectation demande societe fournisseur option dashboard signalement git; do
  mkdir -p $BASE/$d/{controller,service,mapper,dto,entity,repository}
done

echo "=========================================="
echo "3. Déplacement common/"
echo "=========================================="
for f in ApiError BusinessException DuplicateResourceException GlobalExceptionHandler ResourceNotFoundException; do
  [ -f "$BASE/exception/$f.java" ] && git mv "$BASE/exception/$f.java" "$BASE/common/exception/"
done
rmdir $BASE/exception 2>/dev/null || true

[ -f "$BASE/model/audit/AuditableEntity.java" ] && git mv "$BASE/model/audit/AuditableEntity.java" "$BASE/common/audit/"
rmdir $BASE/model/audit 2>/dev/null || true

echo "=========================================="
echo "4. Déplacement fichiers par domaine"
echo "=========================================="

move() {
  local src="$BASE/$1/$2.java"
  local dst="$BASE/$3/$4/$2.java"
  if [ -f "$src" ]; then
    git mv "$src" "$dst"
  fi
}

# AUTH
move controller AuthController auth controller

# USER (user + personel + employee + role)
move controller UserController user controller
move controller PersonelController user controller
move controller employeeController user controller
move service UserService user service
move service PersonelService user service
move mapper PersonelMapper user mapper
move dto UserDto user dto
move dto PersonelDto user dto
move model User user entity
move model Personel user entity
move model Role user entity
move repository UserRepository user repository
move repository PersonelRepository user repository
move repository EmployeeRepository user repository

# PRODUIT
move controller ProduitController produit controller
move service ProduitService produit service
move mapper ProduitMapper produit mapper
move dto ProduitDto produit dto
move model Produit produit entity
move repository ProduitRepository produit repository

# CATEGORIE
move controller CategorieController categorie controller
move service CategorieService categorie service
move mapper CategorieMapper categorie mapper
move dto CategorieDto categorie dto
move model Categorie categorie entity
move repository CategorieRepository categorie repository

# FACTURE (4 entités)
move controller FactureController facture controller
move controller LigneFactureController facture controller
move controller FactureReparerController facture controller
move controller LigneFactureReparerController facture controller
move service FactureService facture service
move service LigneFactureService facture service
move service FactureReparerService facture service
move service LigneFactureReparerService facture service
move mapper FactureMapper facture mapper
move mapper LigneFactureMapper facture mapper
move mapper FactureReparerMapper facture mapper
move mapper LigneFactureReparerMapper facture mapper
move dto FactureDto facture dto
move dto LigneFactureDto facture dto
move dto FactureReparerDto facture dto
move dto LigneFactureReparerDto facture dto
move model Facture facture entity
move model LigneFacture facture entity
move model FactureReparer facture entity
move model LigneFactureReparer facture entity
move repository FactureRepository facture repository
move repository LigneFactureRepository facture repository
move repository FactureReparerRepository facture repository
move repository LigneFactureReparerRepository facture repository

# AVARIE (avarie + reparer + lignereparation)
move controller AvarieController avarie controller
move controller ReparerController avarie controller
move controller LigneReparationController avarie controller
move service AvarieService avarie service
move service ReparerService avarie service
move service LigneReparationService avarie service
move mapper AvarieMapper avarie mapper
move mapper ReparerMapper avarie mapper
move mapper LigneReparationMapper avarie mapper
move dto AvarieDto avarie dto
move dto ReparerDto avarie dto
move dto LigneReparationDto avarie dto
move model Avarie avarie entity
move model Reparer avarie entity
move model LigneReparation avarie entity
move repository AvarieRepository avarie repository
move repository ReparerRepository avarie repository
move repository LigneReparationRepository avarie repository

# AFFECTATION (affectation + bureau + departement + magasin)
move controller AffectationController affectation controller
move controller BureauController affectation controller
move controller DepartementController affectation controller
move controller MagasinController affectation controller
move service AffectationService affectation service
move service BureauService affectation service
move service DepartementService affectation service
move service MagasinService affectation service
move mapper AffectationMapper affectation mapper
move mapper BureauMapper affectation mapper
move mapper DepartementMapper affectation mapper
move mapper MagasinMapper affectation mapper
move dto AffectationDto affectation dto
move dto BureauDto affectation dto
move dto DepartementDto affectation dto
move dto MagasinDto affectation dto
move model Affectation affectation entity
move model Bureau affectation entity
move model Departement affectation entity
move model Magasin affectation entity
move repository AffectationRepository affectation repository
move repository BureauRepository affectation repository
move repository DepartementRepository affectation repository
move repository MagasinRepository affectation repository

# DEMANDE
move controller DemandeController demande controller
move controller LigneDemandeController demande controller
move service DemandeService demande service
move service LigneDemandeService demande service
move mapper DemandeMapper demande mapper
move mapper LigneDemandeMapper demande mapper
move dto DemandeDto demande dto
move dto LigneDemandeDto demande dto
move model Demande demande entity
move model LigneDemande demande entity
move repository DemandeRepository demande repository
move repository LigneDemandeRepository demande repository

# SOCIETE (societe + contrat)
move controller SocieteController societe controller
move controller ContratController societe controller
move service SocieteService societe service
move service ContratService societe service
move mapper SocieteMapper societe mapper
move mapper ContratMapper societe mapper
move dto SocieteDto societe dto
move dto ContratDto societe dto
move model Societe societe entity
move model Contrat societe entity
move repository SocieteRepository societe repository
move repository ContratRepository societe repository

# FOURNISSEUR
move controller FournisseurController fournisseur controller
move service FournisseurService fournisseur service
move mapper FournisseurMapper fournisseur mapper
move dto FournisseurDto fournisseur dto
move model Fournisseur fournisseur entity
move repository FournisseurRepository fournisseur repository

# OPTION
move controller OptionController option controller
move service OptionService option service
move mapper OptionMapper option mapper
move dto OptionDto option dto
move model Option option entity
move repository OptionRepository option repository

# DASHBOARD
move controller DashboardController dashboard controller
move dto DashboardStats dashboard dto

# SIGNALEMENT
move controller SignalerController signalement controller
move service SignalerService signalement service
move mapper SignalerMapper signalement mapper
move dto SignalerDto signalement dto
move model Signaler signalement entity
move repository SignalerRepository signalement repository
move repository SignaleRepository signalement repository

# GIT
[ -f "$BASE/GitUpdate/GitUpdateManagerApplication.java" ] && git mv "$BASE/GitUpdate/GitUpdateManagerApplication.java" "$BASE/git/" && rmdir $BASE/GitUpdate 2>/dev/null || true
[ -f "$BASE/model/GitCommandResult.java" ] && git mv "$BASE/model/GitCommandResult.java" "$BASE/git/"
[ -f "$BASE/model/GitUpdateChecker.java" ] && git mv "$BASE/model/GitUpdateChecker.java" "$BASE/git/"
[ -f "$BASE/controller/GitStatusController.java" ] && git mv "$BASE/controller/GitStatusController.java" "$BASE/git/controller/"
[ -f "$BASE/controller/CommandController.java" ] && git mv "$BASE/controller/CommandController.java" "$BASE/git/controller/"
[ -f "$BASE/service/CommandService.java" ] && git mv "$BASE/service/CommandService.java" "$BASE/git/service/"
[ -f "$BASE/service/UpdateService.java" ] && git mv "$BASE/service/UpdateService.java" "$BASE/git/service/"
[ -f "$BASE/repository/UpdateStatusRepository.java" ] && git mv "$BASE/repository/UpdateStatusRepository.java" "$BASE/git/repository/"

# Nettoyer dossiers vides
for d in controller service mapper dto model repository; do
  rmdir "$BASE/$d" 2>/dev/null || true
done

echo "=========================================="
echo "5. Auto-fix packages"
echo "=========================================="
find $BASE -name "*.java" | while read f; do
  rel=${f#$BASE/}
  dir=$(dirname "$rel" | tr '/' '.')
  if [ "$dir" = "." ]; then
    pkg="com.odji.spring_back_end"
  else
    pkg="com.odji.spring_back_end.$dir"
  fi
  sed -i "s|^package .*;|package $pkg;|" "$f"
done

echo "=========================================="
echo "6. Correction des imports (FQN)"
echo "=========================================="

fix_pkg() {
  local old=$1
  local new=$2
  grep -rl "$old" $BASE --include="*.java" 2>/dev/null | xargs -r sed -i "s|$old|$new|g" || true
}

# Auth
fix_pkg "com.odji.spring_back_end.controller.AuthController" "com.odji.spring_back_end.auth.controller.AuthController"
fix_pkg "com.odji.spring_back_end.dto.auth" "com.odji.spring_back_end.auth.dto"

# User
fix_pkg "com.odji.spring_back_end.controller.UserController" "com.odji.spring_back_end.user.controller.UserController"
fix_pkg "com.odji.spring_back_end.controller.PersonelController" "com.odji.spring_back_end.user.controller.PersonelController"
fix_pkg "com.odji.spring_back_end.controller.employeeController" "com.odji.spring_back_end.user.controller.employeeController"
fix_pkg "com.odji.spring_back_end.service.UserService" "com.odji.spring_back_end.user.service.UserService"
fix_pkg "com.odji.spring_back_end.service.PersonelService" "com.odji.spring_back_end.user.service.PersonelService"
fix_pkg "com.odji.spring_back_end.mapper.PersonelMapper" "com.odji.spring_back_end.user.mapper.PersonelMapper"
fix_pkg "com.odji.spring_back_end.dto.UserDto" "com.odji.spring_back_end.user.dto.UserDto"
fix_pkg "com.odji.spring_back_end.dto.PersonelDto" "com.odji.spring_back_end.user.dto.PersonelDto"
fix_pkg "com.odji.spring_back_end.model.User" "com.odji.spring_back_end.user.entity.User"
fix_pkg "com.odji.spring_back_end.model.Personel" "com.odji.spring_back_end.user.entity.Personel"
fix_pkg "com.odji.spring_back_end.model.Role" "com.odji.spring_back_end.user.entity.Role"
fix_pkg "com.odji.spring_back_end.repository.UserRepository" "com.odji.spring_back_end.user.repository.UserRepository"
fix_pkg "com.odji.spring_back_end.repository.PersonelRepository" "com.odji.spring_back_end.user.repository.PersonelRepository"
fix_pkg "com.odji.spring_back_end.repository.EmployeeRepository" "com.odji.spring_back_end.user.repository.EmployeeRepository"

# Produit
fix_pkg "com.odji.spring_back_end.controller.ProduitController" "com.odji.spring_back_end.produit.controller.ProduitController"
fix_pkg "com.odji.spring_back_end.service.ProduitService" "com.odji.spring_back_end.produit.service.ProduitService"
fix_pkg "com.odji.spring_back_end.mapper.ProduitMapper" "com.odji.spring_back_end.produit.mapper.ProduitMapper"
fix_pkg "com.odji.spring_back_end.dto.ProduitDto" "com.odji.spring_back_end.produit.dto.ProduitDto"
fix_pkg "com.odji.spring_back_end.model.Produit" "com.odji.spring_back_end.produit.entity.Produit"
fix_pkg "com.odji.spring_back_end.repository.ProduitRepository" "com.odji.spring_back_end.produit.repository.ProduitRepository"

# Categorie
fix_pkg "com.odji.spring_back_end.controller.CategorieController" "com.odji.spring_back_end.categorie.controller.CategorieController"
fix_pkg "com.odji.spring_back_end.service.CategorieService" "com.odji.spring_back_end.categorie.service.CategorieService"
fix_pkg "com.odji.spring_back_end.mapper.CategorieMapper" "com.odji.spring_back_end.categorie.mapper.CategorieMapper"
fix_pkg "com.odji.spring_back_end.dto.CategorieDto" "com.odji.spring_back_end.categorie.dto.CategorieDto"
fix_pkg "com.odji.spring_back_end.model.Categorie" "com.odji.spring_back_end.categorie.entity.Categorie"
fix_pkg "com.odji.spring_back_end.repository.CategorieRepository" "com.odji.spring_back_end.categorie.repository.CategorieRepository"

# Facture (4 entités)
for cls in Facture LigneFacture FactureReparer LigneFactureReparer; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.facture.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.facture.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.facture.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.facture.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.facture.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.facture.repository.${cls}Repository"
done

# Avarie
for cls in Avarie Reparer LigneReparation; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.avarie.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.avarie.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.avarie.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.avarie.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.avarie.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.avarie.repository.${cls}Repository"
done

# Affectation
for cls in Affectation Bureau Departement Magasin; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.affectation.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.affectation.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.affectation.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.affectation.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.affectation.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.affectation.repository.${cls}Repository"
done

# Demande
for cls in Demande LigneDemande; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.demande.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.demande.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.demande.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.demande.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.demande.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.demande.repository.${cls}Repository"
done

# Societe
for cls in Societe Contrat; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.societe.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.societe.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.societe.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.societe.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.societe.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.societe.repository.${cls}Repository"
done

# Fournisseur
fix_pkg "com.odji.spring_back_end.controller.FournisseurController" "com.odji.spring_back_end.fournisseur.controller.FournisseurController"
fix_pkg "com.odji.spring_back_end.service.FournisseurService" "com.odji.spring_back_end.fournisseur.service.FournisseurService"
fix_pkg "com.odji.spring_back_end.mapper.FournisseurMapper" "com.odji.spring_back_end.fournisseur.mapper.FournisseurMapper"
fix_pkg "com.odji.spring_back_end.dto.FournisseurDto" "com.odji.spring_back_end.fournisseur.dto.FournisseurDto"
fix_pkg "com.odji.spring_back_end.model.Fournisseur" "com.odji.spring_back_end.fournisseur.entity.Fournisseur"
fix_pkg "com.odji.spring_back_end.repository.FournisseurRepository" "com.odji.spring_back_end.fournisseur.repository.FournisseurRepository"

# Option
fix_pkg "com.odji.spring_back_end.controller.OptionController" "com.odji.spring_back_end.option.controller.OptionController"
fix_pkg "com.odji.spring_back_end.service.OptionService" "com.odji.spring_back_end.option.service.OptionService"
fix_pkg "com.odji.spring_back_end.mapper.OptionMapper" "com.odji.spring_back_end.option.mapper.OptionMapper"
fix_pkg "com.odji.spring_back_end.dto.OptionDto" "com.odji.spring_back_end.option.dto.OptionDto"
fix_pkg "com.odji.spring_back_end.model.Option" "com.odji.spring_back_end.option.entity.Option"
fix_pkg "com.odji.spring_back_end.repository.OptionRepository" "com.odji.spring_back_end.option.repository.OptionRepository"

# Dashboard
fix_pkg "com.odji.spring_back_end.controller.DashboardController" "com.odji.spring_back_end.dashboard.controller.DashboardController"
fix_pkg "com.odji.spring_back_end.dto.DashboardStats" "com.odji.spring_back_end.dashboard.dto.DashboardStats"

# Signalement
for cls in Signaler Signale; do
  fix_pkg "com.odji.spring_back_end.controller.${cls}Controller" "com.odji.spring_back_end.signalement.controller.${cls}Controller"
  fix_pkg "com.odji.spring_back_end.service.${cls}Service" "com.odji.spring_back_end.signalement.service.${cls}Service"
  fix_pkg "com.odji.spring_back_end.mapper.${cls}Mapper" "com.odji.spring_back_end.signalement.mapper.${cls}Mapper"
  fix_pkg "com.odji.spring_back_end.dto.${cls}Dto" "com.odji.spring_back_end.signalement.dto.${cls}Dto"
  fix_pkg "com.odji.spring_back_end.model.${cls}" "com.odji.spring_back_end.signalement.entity.${cls}"
  fix_pkg "com.odji.spring_back_end.repository.${cls}Repository" "com.odji.spring_back_end.signalement.repository.${cls}Repository"
done

# Git
fix_pkg "com.odji.spring_back_end.controller.GitStatusController" "com.odji.spring_back_end.git.controller.GitStatusController"
fix_pkg "com.odji.spring_back_end.controller.CommandController" "com.odji.spring_back_end.git.controller.CommandController"
fix_pkg "com.odji.spring_back_end.service.CommandService" "com.odji.spring_back_end.git.service.CommandService"
fix_pkg "com.odji.spring_back_end.service.UpdateService" "com.odji.spring_back_end.git.service.UpdateService"
fix_pkg "com.odji.spring_back_end.model.GitCommandResult" "com.odji.spring_back_end.git.GitCommandResult"
fix_pkg "com.odji.spring_back_end.model.GitUpdateChecker" "com.odji.spring_back_end.git.GitUpdateChecker"
fix_pkg "com.odji.spring_back_end.repository.UpdateStatusRepository" "com.odji.spring_back_end.git.repository.UpdateStatusRepository"

# Exception (vers common/exception)
fix_pkg "com.odji.spring_back_end.exception.ApiError" "com.odji.spring_back_end.common.exception.ApiError"
fix_pkg "com.odji.spring_back_end.exception.BusinessException" "com.odji.spring_back_end.common.exception.BusinessException"
fix_pkg "com.odji.spring_back_end.exception.DuplicateResourceException" "com.odji.spring_back_end.common.exception.DuplicateResourceException"
fix_pkg "com.odji.spring_back_end.exception.GlobalExceptionHandler" "com.odji.spring_back_end.common.exception.GlobalExceptionHandler"
fix_pkg "com.odji.spring_back_end.exception.ResourceNotFoundException" "com.odji.spring_back_end.common.exception.ResourceNotFoundException"

# Audit
fix_pkg "com.odji.spring_back_end.model.audit.AuditableEntity" "com.odji.spring_back_end.common.audit.AuditableEntity"

echo "=========================================="
echo "7. Build"
echo "=========================================="
./mvnw clean install -DskipTests

echo "=========================================="
echo "✅ Migration terminée"
echo "=========================================="