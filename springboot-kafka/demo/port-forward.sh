#!/bin/bash

export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/instance=grafana,app.kubernetes.io/name=grafana" -o jsonpath="{.items[0].metadata.name}")
kubectl --namespace default port-forward $POD_NAME 3000 &


export POD_NAME=$(kubectl get pods --namespace default -l "app=prometheus,component=server" -o jsonpath="{.items[0].metadata.name}")
kubectl --namespace default port-forward $POD_NAME 9090 &


export POD_NAME=$(kubectl get pods --namespace default -l "app=cp-control-center,release=officefriday" -o jsonpath="{.items[0].metadata.name}")
kubectl --namespace default port-forward $POD_NAME 9021 &

export POD_NAME=$(kubectl get pods --namespace default -l "app=cp-kafka-connect,release=officefriday" -o jsonpath="{.items[0].metadata.name}")
kubectl --namespace default port-forward $POD_NAME 8083 &

kubectl port-forward --namespace default svc/postgres-postgresql 5432:5432 &

kubectl proxy &

