FROM adoptopenjdk:11-jre-hotspot
COPY build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]