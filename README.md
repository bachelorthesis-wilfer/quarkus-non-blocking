# Quarkus Non Blocking
(also called reactive quarkus)


## Run/ develop code locally

I used docker to run postgres. Here's the setup:

First setup the database:
```
docker run --name non_blocking_quarkus -e POSTGRES_PASSWORD=123 -d -p 5432:5432 postgres
```
then create the database:
```
docker exec -it non_blocking_quarkus psql -U postgres -c "CREATE DATABASE bookdatabase;"
```

depending on whether or not your code automatically creates a table you might need to go into the docker postgres and initialize the table like this:
```
CREATE TABLE IF NOT EXISTS book (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), price INTEGER, isbn INTEGER);
```

Afterwards you can start your application and everything should work. (I use IntelliJ to run it.)

:warning: Remember to stop and remove the used container and volumes, as docker uses quite a lot of data.

show all running containers to get the id : `docker ps -a`  
kill/stop specific docker container: `docker container stop <id>`   
kill docker container: `docker rm <id>`

remove volumes
```
docker volume ls
docker volume prune
```

# Quarkus Non Blocking - setup data
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

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

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/reactive-quarkus-2-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
