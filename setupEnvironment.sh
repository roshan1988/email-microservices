function watchString() {
	local fileName=$1
	local watchPattern=$2
	echo "Watching file ${fileName} for patern : ${watchPattern}"
	tail -f $fileName | while read LOGLINE
do
   [[ "${LOGLINE}" == *"${watchPattern}"* ]] && pkill -P $$ tail
done
}

function printPID() {
	local processName=$1
	ps -A | grep -m1 $processName | awk '{print $1}'
}

rm /Users/roshan/Work/Ems_Env_Logs/*

cd /usr/local/Cellar/zookeeper/3.4.8/libexec/bin
# Conf File : /usr/local/Cellar/zookeeper/3.4.8/libexec/conf/zoo.cfg
rm -rf /Users/roshan/Work/zkDataDir
./zkServer.sh start > /Users/roshan/Work/Ems_Env_Logs/zookeeperLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/zookeeperLog' 'STARTED'
echo 'Zookeeper Started'

cd /usr/local/sbin/
echo FileCreated >> /Users/roshan/Work/Ems_Env_Logs/mesosMasterLog
./mesos-master  --registry=in_memory --zk=zk://localhost:2181/mesos --quorum=1 > /Users/roshan/Work/Ems_Env_Logs/mesosMasterLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/mesosMasterLog' 'agents from the registry'
printPID mesos-master

cd /usr/local/sbin/
rm -rf /Users/roshan/Work/mesos_slave_work_dir/*
echo FileCreated >> /Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog
./mesos-agent --containerizers=mesos,docker --work_dir=/Users/roshan/Work/mesos_slave_work_dir --master=zk://localhost:2181/mesos > /Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog 2>&1 &
printPID mesos-agent
watchString '/Users/roshan/Work/Ems_Env_Logs/mesosSlaveLog' 'session establishment complete on server'

cd /Users/roshan/marathon-1.3.5/bin/
./start --master zk://localhost:2181/mesos --zk zk://localhost:2181/marathon > /Users/roshan/Work/Ems_Env_Logs/marathonLog 2>&1 &
watchString '/Users/roshan/Work/Ems_Env_Logs/marathonLog' 'All services up and running'