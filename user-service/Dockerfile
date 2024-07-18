FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/User-Service-0.0.1-SNAPSHOT.jar user-service.jar
COPY ./src/main/resources/application-dev.properties /app/src/main/resources/application-dev.properties
ENTRYPOINT ["java","-jar","/user-service.jar"]