
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-lifecycle-manager-1.0.jar email-lifecycle-manager.jar
EXPOSE 9090
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-lifecycle-manager.jar"]

