#!/usr/bin/env bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

set -e

cd email-configserver								
note "Building email-config-server..." 
mvn clean install
cd -
cd email-eurekaserver								
note "Building email-eureka-server..." 
mvn clean install
docker build -t roshan1988/email-eureka .
cd -
cd email-event-checkin								
note "Building email-event-checkin..." 
mvn clean install
docker build -t roshan1988/email-event-checkin .
cd -
cd email-event-checkin-apigateway	
note "Building email-event-checkin-apigateway..." 
mvn clean install
docker build -t roshan1988/email-event-checkin-apigateway .
cd -
cd email-generator									
note "Building email-generator...";
mvn clean install
docker build -t roshan1988/email-generator .
cd -
cd email-transmitter								
note "Building email-transmitter..."
mvn clean install
docker build -t roshan1988/email-transmitter .
cd -
cd email-lifecycle-manager							
note "Building email-lifecycle-manager..."
mvn clean install
docker build -t roshan1988/email-lifecycle-manager .
cd -
cd email-hystrix-dashboard							
note "Building email-hystrix-dashboard..."
mvn clean install
docker build -t roshan1988/email-hystrix-dashboard .
cd -