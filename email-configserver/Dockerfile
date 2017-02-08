
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-config-server-1.0.jar email-config-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/email-config-server.jar"]

