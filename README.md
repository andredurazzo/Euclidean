# Amaro Test

This API was developed using the stacks:
  - Java 11
  - Spring Boot
  - Actuator
  - jUnit
  - Gradle
  - MongoDB
  - Docker
  - Swagger


# How to run
First make sure that you have installed the [docker-compose](https://docs.docker.com/compose/gettingstarted/), run the docker-compose build command at the project root:
```sh
$ docker-compose build
```
Then run the containers:
```sh
$ docker-compose up
```
To test, GET the address:
```sh
http://localhost:8080/actuator/health 
```
To access the project documentation, access the url:
```
    http://localhost:8080/swagger-ui.html
```
