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

docker run -d -p 5000:5000 --name localregistry registry

docker push localhost:5000/email-config-server:1.0
docker push localhost:5000/email-eureka-server
docker push localhost:5000/email-event-checkin
docker push localhost:5000/email-event-checkin-apigateway
docker push localhost:5000/email-generator
docker push localhost:5000/email-transmitter


rm /Users/roshan/Work/Ems_Env_Logs/*
rm -rf /Users/roshan/Work/zkDataDir


cd /usr/local/Cellar/zookeeper/3.4.8/libexec/bin
# Conf File : /usr/local/Cellar/zookeeper/3.4.8/libexec/conf/zoo.cfg
./zkServer.sh start > /Users/roshan/Work/Ems_Env_Logs/zookeeperLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/zookeeperLog' 'STARTED'
echo 'Zookeeper Started'
printPIDForPort 2181

cd /usr/local/sbin/
echo FileCreated >> /Users/roshan/Work/Ems_Env_Logs/mesosMasterLog
./mesos-master  --registry=in_memory --zk=zk://localhost:2181/mesos --quorum=1 > /Users/roshan/Work/Ems_Env_Logs/mesosMasterLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/mesosMasterLog' 'agents from the registry'
echo 'Mesos Master Started'
printPIDForPort 5050

cd /usr/local/sbin/
rm -rf /Users/roshan/Work/mesos_slave_work_dir/*
echo FileCreated >> /Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog
./mesos-agent --containerizers=mesos,docker --work_dir=/Users/roshan/Work/mesos_slave_work_dir --master=zk://localhost:2181/mesos > /Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog 2>&1 &
printPID mesos-agent
watchString '/Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog' 'session establishment complete on server'
echo 'Mesos agent/slave Started'
printPIDForName mesos-agent

cd /Users/roshan/marathon-1.3.5/bin/
./start --master zk://localhost:2181/mesos --zk zk://localhost:2181/marathon > /Users/roshan/Work/Ems_Env_Logs/marathonLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/marathonLog' 'All services up and running'
echo 'Marathon Started'
printPIDForPort 8080

