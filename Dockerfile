FROM openjdk:10-jdk
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} schema-registry.jar
CMD /usr/bin/java --add-modules java.xml.bind -jar /schema-registry.jar