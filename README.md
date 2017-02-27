> 该项目是一个thrift实例demo，所用thrift的版本为0.6.1

### 使用

- 配置服务端 配置服务端ip和端口号(thrift.cfg.xml)

```xml
<map>
       <entry key="192.168.1.63">
       <value>11006</value>
       </entry>  
       <!-- 127.0.0.1  -->   
</map>
```

- 启动服务端: com.maizuo.thrift.server.Server.main()
- 客户端测试 com.maizuo.thrift.test.CinemaClientTest.main()

### 说明

- 包 com.maizuo.thrift.thrift 里面的代码都是来自thrift自动生成的
- 文件 thrift_demo.thrift 是thrift的IDL文件，代码生成的依据
- 包 com.maizuo.thrift.client
  里面的代码是封装的客户端，它依赖com.maizuo.thriftpool为它提供连接服务端的线程池
- 正常情况下，服务端和客户端是分开的，但是它们都需要包含thrift生成的代码


**更多详细说明参见本项目下的thrift教程**

[thrift教程](http://192.168.1.206:3000/harvey/thrift_demo/src/6f9c4088e324011d8b98cba5f6342784d4e87052/thrift%E6%95%99%E7%A8%8B.md)




