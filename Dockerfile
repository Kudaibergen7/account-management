# Используем базовый образ OpenJDK для Java
FROM openjdk:17

# Устанавливаем директорию приложения в контейнере
WORKDIR /app

# Копируем JAR файл приложения в директорию контейнера
COPY target/account-management-0.0.1-SNAPSHOT.jar /app/account-management.jar
EXPOSE 8090

# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "account-management.jar"]