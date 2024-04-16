FROM openjdk:17-alpine
RUN apk add --no-cache curl
EXPOSE 8082
RUN curl -o app.jar http://admin:25059373@192.168.1.14:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "app.jar"]