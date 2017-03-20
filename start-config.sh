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

docker run --net=bridge -p 8888:8888 --name=configServer localhost:5000/email-config-server:1.0 > /Users/roshan/Work/Ems_Env_Logs/configserverLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/configserverLog' 'Started ConfigServer in'
echo 'Email Config Server Started'
configIpLocal=$(dockip configServer)
echo Email Config Server Ip
echo $configIpLocal
