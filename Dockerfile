FROM eclipse-temurin:24-jre

WORKDIR /app

COPY app.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]
