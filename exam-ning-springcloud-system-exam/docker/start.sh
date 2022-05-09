#!/bin/sh

cd `dirname $0`

IMAGE=$1

if [[ ! -n "${IMAGE}" ]]; then
    echo "IMAGE not set."
    exit 1
fi

rm -f .env
echo "image=${IMAGE}" >> .env

docker-compose pull
docker-compose config
docker-compose up -d
docker-compose ps
