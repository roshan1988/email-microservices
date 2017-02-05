function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

cd zipkin-server
 java -jar zipkin-server-1.20.0-exec.jar > /Users/roshan/Work/Ems_Env_Logs/zipkinServerLog 2>&1 &

#watchString '/Users/roshan/Work/Ems_Env_Logs/lifecycleManagerLog' 'Started ConfigServer in'
echo 'Zipkin Server Started'

cd ../..


