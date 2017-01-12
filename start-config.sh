docker stop $(docker ps -a -q)
docker run --net=host -p 8888:8888 -t roshan1988/email-config-server:1.0

