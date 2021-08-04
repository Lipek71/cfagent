FROM adoptopenjdk:16-jre-hotspot
WORKDIR /opt/app
COPY target/*.jar cfagent-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "cfagent-0.0.1-SNAPSHOT.jar"]