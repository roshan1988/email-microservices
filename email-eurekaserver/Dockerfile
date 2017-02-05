
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-eureka-server-1.0.jar email-eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-eureka-server.jar"]

