FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} vgdemo.jar
ENTRYPOINT ["java","-jar","/vgdemo.jar"]
EXPOSE 8080/tcp
