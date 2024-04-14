FROM openjdk:17-alpine
RUN apk add --no-cache curl
EXPOSE 8082
RUN curl -o app.jar http://admin:nexus@192.168.33.10:8081/#browse/browse:maven-releases:tn%2Fesprit%2FDevOps_Project%2F2.1%2FDevOps_Project-2.1.jar
WORKDIR /
ENTRYPOINT ["java", "-jar", "app.jar"]
