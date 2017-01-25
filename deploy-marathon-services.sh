cd marathon-applications
curl -X POST http://marathon:8080/v2/apps -d @email-event-checkin.json -H "Content-type: application/json"
#curl -X POST http://marathon:8080/v2/apps -d @email-event-checkin-apigateway.json -H "Content-type: application/json"
curl -X POST http://marathon:8080/v2/apps -d @email-generator.json -H "Content-type: application/json"
curl -X POST http://marathon:8080/v2/apps -d @email-transmitter.json -H "Content-type: application/json"
cd ..