FROM openjdk:8-jdk-alpine

RUN mkdir -p /home/microservices/add

COPY . /home/microservices/add

WORKDIR /home/microservices/add

EXPOSE 8200

CMD ["./gradlew", "clean", "bootRun"]