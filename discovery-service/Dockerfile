# syntax=docker/dockerfile:1

FROM openjdk:11 as prod
WORKDIR /app
COPY /target/discovery-service.jar .
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "discovery-service.jar"]


FROM prod as dev
ENV SPRING_PROFILES_ACTIVE=dev
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000", "-jar", "discovery-service.jar"]

EXPOSE 8761