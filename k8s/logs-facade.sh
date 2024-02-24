POD_NAME=$(kubectl get pods | grep "dpl-nilspet-facade" | grep -o '^[^ ]*')

kubectl logs $POD_NAME