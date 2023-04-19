FROM amazoncorretto:17-alpine3.17
WORKDIR /home/app
ARG JAR_FILE=build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} /home/app/app.jar
ENTRYPOINT ["java","-jar","/home/app/app.jar"]