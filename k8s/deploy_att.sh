kubectl delete svc spring-service
kubectl delete deployment spring-app

SCRIPT_DIR=$(pwd)
echo $SCRIPT_DIR
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
echo $PROJECT_DIR

cd $PROJECT_DIR

docker rmi --force $(docker images -q 'nilspet-facade' | uniq)
docker rmi --force $(docker images -q 'nilspet-redis' | uniq)
docker rmi --force $(docker images -q 'nilspet-postgresql' | uniq)

minikube image rm 'nilspet-facade'
minikube image rm 'nilspet-redis'
minikube image rm 'nilspet-postgresql'

docker images

gradle clean build

cd $PROJECT_DIR/facade/docker
rm $PROJECT_DIR/facade/docker/facade-1.0-SNAPSHOT.jar
mv $PROJECT_DIR/facade/build/libs/facade-1.0-SNAPSHOT.jar $PROJECT_DIR/facade/docker/facade-1.0-SNAPSHOT.jar
docker build -t nilspet-facade:latest .

cd $SCRIPT_DIR/postgresql
docker build -t nilspet-postgresql:latest .

cd $SCRIPT_DIR/redis
docker build -t nilspet-redis:latest .

eval $(minikube docker-env)

minikube image load 'nilspet-facade'
minikube image load 'nilspet-redis'
minikube image load 'nilspet-postgresql'

cd $SCRIPT_DIR
kubectl apply -f deployment.yaml --validate=false
kubectl apply -f service.yaml