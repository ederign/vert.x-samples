#!/usr/bin/env bash
mvn package
java -jar target/javaone-rest-api-1.0-SNAPSHOT-fat.jar -Dhttp.port=$1