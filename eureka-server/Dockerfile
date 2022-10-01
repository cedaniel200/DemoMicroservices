FROM openjdk:8-jdk-alpine

RUN mkdir -p /home/microservices/eureka

COPY . /home/microservices/eureka

WORKDIR /home/microservices/eureka

EXPOSE 8761

CMD ["./gradlew", "clean", "bootRun"]