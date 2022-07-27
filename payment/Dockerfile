FROM openjdk:17
WORKDIR /app
COPY build/libs/payment-0.0.1-SNAPSHOT.jar /app/payment.jar
ENTRYPOINT ["java", "-jar", "payment.jar"]