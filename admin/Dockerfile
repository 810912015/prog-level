FROM pl-base:7
RUN mkdir /data
WORKDIR /data
COPY ./target/admin-1.0-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["nohup","java","-jar","./app.jar",">log.file", "2>&1", "&"]