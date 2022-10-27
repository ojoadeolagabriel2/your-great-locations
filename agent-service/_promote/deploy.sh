#!/usr/bin/env sh

set -ex

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

kubectl delete namespace location-ns

kubectl apply -f "$DIR/k8s-manifests/manifest.yaml"