#分码网

1.语言执行系统

  通过执行命令实现：将源码保存，然后通过命令进行编译，执行，最后获取执行结果。
  
2.多页支持
  
  依赖于react-app-rewire-multiple-entry，添加pulbic/下的模板->src下添加文件夹->config-overrides里添加入口
  参考：http://blog.joylau.cn/2019/12/26/React-App-Rewired/

#注意点

1.ingress发布时要修改public/ngconf.txt中的server ip为实际ip（当前ip是本机ip）。

#发布点

1. rabbitmq

  a.docker run -d --hostname my-rabbit --name rabbit1 -p 5671:5671 -p 5672:5672 -p 15672:15672 rabbitmq:3-management
  
  b.http://localhost:15672/#/queues/%2F/pl-req
  
  c.https://hub.docker.com/_/rabbitmq
  
  d.There is a second set of tags provided with the management plugin installed and enabled by default,
   which is available on the standard management port of 15672, with the default username and password of guest / guest.