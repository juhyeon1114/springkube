#!/bin/bash

kubectl -n springkube exec -it springkube-app-68557d7b54-7kzvm -- bash # Pod 접속
curl localhost:8080/hello?name=world # curl로 API 호출