docker build -t reader-admin .
docker run --name ra -e "spring.profiles.active=prodhttps" reader-admin

基础镜像是centos+yum install java-1,8.0-openjdk.x86_64

push到ucloud的镜像仓库

所有应用使用容器承载

rbtmq新增队列后需要界面中添加队列的binding