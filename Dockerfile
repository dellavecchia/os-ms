FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8000
ARG JAR_FILE=jar_image/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
