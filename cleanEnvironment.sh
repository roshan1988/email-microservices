function killProcessForName() {
	local processName=$1
	local pid=$(ps -A | grep -m1 $processName | awk '{print $1}')
	if [ ! -z "$pid" -a "$pid" != " " ]; then
        echo "Killing process with Id : ${pid}"
        kill -9 $pid
    fi
}

function killProcessForPort() {
	local port=$1
	local pid=$(lsof -n -i4TCP:$port | grep LISTEN | awk '{print $2}')
	if [ ! -z "$pid" -a "$pid" != " " ]; then
        echo "Killing process with Id : ${pid}"
        kill -9 $pid
    fi
}


killProcessForPort 5601
killProcessForPort 9600
killProcessForPort 9300
killProcessForPort 8761
killProcessForPort 8888

# killProcessForName mesos-agent
# killProcessForPort 5051
# killProcessForPort 2181
# killProcessForPort 8888
# killProcessForPort 8761

