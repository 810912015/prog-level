FROM pl-base:7
RUN mkdir /data
RUN mkdir /data/runner
WORKDIR /data
COPY ./target/portal-1.0-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["nohup","java","-jar","./app.jar",">log.file", "2>&1", "&"]