FROM openjdk:8-jdk-alpine
MAINTAINER Lucas Augusto <nortthon@gmail.com>
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]