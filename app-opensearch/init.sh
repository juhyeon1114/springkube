#!/bin/bash

helm repo add opensearch https://opensearch-project.github.io/helm-charts
kubectl create namespace opensearch
helm -n opensearch install opensearch opensearch/opensearch --values=values.yaml
helm -n opensearch install dashboard opensearch/opensearch-dashboards

kubectl -n opensearch port-forward dashboard-opensearch-dashboards-675fd5df99-2qsnj 5601