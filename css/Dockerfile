FROM openjdk:11
RUN mkdir -p  deploy
WORKDIR /deploy
COPY ./build/libs/css-0.0.1.jar css.jar
ENTRYPOINT ["java", "-jar", "/deploy/css.jar"]

# css 모듈에 대한 도커 이미지