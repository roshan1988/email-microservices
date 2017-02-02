
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-transmitter-service-1.0.jar email-transmitter-service.jar
EXPOSE 8090
ENTRYPOINT ["java","-Xmx400m","-jar","/email-transmitter-service.jar"]

