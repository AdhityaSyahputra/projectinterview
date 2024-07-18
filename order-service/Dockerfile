FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/Order-Service-0.0.1-SNAPSHOT.jar order-service.jar
COPY ./src/main/resources/application-dev.properties /app/src/main/resources/application-dev.properties
ENTRYPOINT ["java","-jar","/order-service.jar"]