FROM openjdk:17-alpine
RUN apk add --no-cache curl
EXPOSE 8082
RUN curl -o app.jar http://admin:0000@192.168.8.135:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "app.jar"]
