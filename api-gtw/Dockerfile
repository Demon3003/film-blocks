# syntax=docker/dockerfile:1

FROM openjdk:11 as prod
WORKDIR /app
COPY /spring-app/target/api-gtw.jar .
VOLUME /logs
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "api-gtw.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "api-gtw.jar"]

EXPOSE 8765