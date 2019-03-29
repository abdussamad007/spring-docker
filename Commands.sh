mvn clean build
java -jar target/spring-docker-0.0.1-SNAPSHOT.jar 
tail -f logs/health.txt
docker ps
docker exec -it running_container /bin/sh
vim Dockerfile
docker images 
docker build .
docker build . -t hello-docker
docker tag hello-docker ashishwaghmare/hello-docker
docker run -it image_name
docker run -p 8080:8080 -it image_name
docker run -p 9090:8080 -it image_name
docker login --username docker_hub_user_name
docker push ashishwaghmare/hello-docker
docker volume create new-logs
docker volume inspect --format '{{ .Mountpoint }}' new-logs
docker run -p 9090:8080 -v new-logs:/logs -it hello-docker
sudo su
cd /home/docker/volumes