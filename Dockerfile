FROM openjdk:17
EXPOSE 8080
ADD target/big-query-example-image.jar big-query-example-image.jar
ENTRYPOINT [ "java", "-jar", "/big-query-example-image.jar" ]