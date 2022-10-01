FROM openjdk:8-jdk-alpine

RUN mkdir -p /home/microservices/gateway

COPY . /home/microservices/gateway

WORKDIR /home/microservices/gateway

EXPOSE 8762

CMD ["./gradlew", "clean", "bootRun"]