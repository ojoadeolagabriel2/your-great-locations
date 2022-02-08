#!/usr/bin/env sh

set -ex

LOCAL_NET=${ENV_LOCAL_NET:-my_local_network}

docker network inspect "$LOCAL_NET" >/dev/null 2>&1 || \
    docker network create --driver bridge "$LOCAL_NET"