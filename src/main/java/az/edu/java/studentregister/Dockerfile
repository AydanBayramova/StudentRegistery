# 1. Base image (Java 17 üçün)
FROM eclipse-temurin:17-jdk-alpine

# 2. App qovluğunu yaradıb köçürürük
WORKDIR /app

# 3. Build edilmiş jar faylını konteynerə kopyalayırıq
COPY build/libs/StudentRegister-0.0.1-SNAPSHOT.jar app.jar

# 4. Jar faylını işə salırıq
ENTRYPOINT ["java","-jar","app.jar"]

# 5. Default port (əgər Spring Boot 8080-də işləyirsə)
EXPOSE 8080
