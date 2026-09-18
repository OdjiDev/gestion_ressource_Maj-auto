# ==============================================================
# Étape 1 : Build Angular
# ==============================================================
FROM node:24-alpine AS build

WORKDIR /app

# Copier les fichiers de dépendances (cache Docker)
COPY package*.json ./

# Installation propre et reproductible
RUN npm ci --no-audit --no-fund

# Copier le code source
COPY . .

# Build de production
RUN npm run build -- --configuration production

# ==============================================================
# Étape 2 : Servir avec Nginx (non-root)
# ==============================================================
FROM nginx:alpine

# Créer utilisateur non-root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Supprimer la config par défaut (avec -f pour éviter l'erreur si absent)
RUN rm -f /etc/nginx/conf.d/default.conf

# Copier le build Angular
COPY --from=build /app/dist/*/browser /usr/share/nginx/html

# Copier la config Nginx personnalisée
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Permissions pour utilisateur non-root
RUN chown -R appuser:appgroup \
        /usr/share/nginx/html \
        /var/cache/nginx \
        /var/log/nginx \
        /etc/nginx/conf.d && \
    touch /var/run/nginx.pid && \
    chown appuser:appgroup /var/run/nginx.pid

USER appuser

#  CORRIGÉ : 8080 au lieu de 80
# Un utilisateur non-root ne peut PAS binder un port < 1024 (réservé à root)
EXPOSE 8080

#  CORRIGÉ : Healthcheck sur 8080
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080 || exit 1

CMD ["nginx", "-g", "daemon off;"]