function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

function printPIDForName() {
	local processName=$1
	ps -A | grep -m1 $processName | awk '{print $1}'
}

function printPIDForPort() {
	local port=$1
	lsof -n -i4TCP:$port | grep LISTEN | awk '{print $2}'
}

rm /Users/roshan/Work/Ems_Env_Logs/*

export PATH="$PATH:/usr/local/Cellar/logstash/5.1.1/bin/"
logstash -f logstash.conf > /Users/roshan/Work/Ems_Env_Logs/logtsashLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/logtsashLog' 'Successfully started Logstash'
echo 'Successfully started Logstash'

elasticsearch > /Users/roshan/Work/Ems_Env_Logs/elasticSearchLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/elasticSearchLog' 'started'
echo 'Successfully started Elasticsearch'

kibana > /Users/roshan/Work/Ems_Env_Logs/kibanaLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/kibanaLog' 'green - Ready'
echo 'Successfully started Kibana'

