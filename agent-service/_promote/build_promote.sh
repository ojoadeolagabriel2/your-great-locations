#!/bin/sh

set -xe

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && cd .. && pwd )"

# script vars
SERVICE_NAME="${ENV_SERVICE_NAME:-agent-service}"
IMAGE_NAME="ojoadeolagabriel/${SERVICE_NAME}"

# versioned stuff
VERSION="${ENV_VERSION:-$(date +%Y%m%d%H%M%S)}"
RESOLVED_NAME="${IMAGE_NAME}:${VERSION}"

# switch to project dir
cd "$DIR"

# package app
mvn clean package spring-boot:repackage -DskipTests

echo "Saturn432$" | docker login registry-1.docker.io -u ojoadeolagabriel --password-stdin

# build latest image
docker build -t "${IMAGE_NAME}:latest" -f "$DIR"/Dockerfile .

# build versioned image
docker build -t "${RESOLVED_NAME}" -f "$DIR"/Dockerfile .

# push image
docker push "${IMAGE_NAME}:latest"