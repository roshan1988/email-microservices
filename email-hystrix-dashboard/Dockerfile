
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-hystrix-dashboard-0.0.1-SNAPSHOT.jar email-hystrix-dashboard.jar
EXPOSE 9999
ENTRYPOINT ["java","-Xmx400m","-Djava.security.egd=file:/dev/urandom","-jar","/email-hystrix-dashboard.jar"]

