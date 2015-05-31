#!/usr/bin/env bash

yum update -y

yum install dkms -y

yum install yum-cron -y
chkconfig yum-cron on
