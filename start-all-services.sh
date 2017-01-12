docker run --net host -p 8070:8070 -t roshan1988/email-event-checkin:1.0 &
docker run --net host -p 9070:9070 -t roshan1988/email-event-checkin-apigateway:1.0 &
docker run --net host -p 8060:8060 -t roshan1988/email-generator:1.0 &
docker run --net host -p 8090:8090 -t roshan1988/email-transmitter:1.0 &
docker run --net host -p 9090:9090 -t roshan1988/email-lifecycle-manager:1.0 &

