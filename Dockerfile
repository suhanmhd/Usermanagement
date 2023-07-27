FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} studentmanagement.jar
ENTRYPOINT ["java","-jar","/studentmanagement.jar"]


EXPOSE 8080