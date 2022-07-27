# Java Spring Boot Microservices

Some microservices (Order, Warehouse, Payment, Loyalty) for testing on Kubernetes.
Two options: 
A) - Running locally with containerized mysql.
B) - Starting a kubernetes and running the four microservices and mysql in pods.



## A) - Running locally with containerized mysql.

Install Docker Desktop https://www.docker.com/products/docker-desktop/.  Then, activate Kubernetes on Options Screen.

```bash
cd order-processor\
./gradlew clean
./gradlew build
docker build -t order-processor .   
docker run -p 8090:8090 order-processor:latest



docker network create springboot-mysql-net

docker run -p 3306:3306 --name mysqlContainer -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=myDB-warehouse -d mysql:5.7
docker run --name mysqlContainer --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=myDB-warehouse -d mysql:5.7

cd warehouse\
./gradlew clean
./gradlew build
docker build -t warehouse .   
docker run -p 8091:8091 warehouse:latest

cd payment\
./gradlew clean
./gradlew build
docker build -t payment .   
docker run -p 8092:8092 payment:latest

cd loyalty\
./gradlew clean
./gradlew build
docker build -t loyalty .   
docker run -p 8093:8093 loyalty:latest

```


## B) - Starting a kubernetes and running the four microservices and mysql in pods.

Install Docker Desktop https://www.docker.com/products/docker-desktop/.  Then, activate Kubernetes on Options Screen.

```bash
docker run -d -p 5000:5000 --restart=always --name registry registry:2



cd payment\
kubectl delete deployment,svc payment
./gradlew clean
./gradlew build
docker build -t payment .
docker tag payment localhost:5000/payment
docker push localhost:5000/payment
kubectl apply -f payment\deployment.yml



cd loyalty\
kubectl delete deployment,svc loyalty
./gradlew clean
./gradlew build
docker build -t loyalty .
docker tag loyalty localhost:5000/loyalty
docker push localhost:5000/loyalty
kubectl apply -f loyalty\deployment.yml


cd order-processor\
kubectl delete deployment,svc order-processor
./gradlew clean
./gradlew build
docker build -t order-processor .
docker tag order-processor localhost:5000/order-processor
docker push localhost:5000/order-processor
kubectl apply -f order-processor\deployment.yml


cd warehouse\
kubectl delete deployment,svc warehouse
kubectl delete deployment mysql-warehouse 
kubectl delete service mysqlservice-warehouse
kubectl delete deployment,svc mysql-warehouse
kubectl delete service mysqlservice-warehouse
kubectl apply -f mysql-pv.yaml
kubectl apply -f mysql-deployment.yaml
./gradlew clean
./gradlew build
docker build -t warehouse .
docker tag warehouse localhost:5000/warehouse
docker push localhost:5000/warehouse
kubectl apply -f warehouse\deployment.yml



clear 
kubectl get deploy,pods,svc



```

paulloestevam@gmail.com