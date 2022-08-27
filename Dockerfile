FROM adoptopenjdk/openjdk11:jdk-11.0.2.7-alpine-slim
COPY java.security /opt/java/openjdk/conf/security
COPY target/employee-0.0.4.jar .
EXPOSE 8082
CMD  java -jar employee-0.0.4.jar
