
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-event-checkin-apigateway-1.0.jar email-event-checkin-apigateway.jar
EXPOSE 9070
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-event-checkin-apigateway.jar"]

