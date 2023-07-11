FROM openjdk:17
EXPOSE 8080
ADD target/pathfinder.jar pathfinder.jar
ENTRYPOINT ["java", "-jar", "/pathfinder.jar"]