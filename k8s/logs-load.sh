POD_NAME=$(kubectl get pods | grep "dpl-nilspet-load" | grep -o '^[^ ]*')

kubectl logs $POD_NAME