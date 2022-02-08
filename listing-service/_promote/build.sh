#!/bin/sh

set -xe

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && cd .. && pwd )"

# script vars
SERVICE_NAME="${ENV_SERVICE_NAME:-listing-service}"
IMAGE_NAME="${SERVICE_NAME}"
IMAGE_REGISTRY_PREFIX="registry.gitlab.com/aloeda_product_development/your_great_locations"

# versioned stuff
VERSION="${2:-$(date +%Y%m%d%H%M%S)}"

# switch to project dir
cd "$DIR"

# package app
mvn clean package spring-boot:repackage -DskipTests

docker login registry.gitlab.com -u "ojoadeolagabriel@gmail.com" -p "glpat-ikJcwVXdZqSGZtgsUKns"

# build latest image
docker build -t "${IMAGE_REGISTRY_PREFIX}/${IMAGE_NAME}:latest" -f "$DIR"/Dockerfile .
docker build -t "${IMAGE_REGISTRY_PREFIX}/${IMAGE_NAME}:${VERSION}" -f "$DIR"/Dockerfile .

# push
docker push "${IMAGE_REGISTRY_PREFIX}/${IMAGE_NAME}:latest"
docker push "${IMAGE_REGISTRY_PREFIX}/${IMAGE_NAME}:${VERSION}"