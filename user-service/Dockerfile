# syntax=docker/dockerfile:1

FROM openjdk:11 as prod
WORKDIR /app
COPY /spring-app/target/user-service.jar .
VOLUME /logs
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "user-service.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "user-service.jar"]

EXPOSE 8556