FROM openjdk:17
WORKDIR /app
COPY build/libs/order-processor-0.0.1-SNAPSHOT.jar /app/order-processor.jar
ENTRYPOINT ["java", "-jar", "order-processor.jar"]