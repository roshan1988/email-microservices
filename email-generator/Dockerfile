
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-generator-service-1.0.jar email-generator-service.jar
EXPOSE 8060
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-generator-service.jar"]

