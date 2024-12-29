#!/bin/bash

while true; do
  for ((i=0; i<50; i++)); do
    curl -s http://localhost/cache/hello?name=world &
  done
  sleep 1
done