# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM tomcat:9.0-jdk17-temurin
# Remove default apps to keep it clean and safe
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy the built WAR to ROOT.war to serve it at the root path
COPY --from=build /app/target/PayRollManagement.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
