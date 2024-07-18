FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/Product-Service-0.0.1-SNAPSHOT.jar product-service.jar
COPY ./src/main/resources/application-dev.properties /app/src/main/resources/application-dev.properties
ENTRYPOINT ["java","-jar","/product-service.jar"]