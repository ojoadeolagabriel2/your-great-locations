#!/usr/bin/env sh

set -ex

PORT=${ENV_PORT:-3000}

docker run -d \
  -p "$PORT":3000 \
  --name=grafana \
  -e "GF_INSTALL_PLUGINS=grafana-clock-panel,grafana-simple-json-datasource" \
  grafana/grafana-enterprise

docker inspect grafana | jq '.[].NetworkSettings.Networks.bridge.IPAddress'