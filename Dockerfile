FROM openjdk:17-jdk-alpine
ENV APP_EXPREG_PASSWORD_USER ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$
WORKDIR /app
COPY target/app.jar /app/app.jar
EXPOSE 8181
ENTRYPOINT ["java","-Duser.timezone=America/Santiago","-jar","/app/app.jar"]