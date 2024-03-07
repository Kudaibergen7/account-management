# Используем базовый образ Maven для сборки проекта
FROM maven:3.8.3-openjdk-17 AS build

# Устанавливаем директорию приложения в контейнере
WORKDIR /app

COPY .mvn/wrapper /app

COPY . .

# Собираем проект с помощью Maven
RUN mvn clean package

# Используем базовый образ OpenJDK для Java для запуска приложения
FROM openjdk:17

# Устанавливаем директорию приложения в контейнере
WORKDIR /app

# Копируем собранный JAR файл из этапа сборки в контейнер
COPY /out/artifacts/account_management_jar/account-management.jar /app/account-management.jar
# Открываем порт 8090
EXPOSE 8090

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "account-management.jar"]

