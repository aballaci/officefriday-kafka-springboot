FROM maven:3.6.3-jdk-11 as MAVEN_BUILD

MAINTAINER Armand Ballaci

COPY target/*consumer*.jar target/consumer.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../consumer.jar)

FROM openjdk:11-jdk-slim as production
ARG DEPENDENCY=target/dependency
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=MAVEN_BUILD ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=MAVEN_BUILD ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","com.springboot.KafkaConsumerApplication"]