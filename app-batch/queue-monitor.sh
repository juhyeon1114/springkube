#!/bin/bash

watch -n 1 kubectl exec redis -- redis-cli scard hello:task-queue