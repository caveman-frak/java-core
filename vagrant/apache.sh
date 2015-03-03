#!/usr/bin/env bash

yum install httpd mod_ssl -y
rm -rf /var/www
ln -fs /vagrant /var/www

/usr/sbin/apachectl start

yum install wget -y
