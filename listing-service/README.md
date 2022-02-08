# Location listing service
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

:star: send a star our way

A light weight, containerized pokemon information service for you favourite pokemon

* fetch listing information
* caches responses to improve performance (~ 30ms once listing information is cached)
* provides easy to use startup script for running app in a java 11 based docker container

## Table of content

- [Installation](#installation)
- [Setup](#setup)
    - [Parameters](#parameters)
- [How to use utility](#usage)
- [Sample Output](#license)

## Installation

Service requires that apache maven 3+ be installed. It also requires a running docker instance locally on your unix machine

1. install apache maven from here unto your unix machine: https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-mac-os-x
2. install docker to your unix machine here: https://docs.docker.com/docker-for-mac/install/
3. install java 11 on your unix machine as seen here https://java.tutorials24x7.com/blog/how-to-install-java-11-on-mac


## Setup

Getting service ready for execution...

### Parameters

The following configuration represent the default and need not be changed to start the service.

```yaml
spring:
  redis:
    password: ${ENV_REDIS_PASSWORD:MDNcVb924a}
    port: ${ENV_REDIS_PORT:6379}
    host: ${ENV_REDIS_HOST:localhost}
    timeout: ${ENV_REDIS_TIMEOUT:60000}
  datasource:
    url: 'jdbc:postgresql://${ENV_DATASOURCE_DB_HOST:localhost}:${ENV_DATASOURCE_DB_PORT:5438}/${ENV_DATABASE:postgres}?useLegacyDatetimeCode=false'
    username: ${ENV_DATASOURCE_USERNAME:postgres}
    password: ${ENV_DATASOURCE_SECRET:postgres}
    driver-class-name: 'org.postgresql.Driver'
```
### How to use utility

Running this service is as easy as 1.. 2.. 3..

1. using your command line window, navigate to the '_deployment' folder in the root of this project and execute the following command:

```bash
chmod +x build_promote.sh && ./build_promote.sh
```
###### note: command above will a. run automated tests b. build jar c. build and execute app image in docker (really simple)

2. confirm application's docker container is running:

```bash
docker ps -a 
```
###### note: should print image with a name 'truelayer/pokemon-information-service:latest' running under the name 'pokemon-information-service'

3. confirm application is running

```bash
curl http://localhost:50000/health
```

should return a 200 OK message with response body as below:

```json
{"status":"UP"}
```

4. call the api endpoint as so:

```bash
curl 'http://localhost:50000/pokemon/pikachu'
```

## Sample Output

```json
{
  "name": "pikachu",
  "description": "Whenever pikachu cometh across something new,  't blasts 't with a jolt of electricity. If 't be true thee cometh across a blackened berry,  't?s evidence yond this pok?mon did misprision the intensity of its charge."
}
```

### Things to know

1. application calls the free endpoint https://api.funtranslations.com for text translation to shakespearean form (which is rate-limited to 5 calls per hour after which it returns status code 429), as such we cache responses from http://localhost:50000/pokemon/pikachu to limit calls to https://api.funtranslations.com.
2. to get pokemon specie information we restrict the game filter to 'ruby' by default. this can be changed in the application yaml (see 'game_version_name' config above) or set as an environment variable.
3. we return the untranslated text where too many calls to the unlicensed endpoint https://api.funtranslations.com has occurred.

### Things to do in the future

1. get paid license to https://api.funtranslations.com and update HttpClient to pass api key in http header 'X-FunTranslations-Api-Secret' to this endpoint.
2. use configurable timeouts for http clients.
3. pass game version to application api e.g. red or ruby
4. publish api contract.
5. push prometheus metrics to grafana dashboard