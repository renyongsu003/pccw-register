FROM openjdk:8

COPY register-starter/target/register-starter-0.0.1-SNAPSHOT.jar /opt/www/htdocs/

EXPOSE 8080
CMD ["java", "-jar", "/opt/www/htdocs/register-starter-0.0.1-SNAPSHOT.jar"]