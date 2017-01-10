docker stop $(docker ps -a -q)
docker run --net=host --add-host=moby:127.0.0.1 -p 8888:8888 -t email-config-server:1.0

