FROM eclipse-temurin:24-jre
WORKDIR /app
COPY target/elixirline_backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]
