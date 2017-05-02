## 服务器部署

**启动 Vertx**

vertx run io.vertPress.weixin.WeixinServer -cp vertx-weixin-0.0.1.jar&

**端口转发**

iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8081