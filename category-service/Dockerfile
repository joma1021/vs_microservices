FROM openjdk:15
ARG JAR_FILE=build/libs/category-service.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]