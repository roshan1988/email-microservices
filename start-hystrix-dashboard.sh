function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

docker run --name hystrix --net microservicesnet -p 9999:9999 roshan1988/email-hystrix-dashboard > /Users/roshan/Work/Ems_Env_Logs/hystrixDashboardLog 2>&1 &

#watchString '/Users/roshan/Work/Ems_Env_Logs/lifecycleManagerLog' 'Started ConfigServer in'
echo 'Email Hystrix Dashboard Started'


