# Banco-Bci ::: Registro Usuario y Generacion Token

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white) 
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![SpringBoot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![JUnit5](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

## Prerequisites

* Strong knowledge in development with Java 17, Spring Framework and SpringBoot bootstrap, including an installed version of the JVM and Maven.

## Clone the source

If you want to skip the creation steps you can clone the finished sample (without memcache backed session):

    $ git clone https://github.com/genesiscastillo/evaluacion-bci.git

---
# Run your Application

* To Tests your application simply run:
```bash
mvn test
```

* To build your application simply run:
```bash
mvn clean package install
```

* Similar to the previous approach, it is possible to assign values to properties from the environment
in your `application.properties` file.

```bash
app.security.jwt.secretKey=${APP_SECURITY_JWT_SECRET_KEY:mySecretKey}
app.security.jwt.expirationTime=${APP_SECURITY_JWT_EXPIRATION_TIME:86400} 
```

* And run your app as follows.

```bash
APP_SECURITY_JWT_SECRET_KEY=secretKey123
APP_SECURITY_JWT_EXPIRATION_TIME=600

java -jar target\app.jar
```

![deploy](./data/img/deploy.png)


That's it. Your application should start up on port **8181.**

## Testing & Evidence

### GET: http://localhost:8181api/user

* Creating a User
![test1](./data/img/postman1.png)

* Conflict in Creation of User
![test2](./data/img/postman2.png)

* Error en request
![test3](./data/img/postman3.png)

### POST: http://localhost:8181api/user?email=abc@gmail.com

* Get User by email OK
![test4](./data/img/postman4.png)

* Get Uset bye email no found
![test5](./data/img/postman5.png)

---
# Ethical Hacking

## check health app 
```bash
http://localhost:8181/actuator/health
```
![health](./data/img/health.png)

## Swagger APIs Specification
```bash
http://localhost:8181/swagger-ui/index.html
```

![openapi](./data/img/openapi.png)

```bash
http://localhost:8181/v3/api-docs
```

![apidoc](./data/img/apidoc.png)

## Check vulnerability dependecies
```bash
mvn org.owasp:dependency-check-maven:7.4.4:check
```
![dependency](./data/img/dependency.png)

## Testing and Coverage Code

![coverage](./data/img/coverage.png)

---


## Versions

    * 1.0.0 : Evaluacion-Bci

## Authors

    * Cesar Castillo : genesiscastillo@hotmail.com