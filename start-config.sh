function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

cd email-configserver/target
java -jar email-config-server-1.0.jar > /Users/roshan/Work/Ems_Env_Logs/configserverLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/configserverLog' 'Started ConfigServer in'
echo 'Email Config Server Started'

cd ../..


