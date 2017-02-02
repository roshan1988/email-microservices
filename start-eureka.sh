function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

cd email-eurekaserver/target
# java -jar email-eureka-server-1.0.jar > /Users/roshan/Work/Ems_Env_Logs/eurekaLog 2>&1 &
docker run --name eureka -p 8761:8761 -m 500M --net microservicesnet -h eureka roshan1988/email-eureka > /Users/roshan/Work/Ems_Env_Logs/eurekaLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/eurekaLog' 'initialization completed in'
echo 'Eureka Server Started'

cd ../..


