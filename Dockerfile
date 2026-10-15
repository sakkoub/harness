FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /src
COPY pom.xml .
RUN mvn -B -q dependency:go-offline
COPY src ./src
# Tests already ran in the pipeline's "build and test" step
RUN mvn -B -q package -DskipTests

FROM eclipse-temurin:11-jre
RUN useradd --system --uid 10001 app
WORKDIR /app
COPY --from=build /src/target/harness-sample-1.0.0-SNAPSHOT.jar app.jar
USER 10001
EXPOSE 8080
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "app.jar"]
