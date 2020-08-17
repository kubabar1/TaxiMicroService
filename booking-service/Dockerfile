FROM gradle:jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM openjdk:11
RUN mkdir /app
# RUN mkdir /script
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
# COPY ./wait-for-it.sh /script/wait-for-it.sh
# RUN ["chmod", "+x", "/script/wait-for-it.sh"]
# HEALTHCHECK --interval=5m --timeout=3s CMD curl -f http://images-service-database:5432 || exit 1
# ,"-Dspring.profiles.active=docker"
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]