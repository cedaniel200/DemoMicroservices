FROM openjdk:8-jdk-alpine

RUN mkdir -p /home/microservices/subtract

COPY . /home/microservices/subtract

WORKDIR /home/microservices/subtract

EXPOSE 8300

CMD ["./gradlew", "clean", "bootRun"]