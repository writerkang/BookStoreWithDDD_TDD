FROM openjdk:16
ADD . /app
WORKDIR /app
RUN chomod 777 gradlew
RUN ./gradlew
EXPOSE 30082