#!/bin/sh

set -xe

export APP_NAME=${ENV_APP_NAME:-agent-service}
export APP_NAMESPACE=${ENV_APP_NAMESPACE:-location_ns}

# shellcheck disable=SC2005
echo "$( \
          jsonnet \
          --ext-str "APP_NAMESPACE=$APP_NAMESPACE" \
          --ext-str "APP_NAME=$APP_NAME" \
           dev.apply.yaml.jsonnet)" \
             | sed 's/\\//g' \
             | tr -d '"' \
             > doc.yaml
