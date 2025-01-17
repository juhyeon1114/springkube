#!/bin/bash

helm repo add opensearch https://opensearch-project.github.io/helm-charts
kubectl create namespace opensearch
helm -n opensearch install opensearch opensearch/opensearch
helm -n opensearch install dashboard opensearch/opensearch-dashboards