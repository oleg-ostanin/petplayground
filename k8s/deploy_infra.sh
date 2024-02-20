kubectl delete svc svc-nilspet-psql
kubectl delete svc svc-nilspet-redis
kubectl delete deployment dpl-nilspet-psql
kubectl delete deployment dpl-nilspet-redis

SCRIPT_DIR=$(pwd)
echo $SCRIPT_DIR
PROJECT_DIR="$(dirname "$SCRIPT_DIR")"
echo $PROJECT_DIR

cd $PROJECT_DIR

docker rmi --force $(docker images -q 'nilspet-redis' | uniq)
docker rmi --force $(docker images -q 'nilspet-postgresql' | uniq)

minikube image rm --force 'nilspet-redis'
minikube image rm --force 'nilspet-postgresql'

cd $SCRIPT_DIR/postgresql
docker build -t nilspet-postgresql:latest .

cd $SCRIPT_DIR/redis
docker build -t nilspet-redis:latest .

eval $(minikube docker-env)

minikube image load 'nilspet-redis'
minikube image load 'nilspet-postgresql'

cd $SCRIPT_DIR
kubectl apply -f $SCRIPT_DIR/postgresql/dpl-psql.yaml --validate=false
kubectl apply -f $SCRIPT_DIR/postgresql/svc-psql.yaml
kubectl apply -f $SCRIPT_DIR/redis/dpl-redis.yaml --validate=false
kubectl apply -f $SCRIPT_DIR/redis/svc-redis.yaml