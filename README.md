# Java Spring Boot Microservices

Some microservices (Order, Warehouse, Payment, Loyalty) for testing on Kubernetes.

## Installation

Install Docker Desktop https://www.docker.com/products/docker-desktop/.  Then, activate Kubernetes on Options Screen.

```bash
cd E:\Downloads\order-processor\
./gradlew clean
./gradlew build
docker build -t order-processor .   
docker run -p 8090:8090 order-processor:latest



docker network create springboot-mysql-net

docker run -p 3306:3306 --name mysqlContainer -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=myDB-warehouse -d mysql:5.7
docker run --name mysqlContainer --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=myDB-warehouse -d mysql:5.7

cd E:\Downloads\warehouse\
./gradlew clean
./gradlew build
docker build -t warehouse .   
docker run -p 8091:8091 warehouse:latest

cd E:\Downloads\payment\
./gradlew clean
./gradlew build
docker build -t payment .   
docker run -p 8092:8092 payment:latest

cd E:\Downloads\loyalty\
./gradlew clean
./gradlew build
docker build -t loyalty .   
docker run -p 8093:8093 loyalty:latest

```