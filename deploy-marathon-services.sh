
function dockip() {   
docker inspect --format '{{ .NetworkSettings.IPAddress }}' "$@"; 
}

function editEnvConfig() { 
  jq '.env."'$1'"="'$2'"'
}

cd marathon-applications

configIp=$(dockip configServer)
eurekaIp=$(dockip eurekaServer)

cat email-event-checkin.json | editEnvConfig "spring.cloud.config.uri" "http://${configIp}:8888" | editEnvConfig "eureka.client.serviceUrl.defaultZone" "http://${eurekaIp}:8761/eureka/" > email-event-checkin-modified.json
echo "Posting Application to Marathon : Event Checkin Service"
curl -X POST http://localhost:8080/v2/apps -d @email-event-checkin-modified.json -H "Content-type: application/json"
cat email-event-checkin-apigateway.json | editEnvConfig "spring.cloud.config.uri" "http://${configIp}:8888" | editEnvConfig "eureka.client.serviceUrl.defaultZone" "http://${eurekaIp}:8761/eureka/" > email-event-checkin-apigateway-modified.json
echo "Posting Application to Marathon : Event Checkin API Service"
curl -X POST http://localhost:8080/v2/apps -d @email-event-checkin-apigateway-modified.json -H "Content-type: application/json"
cat email-generator.json | editEnvConfig "spring.cloud.config.uri" "http://${configIp}:8888" | editEnvConfig "eureka.client.serviceUrl.defaultZone" "http://${eurekaIp}:8761/eureka/" > email-generator-modified.json
echo "Posting Application to Marathon : Email Generator Service"
curl -X POST http://localhost:8080/v2/apps -d @email-generator-modified.json -H "Content-type: application/json"
cat email-transmitter.json | editEnvConfig "spring.cloud.config.uri" "http://${configIp}:8888" | editEnvConfig "eureka.client.serviceUrl.defaultZone" "http://${eurekaIp}:8761/eureka/" > email-transmitter-modified.json
echo "Posting Application to Marathon : Email Transmitter Service"
curl -X POST http://localhost:8080/v2/apps -d @email-transmitter-modified.json -H "Content-type: application/json"
cd ..