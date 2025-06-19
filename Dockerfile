FROM eclipse-temurin:24-jre

WORKDIR /app

COPY app.jar app.jar

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "exec java --enable-preview -jar app.jar --server.port=${PORT}"]
