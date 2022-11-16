Some microservices (Order, Warehouse, Payment, Loyalty) for testing on Kubernetes.

https://youtu.be/ULaI4RcBYSk


## pruebas ambiente local

- descargar microservicios con:  git clone https://github.com/paulloestevam/course-microservice01.git
- abrir proyecto order-processor en IntelliJ 
- agregar los otros 3 proyectos con la opcion "Link Gradle Project"

- levantar microservicio "loyalty" de manera local ( task bootRun en la ventana Gradle->tasks->application)
- probar endpoint GET con postman url: http://localhost:8093/api/v1/loyalty 

- levantar microservicio "payment"
- probar endpoint GET con postman url: http://localhost:8092/api/v1/payment

- levantar microservicio "order-processor"
- probar endpoint GET con postman url: http://localhost:8090/api/v1/orderprocessor

- levantar microservicio "warehouse"  (falla por falta de base de datos)



## docker mysql warehouse

- abrir Docker Desktop
- crear container mySQL con el comando: docker run -p 3306:3306 --name mysqlContainer  -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=myDB-warehouse mysql:5.7
- conectar-se a la bd con DBeaver y probar
- apuntar a localhost la url de la base de datos del microservicio warehouse (archivo warehouse\src\main\resources\application.yaml)
- definir password 123456 (archivo warehouse\src\main\resources\application.yaml)

- levantar microservicio "warehouse"
- probar endpoint GET con postman url: http://localhost:8091/api/v1/warehouse



## order-processor integración de todos los microservicios

- apuntar a localhost la url de los microservicios llamados por order-processor, archivos:
	order-processor\src\main\java\com\nttdata\orderprocessor\services\LoyaltyService.java
	order-processor\src\main\java\com\nttdata\orderprocessor\services\PaymentService.java
	order-processor\src\main\java\com\nttdata\orderprocessor\services\WarehouseService.java
- reiniciar microservicio "order-processor"

- probar endpoint POST con postman url: http://localhost:8090/api/v1/orderprocessor/orderFullfillment con body RAW  con tipo JSON:
```
{
    "customerId": "8474",
    "productId": "450"
}
```


## alternativa: ejecutar uno de los microservicios como un contenedor

- parar microservicio "payment"
- abrir terminal y ejecutar los comandos:

	```
	cd C:\Users\paull\course-microservice01\payment\
	./gradlew clean
	./gradlew build
	docker build -t payment .   
	docker run -p 8092:8092 payment:latest
	```

- probar endpoint GET con postman url:  http://localhost:8092/api/v1/payment
- probar endpoint POST con postman url: http://localhost:8090/api/v1/orderprocessor/orderFullfillment con body RAW  con tipo JSON:
```
{
    "customerId": "8474",
    "productId": "450"
}
```



## cluster kubernetes con microservicios en contenedores

- eliminar proyectos del IntelliJ
- eliminar carpeta course-microservice01
- parar todos los containers y eliminar imagenes en el Docker Desktop

- habilitar Kubernetes en Docker Desktop (Options -> Kubernetes -> Enable Kubernetes)
- descargar microservicios con:  git clone https://github.com/paulloestevam/course-microservice01.git
- ejecutar los comandos:
	```
	docker run -d -p 5000:5000 --restart=always --name registry registry:2

	cd C:\Users\paull\course-microservice01\payment\
	kubectl delete deployment,svc payment
	./gradlew clean
	./gradlew build
	docker build -t payment .
	docker tag payment localhost:5000/payment
	docker push localhost:5000/payment
	kubectl apply -f C:\Users\paull\course-microservice01\payment\deployment.yml

	cd C:\Users\paull\course-microservice01\loyalty\
	kubectl delete deployment,svc loyalty
	./gradlew clean
	./gradlew build
	docker build -t loyalty .
	docker tag loyalty localhost:5000/loyalty
	docker push localhost:5000/loyalty
	kubectl apply -f C:\Users\paull\course-microservice01\loyalty\deployment.yml

	cd C:\Users\paull\course-microservice01\order-processor\
	kubectl delete deployment,svc order-processor
	./gradlew clean
	./gradlew build
	docker build -t order-processor .
	docker tag order-processor localhost:5000/order-processor
	docker push localhost:5000/order-processor
	kubectl apply -f C:\Users\paull\course-microservice01\order-processor\deployment.yml

	cd C:\Users\paull\course-microservice01\warehouse\
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
	kubectl apply -f C:\Users\paull\course-microservice01\warehouse\deployment.yml

	kubectl get deploy,pods,svc
	cd
	```

- probar endpoint GET con postman url:  http://localhost:8093/api/v1/loyalty 
- probar endpoint GET con postman url:  http://localhost:8092/api/v1/payment
- probar endpoint GET con postman url:  http://localhost:8090/api/v1/orderprocessor
- probar endpoint GET con postman url:  http://localhost:8091/api/v1/warehouse
- probar endpoint POST con postman url: http://localhost:8090/api/v1/orderprocessor/orderFullfillment con body RAW  con tipo JSON:
```
{
    "customerId": "8474",
    "productId": "450"
}
```


## probando el Load Balancer del kubernetes

- En Postman, clicar en la carpeta de los endpoints y luego boton Run. (Iterations 500 y Delay 10)
- Para ver los logs de los pods abrir el Contenedor en Docker Desktop, o a traves del plugin Docker instalado en Intellij,  o a traves del comando "kubectl logs nombre_del_pod", para obtener el nombre del pod ejecute el comando "kubectl get deploy,pods,svc".

- Aumentar la cantidad de pods del microservicio order-processor con el comando 
```
kubectl scale deployment order-processor --replicas=3
```











## versión anterior


https://youtu.be/OACNzZIeCYg The source code

https://youtu.be/q6VpKagFQqY Running Microservices locally with mysql docker

https://youtu.be/dI4YBjd-pOc Testing the Kubernetes k8s load balancer

https://youtu.be/on5iLimdpsE Installing docker kubernetes and deploying spring boot pods services mysql



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