FROM eclipse-temurin:24-jre

WORKDIR /app

COPY app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "exec java --enable-preview -jar app.jar --server.port=${PORT}"]
