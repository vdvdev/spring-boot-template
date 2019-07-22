FROM openjdk:11.0.3-jre-slim
#Spring boot apps create working dirs in /tmp by default. The step is necessary if apps need to write in the filesystem
VOLUME /tmp
COPY target/spring-boot-template-?.?.?-SNAPSHOT.jar app.jar
#-Djava.security.egd=file:/dev/./urandom is used to reduce Tomcat startup time
ENTRYPOINT  [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.additional-location=file:/opt/config/config.yaml,file:/opt/secret/secret.yaml -jar /app.jar", "" ]