#!/bin/sh

set -xe

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && cd .. && pwd )"

# script vars
SERVICE_NAME="${ENV_SERVICE_NAME:-listing-service}"
IMAGE_NAME="${SERVICE_NAME}"

# versioned stuff
VERSION="${ENV_VERSION:-$(date +%Y%m%d%H%M%S)}"
RESOLVED_NAME="${IMAGE_NAME}:${VERSION}"

# switch to project dir
cd "$DIR"

# package app
mvn clean package spring-boot:repackage -DskipTests

docker login registry.gitlab.com

# build latest image
docker build -t "${IMAGE_NAME}:latest" -f "$DIR"/Dockerfile .

# build versioned image
docker build -t "${RESOLVED_NAME}" -f "$DIR"/Dockerfile .

# run from k8s
"$DIR"/k8s/apply.sh