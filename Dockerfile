# Build stage
FROM openjdk:11 AS builder
WORKDIR /gradle
COPY gradlew build.gradle settings.gradle ./
COPY gradle/ gradle/
COPY . .

# Gradle Wrapper 스크립트에 실행 권한을 부여
RUN chmod +x ./gradlew
# build 과정 로깅하기 위해 아래 코드로 변경
RUN ./gradlew clean build --no-daemon


# Run stage
FROM openjdk:11
EXPOSE 8080
WORKDIR /app
COPY --from=builder /gradle/build/libs/*.jar ./app.jar

# 데몬으로 실행되도록 CMD로 지정
CMD ["java", "-jar", "app.jar"]
