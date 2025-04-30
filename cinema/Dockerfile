# Stage 1: Build từ source code
FROM maven:3.9.3-eclipse-temurin-17 AS builder

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ source code vào container
COPY . .

# Build project, tạo file .jar
RUN mvn clean package -DskipTests

# Stage 2: Chạy file jar đã build
FROM openjdk:17-jdk-slim

# Thư mục làm việc khi container chạy
WORKDIR /app

# Copy file .jar từ Stage 1 sang
COPY --from=builder /app/target/*.jar app.jar

# Chạy file jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Mở port 8080 để Railway biết mở cổng
EXPOSE 8081