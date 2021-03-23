FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine
ADD target/employee-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
CMD java -jar target/employee-0.0.1-SNAPSHOT.jar