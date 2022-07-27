FROM openjdk:17
WORKDIR /app
COPY build/libs/warehouse-0.0.1-SNAPSHOT.jar /app/warehouse.jar
ENTRYPOINT ["java", "-jar", "warehouse.jar"]