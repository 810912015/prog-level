### 注意点
docker build -t reader-admin .
docker run --name ra -e "spring.profiles.active=prodhttps" reader-admin

基础镜像是centos+yum install java-1,8.0-openjdk.x86_64

push到ucloud的镜像仓库

所有应用使用容器承载

rbtmq新增队列后需要界面中添加队列的binding

###翻译服务的实现

1.使用express架设翻译服务，代码在ppt项目中，部署在ip:7070/translate;

2.翻译流程：获取文本-》调用google翻译;

3.交互流程：用户界面输入url等
           ->java后台判断是否可翻译
           ->调用node实现的翻译服务
           ->翻译完成后回调java后台
           ->java后台保存数据，用户可通过界面编辑翻译后的内容。