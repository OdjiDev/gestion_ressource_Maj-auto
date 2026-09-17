# ==============================================================
# Étape 1 : Build Angular
# ==============================================================
FROM node:24-alpine AS build

WORKDIR /app

# Copier les fichiers de dépendances en premier (cache Docker)
COPY package*.json ./

# Installer les dépendances
# npm ci = installation propre et reproductible (recommandé si package-lock.json existe)
RUN npm ci

# Copier le reste du code source
COPY . .

# Build de production Angular
RUN npm run build -- --configuration production

# ==============================================================
# Étape 2 : Servir les fichiers statiques avec Nginx
# ==============================================================
FROM nginx:alpine

# Copier le build Angular
COPY --from=build /app/dist/*/browser /usr/share/nginx/html

# Copier la configuration Nginx personnalisée
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]