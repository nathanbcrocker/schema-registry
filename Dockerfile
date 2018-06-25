FROM openjdk:10-jdk
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} schema-registry.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/schema-registry.jar"]