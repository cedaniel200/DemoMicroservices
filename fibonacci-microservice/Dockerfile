FROM openjdk:8-jdk-alpine

RUN mkdir -p /home/microservices/fibonacci

COPY . /home/microservices/fibonacci

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /home/microservices/fibonacci

EXPOSE 8400

CMD ["./gradlew", "clean", "bootRun"]