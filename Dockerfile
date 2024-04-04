FROM openjdk:17

WORKDIR /app

COPY build/libs/ms-search-0.0.1-SNAPSHOT.jar /app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/ms-search-0.0.1-SNAPSHOT.jar"]
CMD [""]





