FROM 106.75.17.41:3389/rbase
RUN mkdir /data
WORKDIR /data
COPY ./target/reader-admin-1.0-SNAPSHOT.jar ./app.jar
COPY ./2514079__cooee.com.pfx ./
ENTRYPOINT ["nohup","java","-jar","./app.jar",">log.file", "2>&1", "&"]