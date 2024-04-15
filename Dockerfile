FROM openjdk:17-alpine
RUN apk add --no-cache curl
EXPOSE 8082
RUN curl -o app.jar http://admin:nexus@192.168.127.132:8081/repository/maven-releases/tn/esprit/DevOps_Project/1.2.3/DevOps_Project-1.2.3.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "app.jar"]
