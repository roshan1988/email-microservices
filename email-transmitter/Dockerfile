
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-transmitter-service-1.0.jar email-transmitter-service.jar
EXPOSE 8090
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-transmitter-service.jar"]

