FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY marklogic-datahub-5.*.war app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]