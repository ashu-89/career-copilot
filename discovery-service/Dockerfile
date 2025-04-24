# Use OpenJDK 21 base image (Alpine variant) to build the application
FROM alpine/java:21.0.2-jdk

# Set the location for temporary files
VOLUME /tmp

# Build the application JAR file
ARG JAR_FILE=target/discovery-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
