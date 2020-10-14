# sbt-native-packager-playground
Testing out the sbt-native-packager


## issues
Seems like some settings won't take effect.  
The generated Dockerfile seems to be using default values for some settings

steps to re-create:
```bash
sbt docker:stage
```
inspect the file `target/docker/stage/Dockerfile`

On my Mac it produces this result.
Missing labels, exposed port, totally wrong base image, etc... 
````
FROM openjdk:8 as stage0
LABEL snp-multi-stage="intermediate"
LABEL snp-multi-stage-id="4684b403-277e-4988-a9ca-e8dd2dd5c94c"
WORKDIR /opt/docker
COPY 1/opt /1/opt
COPY 2/opt /2/opt
USER root
RUN ["chmod", "-R", "u=rX,g=rX", "/1/opt/docker"]
RUN ["chmod", "-R", "u=rX,g=rX", "/2/opt/docker"]
RUN ["chmod", "u+x,g+x", "/1/opt/docker/bin/sbt-native-packager-playground"]

FROM openjdk:8 as mainstage
LABEL MAINTAINER="Peter Nerg"
WORKDIR /opt/docker
COPY --from=stage0 --chown=daemon:daemon /1/opt/docker /opt/docker
COPY --from=stage0 --chown=daemon:daemon /2/opt/docker /opt/docker
USER daemon
ENTRYPOINT ["/opt/docker/bin/sbt-native-packager-playground"]
CMD []

````