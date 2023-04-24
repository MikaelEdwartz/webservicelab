FROM maven:3.8.7-eclipse-temurin-19 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:19-jre-jammy
COPY --from=build /app/CipherProvider/target/*.jar /app/lib/provider.jar
COPY --from=build /app/CipherService/target/*.jar /app/lib/service.jar
COPY --from=build /app/Client/target/*.jar /app/client.jar

ENTRYPOINT java --module-path /app:/app/lib/service.jar:/app/lib/provider.jar -m app/client.Main
