FROM maven:eclipse-temurin AS build
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:20-jre-jammy
COPY --from=build /app/CipherProvider/target/*.jar /app/provider.jar
COPY --from=build /app/CipherService/target/*.jar /app/service.jar
COPY --from=build /app/Client/target/*.jar /app/client.jar

ENTRYPOINT java --module-path /app/client.jar:/app/service.jar:/app/provider.jar -m client/se.iths.client.Main
