FROM openjdk:11
RUN mkdir -p  deploy
WORKDIR /deploy
COPY ./build/libs/consumer-0.0.1.jar consumer.jar
ENTRYPOINT ["java", "-jar", "/deploy/consumer.jar"]

# consumer 모듈에 대한 도커 이미지