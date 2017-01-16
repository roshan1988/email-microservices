function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

function dockip() {   
docker inspect --format '{{ .NetworkSettings.IPAddress }}' "$@"; 
}

configIpLocal=$(dockip configServer)

docker run --net=bridge -p 8761:8761 --name=eurekaServer -e "spring.cloud.config.uri=http://${configIpLocal}:8888"  -e "spring.cloud.config.overrideSystemProperties=false" localhost:5000/email-eureka-server:1.0 > /Users/roshan/Work/Ems_Env_Logs/eurekaLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/eurekaLog' 'initialization completed in'
echo 'Eureka Server Started'
eurekaIpLocal=$(dockip eurekaServer)
echo Email Eureka Server Ip
echo $eurekaIpLocal

