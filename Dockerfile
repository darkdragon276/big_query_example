FROM openjdk:17
EXPOSE 8080
ADD target/big-query-example-image.jar big-query-example-image.jar
ADD target/classes/key.json key.json
ENV GOOGLE_APPLICATION_CREDENTIALS=/key.json
ENTRYPOINT [ "java", "-jar", "/big-query-example-image.jar" ]