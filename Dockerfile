FROM openjdk:17
COPY target/new.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","new.jar"]