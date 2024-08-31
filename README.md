# Quarkus Non Blocking
(also called reactive quarkus)

## Setup Postgres Database for local development

I used docker to run postgres. Here's the setup:

fill out {path_to_schema} with the correct path to the schema.sql (located under src/main/resources)  
and then run this command:

```
docker run --name postgrestestdb -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=bookdatabase -v {path_to_schema}/schema.sql:/docker-entrypoint-initdb.d/schema.sql -d -p 5432:5432 postgres
```

Afterwards you can start your application and everything should work. 

:warning: Remember to stop and remove the used container and volumes, as docker uses quite a lot of data.

show all running containers to get the id : `docker ps -a`  
kill/stop specific docker container: `docker container stop <id>`   
kill docker container: `docker rm <id>`

remove volumes
```
docker volume ls
docker volume prune
```

# Build and Run the application

I use IntelliJ to run the application locally. 

## Build a jar

(from quarkus setup)
The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Build a native executable with GraalVM

(from quarkus setup)
You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

theres now a runner file in your target folder from where you can execute it. 

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.


