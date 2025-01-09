#!/bin/bash

for ((i=0; i<1000; i++)); do
  curl -s "http://localhost/cache/hello/enqueue/$i" &
done