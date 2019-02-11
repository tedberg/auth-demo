#!/usr/bin/env bash

dockerRepository=triview-docker

if [ "$#" -lt 1 ]; then
    echo "docker-push.sh artifactVersion"
    exit -1
fi

moduleName=server
appName=auth-demo-${moduleName} # Could be passed
fullDockerImageName=${dockerRepository}/${appName} # Could be passed
artifactVersion=$1 # Passed from maven

artifact="./target/${moduleName}-${artifactVersion}.jar"

if [[ "$OSTYPE" == "darwin"* ]]; then
   digest=$(md5 -r ${artifact} Dockerfile | md5 -r | cut -f1 -d" ")
else
   digest=$(md5sum ${artifact} Dockerfile | md5sum | cut -f1 -d" ")
fi

# Tag image locally
docker tag ${fullDockerImageName}:latest ${fullDockerImageName}:${artifactVersion}
docker tag ${fullDockerImageName}:latest ${fullDockerImageName}:${digest}

docker push ${fullDockerImageName}:latest
docker push ${fullDockerImageName}:${digest}
docker push ${fullDockerImageName}:${artifactVersion}

# expose digest which can be captured by publish script
rm ./target/digest.txt
echo ${digest} >> ./target/digest.txt
