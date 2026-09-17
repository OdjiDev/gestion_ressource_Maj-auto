# Étape 1 : Build Angular
FROM node:24-alpine AS build
WORKDIR /app
COPY package*.json ./
# npm 11 est inclus dans l'image node:24-alpine
RUN npm ci
COPY . .
RUN npm run build -- --configuration production

# Étape 2 : Servir avec Nginx
FROM nginx:alpine
COPY --from=build /app/dist/*/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]