# Location listing service
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

:star: send a star our way

A light weight, containerized pokemon information service for you favourite pokemon

* fetch listing information
* fetch offers information
* caches responses to improve performance (~ 30ms once listing information is cached)
* provides easy to use startup script for running app in a java 11 based docker container

## Table of content

- [Installation](#installation)
- [Getting started](#setup)
    - [Required parameters](#parameters)
- [How to use](#usage)

## Installation

Services require that apache maven 3+ be installed. It also requires a running docker instance locally on your unix machine

1. install apache maven from here unto your unix machine: https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-mac-os-x
2. install docker to your unix machine here: https://docs.docker.com/docker-for-mac/install/
3. install java 11 on your unix machine as seen here https://java.tutorials24x7.com/blog/how-to-install-java-11-on-mac
