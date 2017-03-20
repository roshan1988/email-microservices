function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

cd email-services-admin/target
docker run --name adminservice -m 500M --net microservicesnet -p 9010:9010 roshan1988/email-services-admin > /Users/roshan/Work/Ems_Env_Logs/servicesAdminLog 2>&1 &

#watchString '/Users/roshan/Work/Ems_Env_Logs/lifecycleManagerLog' 'Started ConfigServer in'
echo 'Email Services Admin Started'

cd ../..


