
FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD  target/email-services-admin-1.0.jar email-services-admin.jar
EXPOSE 9010
ENTRYPOINT ["java","-Xmx400m","-jar","/email-services-admin.jar"]

