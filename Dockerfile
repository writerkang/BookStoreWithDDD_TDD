FROM openjdk:16
ADD . /app
WORKDIR /app
RUN chmod 777 gradlew
RUN ./gradlew
EXPOSE 30082