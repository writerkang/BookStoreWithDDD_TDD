FROM openjdk:16
ADD . /app
WORKDIR /app
RUN chmod 777 gradlew
RUN ./gradlew build
CMD ["java", "-jar", "build/libs/bookstore-0.0.1-SNAPSHOT.jar"]
EXPOSE 30082