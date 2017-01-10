docker run --net host -p 8070:8070 -t email-event-checkin:1.0 &
docker run --net host -p 9070:9070 -t email-event-checkin-apigateway:1.0 &
docker run --net host -p 8060:8060 -t email-generator:1.0 &
docker run --net host -p 8090:8090 -t email-transmitter:1.0 &
docker run --net host -p 9090:9090 -t email-lifecycle-manager:1.0 &

