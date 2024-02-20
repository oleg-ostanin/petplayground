kubectl delete svc svc-nilspet-facade
kubectl delete deployment dpl-nilspet-facade

SCRIPT_DIR=$(pwd)
echo $SCRIPT_DIR
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
echo $PROJECT_DIR

cd $PROJECT_DIR

docker rmi --force $(docker images -q 'nilspet-facade' | uniq)

minikube image rm --force 'nilspet-facade'

docker images

gradle clean build

cd $PROJECT_DIR/facade/docker
rm $PROJECT_DIR/facade/docker/facade-1.0-SNAPSHOT.jar
mv $PROJECT_DIR/facade/build/libs/facade-1.0-SNAPSHOT.jar $PROJECT_DIR/facade/docker/facade-1.0-SNAPSHOT.jar
docker build -t nilspet-facade:latest .

eval $(minikube docker-env)

minikube image load 'nilspet-facade'

cd $SCRIPT_DIR
kubectl apply -f $SCRIPT_DIR/facade/dpl-facade.yaml --validate=false
kubectl apply -f $SCRIPT_DIR/facade/svc-facade.yaml