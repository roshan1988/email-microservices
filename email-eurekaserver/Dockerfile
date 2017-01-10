
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-eureka-server-1.0.jar email-eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/email-eureka-server.jar"]

