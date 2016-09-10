#!/usr/bin/env bash
mvn package
java -jar target/javaone-1.0-SNAPSHOT-fat.jar \
  --redeploy="src/**/*.js,src/**/*.java,src/**/*.html,src/**/*.jade" \
  --onRedeploy="./run.sh"