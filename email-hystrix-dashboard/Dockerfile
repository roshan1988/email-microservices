
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-hystrix-dashboard-0.0.1-SNAPSHOT.jar email-hystrix-dashboard.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/email-hystrix-dashboard.jar"]

