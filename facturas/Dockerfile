FROM openjdk:21-ea-24-oracle

WORKDIR /app

COPY target/facturas-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_A81Q3SGRBZPPCK9H /app/oracle_wallet/
EXPOSE 8081


CMD ["java", "-jar", "app.jar"]

