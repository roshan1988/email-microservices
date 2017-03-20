function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

cd email-lifecycle-manager/target
docker run --name lifecycle -m 500M --net microservicesnet roshan1988/email-lifecycle-manager > /Users/roshan/Work/Ems_Env_Logs/lifecycleManagerLog 2>&1 &

#watchString '/Users/roshan/Work/Ems_Env_Logs/lifecycleManagerLog' 'Started ConfigServer in'
echo 'Email Lifecycle Manager Started'

cd ../..


