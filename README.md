# Eclipse JKube Spring Boot with Postgres Sample

This is a demo Spring Boot project which showcases how you can use Eclipse JKube to deploy a Spring Boot application which depends on a Database. You can use Eclipse JKube resource fragments to deploy the database to Kubernetes and Eclipse Jkube would generate application image and manifests for you.

## How to Build
To compile, you need to have maven and java installed. Then you can build project using this command:
```
mvn clean install
```

## How to Run
In order to run locally, you would need to run postgres database via docker first. You can easily do that with this simple command:
```
docker run -d --name pg13 -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust postgres:13
```
After this modify `application.yml` in `src/main/resources` to point to localhost's postgres instance:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?createDatabaseIfNotExist=true
spring.datasource.username=postgres
spring.datasource.password=demo1
```

After this you can run application using `mvn spring-boot:run` goal.

## Deploying to Kubernetes

In order to deploy to Kubernetes, you would need to have access to Kubernetes instance. If not, you can install some local Kubernetes Cluster like minikube. I used minikube for testing it locally and I followed these steps:
```
# Start minikube
$ minikube start

# To point your shell to minikube's docker-daemon
$ eval $(minikube -p minikube docker-env)

# Run Eclipse JKube build, resource, apply goals
$ mvn package k8s:build k8s:resource k8s:apply
```
