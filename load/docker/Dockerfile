FROM openjdk:17
VOLUME /tmp
EXPOSE 8090
ARG JAR_FILE=load-1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]