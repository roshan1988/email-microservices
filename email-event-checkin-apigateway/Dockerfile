
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-event-checkin-apigateway-1.0.jar email-event-checkin-apigateway.jar
EXPOSE 8075
ENTRYPOINT ["java","-jar","/email-event-checkin-apigateway.jar"]

