./build-all.sh
#./pushDockerImages.sh
./setupEnvironment.sh
./start-config.sh
./start-eureka.sh
./start-lifecycle-manager.sh
./start-hystrix-dashboard.sh
./start-services-admin.sh
./deploy-marathon-services.sh


