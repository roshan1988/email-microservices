
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-event-checkin-1.0.jar email-event-checkin.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","/email-event-checkin.jar"]

