#!/bin/bash

kubectl -n springkube create secret generic app-secret --from-literal=api-key=abcd12345678