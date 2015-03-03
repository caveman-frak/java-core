#!/usr/bin/env bash

yum install epel-release -y
yum install salt-master -y

service salt-master start
