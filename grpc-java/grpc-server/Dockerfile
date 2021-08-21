FROM openjdk:11-jre-slim
COPY target/grpc-server-*.jar /grpc-server.jar
CMD ["java", "-jar", "/grpc-server.jar"]
EXPOSE 8080