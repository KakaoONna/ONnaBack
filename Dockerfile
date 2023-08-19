FROM openjdk:11 AS builder
WORKDIR /gradle
COPY gradlew build.gradle settings.gradle ./
COPY gradle/ gradle/
COPY . .
RUN ./gradlew clean build --no-daemon
# Run stage
FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY --from=builder /gradle/build/libs/*.jar ./app.jar

# 데몬으로 실행되도록 CMD로 지정
CMD ["java", "-jar", "app.jar"]