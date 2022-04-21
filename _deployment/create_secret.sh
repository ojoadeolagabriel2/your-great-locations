#!/bin/sh

set -xe

DEFAULT_K8S_SECRET_NAME="${ENV_DEFAULT_K8S_SECRET_NAME:-"regcred"}"
DEFAULT_K8S_REGISTRY="${ENV_DEFAULT_K8S_REGISTRY:-"registry-1.docker.io"}"
DEFAULT_K8S_REGISTRY_EMAIL="${ENV_DEFAULT_K8S_REGISTRY_EMAIL:-"ojoadeolagabriel@gmail.com"}"
DEFAULT_K8S_NAMESPACE="${ENV_DEFAULT_K8S_NAMESPACE:-location-ns}"
DEFAULT_K8S_USERNAME="${ENV_DEFAULT_K8S_USERNAME:-ojoadeolagabriel}"
DEFAULT_K8S_USER_PASSWORD="${ENV_DEFAULT_K8S_USER_PASSWORD:-ojoadeolagabriel}"

# remove old secret within namespace
kubectl delete secret "$DEFAULT_K8S_SECRET_NAME" --namespace="$DEFAULT_K8S_NAMESPACE" || true

# re/create secret within registry
kubectl create secret docker-registry regcred \
  --docker-server="$DEFAULT_K8S_REGISTRY" \
  --docker-username="$DEFAULT_K8S_USERNAME" \
  --docker-password="$DEFAULT_K8S_USER_PASSWORD" \
  --namespace="$DEFAULT_K8S_NAMESPACE" \
  --docker-email="$DEFAULT_K8S_REGISTRY_EMAIL"

# printout k8s secret manifest in yaml
kubectl get secret "$DEFAULT_K8S_SECRET_NAME" --namespace="$DEFAULT_K8S_NAMESPACE" --output=yaml