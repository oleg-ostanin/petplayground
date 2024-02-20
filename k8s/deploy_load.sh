kubectl delete svc svc-nilspet-load
kubectl delete deployment dpl-nilspet-load

SCRIPT_DIR=$(pwd)
echo $SCRIPT_DIR
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
echo $PROJECT_DIR

cd $PROJECT_DIR

docker rmi --force $(docker images -q 'nilspet-load' | uniq)

minikube image rm --force 'nilspet-load'

docker images

gradle clean build

cd $PROJECT_DIR/load/docker
rm $PROJECT_DIR/load/docker/load-1.0-SNAPSHOT.jar
mv $PROJECT_DIR/load/build/libs/load-1.0-SNAPSHOT.jar $PROJECT_DIR/load/docker/load-1.0-SNAPSHOT.jar
docker build -t nilspet-load:latest .

eval $(minikube docker-env)

minikube image load 'nilspet-load'

cd $SCRIPT_DIR
kubectl apply -f $SCRIPT_DIR/load/dpl-load.yaml --validate=false
kubectl apply -f $SCRIPT_DIR/load/svc-load.yaml