### 一. Thrift 简介

> Thrift是一个跨语言的服务部署框架，最初由Facebook于2007年开发，2008年进入Apache开源项目。
> Thrift通过一个中间语言(IDL,接口定义语言)来定义RPC的接口和数据类型，然后通过一个编译器生成不
> 同语言的代码（目前支持C++,Java,Python, PHP, Ruby, Erlang, Perl, Haskell,
> C#, Cocoa, Smalltalk和OCaml）,并由生成的代码负责RPC协议层和传输层的实现。

### 二. Thrift 架构介绍

![](/image/arthitecture2.jpg)

> Thrift实际上是实现了C/S模式，通过代码生成工具将接口定义文件生成服务器端和客户端代码（可以
> 为不同语言），从而实现服务端和客户端跨语言的支持。用户在Thirft描述文件中声明自己的服务，这
> 些服务经过编译后会生成相应语言的代码文件，然后用户实现服务（客户端调用服务，服务器端提服务）
> 便可以了。其中protocol（协议层,定义数据传输格式，可以为二进制或者XML等）和transport（传输
> 层，定义数据传输方式，可以为TCP/IP传输，内存共享或者文件共享等）被用作运行时库。

### 三. 支持的数据传输格式、数据传输方式和服务模型

3.1. 支持的传输格式

- TBinaryProtocol – 二进制格式.
- TCompactProtocol – 压缩格式
- TJSONProtocol – JSON格式
- TSimpleJSONProtocol –提供JSON只写协议,
  生成的文件很容易通过脚本语言解析。
- TDebugProtocol – 使用易懂的可读的文本格式，以便于debug

3.2. 支持的数据传输方式

- TSocket -阻塞式socker
- TFramedTransport – 以frame为单位进行传输，非阻塞式服务中使用。
- TFileTransport – 以文件形式进行传输。
- TMemoryTransport – 将内存用于I/O.
  java实现时内部实际使用了简单的ByteArrayOutputStream。
- TZlibTransport – 使用zlib进行压缩，
  与其他传输方式联合使用。当前无java实现。

3.3. 支持的服务模型

- TSimpleServer – 简单的单线程服务模型，常用于测试
- TThreadPoolServer – 多线程服务模型，使用标准的阻塞式IO。
- TNonblockingServer –
  多线程服务模型，使用非阻塞式IO（需使用TFramedTransport数据传输方式）

### 四. Thrift IDL 文件介绍

#### 1. IDL 文件示例(thrift_demo.thrift)

```c
namespace java com.maizuo.thrift
struct Cinema {
    1:i32 cinemaId;
    2:string cinemaName;
    3:bool isUse;
    4:i32 hallCount;
}
service CinemaService {
    list<Cinema> getCinemas(1:i32 cinemaId , 2:i32 channelId);
}

```

#### 2. 基本类型

thrift不支持无符号类型，因为很多编程语言不存在无符号类型，比如java

- byte: 有符号字节
- i16: 16位有符号整数
- i32: 32位有符号整数
- i64: 64位有符号整数
- double: 64位浮点数
- string: 字符串

#### 3. 容器类型

集合中的元素可以是除了service之外的任何类型，包括exception。

- list<T>: 一系列由T类型的数据组成的有序列表，元素可以重复
- set<T>: 一系列由T类型的数据组成的无序集合，元素不可重复
- map<K, V>: 一个字典结构，key为K类型，value为V类型，相当于Java中的HMap<K,V>

#### 4. 结构体(struct)

就像C语言一样，thrift也支持struct类型，目的就是将一些数据聚合在一起，方便传输管理。
struct的定义形式如下：

```c
struct People {
     1: string name;
     2: i32 age;
     3: string sex;
}
```

#### 5. 枚举(enum)

枚举的定义形式和Java的Enum定义差不多，例如：

```c
enum Sex {
    MALE,
    FEMALE
}
```

#### 6. 异常(exception)

thrift支持自定义exception，规则和struct一样，如下：

```c
exception RequestException {
    1: i32 code;
    2: string reason;
}
```

#### 7. 服务(service)

thrift定义服务相当于Java中创建Interface一样，创建的service经过代码生成命令之后就会生成
客户端和服务端的框架代码。定义形式如下：

```c
service HelloWordService {
     // service中定义的函数，相当于Java interface中定义的函数
     string doAction(1: string name, 2: i32 age);
 }
```

#### 8. 类型定义

thrift支持类似C++一样的typedef定义，比如：

typedef i32 Integer typedef i64 Long 注意，末尾没有逗号或者分号

#### 9. 常量(const)

thrift也支持常量定义，使用const关键字，例如：

const i32 MAX_RETRIES_TIME = 10 const string MY_WEBSITE =
"http://qifuguang.me"; 末尾的分号是可选的，可有可无，并且支持16进制赋值

#### 10. 命名空间

thrift的命名空间相当于Java中的package的意思，主要目的是组织代码。thrift使用关键字
namespace定义命名空间，例如：

namespace java com.winwill.thrift 格式是：namespace 语言名 路径，
注意末尾不能有分号。

#### 11. 文件包含

thrift也支持文件包含，相当于C/C++中的include，Java中的import。使用关键字include定义，例如：

include "global.thrift"

#### 12. 注释

thrift注释方式支持shell风格的注释，支持C/C++风格的注释，即#和//开头的语句都单当做注释，
/**/包裹的语句也是注释。

#### 13. 可选与必选

thrift提供两个关键字required，optional，分别用于表示对应的字段时必填的还是可选的。例如：

```c
struct People {
    1: required string name;
    2: optional i32 age;
}
```

表示name是必填的，age是可选的。

### 五. 生成代码

依据上面提到的demo项目的thrift文件
```c
namespace java com.maizuo.thrift
struct Cinema {
    1:i32 cinemaId;
    2:string cinemaName;
    3:bool isUse;
    4:i32 hallCount;
}
service CinemaService {
    list<Cinema> getCinemas(1:i32 cinemaId , 2:i32 channelId);
}

```
在该文件的同级目录下，执行命令，生成代码
thrift --gen java thrift_demo.thrift

生成代码的目录结构：

![](/image/tree.png)

### 六. 使用介绍
#### 1. 项目构建
- 以thrift_demo为例，将生成的代码复制到包`com.maizuo.thrift.thrift`
- 引入thrift的依赖

```xml
<dependency>
    <groupId>org.apache.thrift</groupId>
    <artifactId>libthrift</artifactId>
    <version>0.6.1</version>
</dependency>
```

#### 2. 实现定义的接口

根据thrift_demo.thrift 定义的接口list<Cinema> getCinemas(1:i32 cinemaId , 2:i32 channelId)，
完成实现类，可参考类`com.maizuo.thrift.process.CinemaProcess`,示例如下

```java
public class CinemaProcess implements CinemaService.Iface {
    @Override
    public List<Cinema> getCinemas(int cinemaId, int channelId) throws TException {
        List<Cinema> cinemas = new ArrayList<Cinema>();
        List<Boolean> booList = new ArrayList<Boolean>();
        booList.add(true);
        booList.add(false);
        for (int i = 0; i <= 10; i++) {
            Cinema cinema = new Cinema();
            cinema.setCinemaId(i + 10000);
            cinema.setCinemaName("中影夏日国际" + i);
            cinema.setIsUse(booList.get(new Random().nextInt(1)));
            cinema.setHallCount(i + 2);
            cinemas.add(cinema);
        }
        return cinemas;
    }
}
```

#### 3. 服务端

- 服务端编写步骤：
    1. 定义一个TProcess，这个是thrift根据用户定义的thrift文件自动生成的类
    2. 使用TServerTransport获得一个TTransport
    3. 使用TTransportFactory，可选地将原始传输转换为一个适合的应用传输（典型的是使用TBufferedTransportFactory）
    4. 使用TProtocolFactory，为TTransport创建一个输入和输出
    5. 创建TServer对象（单线程，可以使用TSimpleServer；对于多线程，用户可使用TThreadPoolServer或者
            TNonblockingServer），调用它的server()函数。

```java
public class Server {
    public static void main(String[] arg) {
        initConfig();
        new Server().startPoolServer();
    }

    /**
     * 多线程服务器端使用标准的堵塞式I/O
     */
    private void startPoolServer()
    {
        try {
            int port = Integer.parseInt(ServerConfig.getProperty("user.serverPort"));
            TServerSocket serverTransport = new TServerSocket(port);
            CinemaService.Processor process = new CinemaService.Processor(new CinemaProcess());
            TBinaryProtocol.Factory portFactory = new TBinaryProtocol.Factory(true,true);
            TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport);
            args.processor(process);
            args.protocolFactory(portFactory);
            args.maxWorkerThreads = Integer.parseInt(ServerConfig.getProperty("user.maxWorkerThreads"));
            args.minWorkerThreads = Integer.parseInt(ServerConfig.getProperty("user.minWorkerThreads"));
            TServer server = new TThreadPoolServer(args);

            System.out.println("CinemaServer is startup success!");
            server.serve();

        } catch (TTransportException e)
        {
            e.printStackTrace();
        }
    }

    private static void initConfig()
    {
        try {
            Class.forName("com.maizuo.config.ServerConfig");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
```


#### 4.客户端（基于com.maizuo.thriftpool，封装的线程池）

- Client编写的方法分为以下几个步骤：
    1. 定义TTransport，为你的client设置传输方式（如socket， http等）。
    2. 定义Protocal，使用装饰模式（Decorator设计模式）封装TTransport，为你的数据设置编码格式（如二进制格式，
            JSON格式等）
    3. 实例化client对象，调用服务接口。


- 引入依赖

```xml
<dependency>
    <groupId>com.maizuo</groupId>
    <artifactId>thriftpool</artifactId>
    <version>0.0.1</version>
</dependency>
```

- 填写配置文件（thrift.cfg.xml）

```xml
<bean id="CinemaSocketPool" class="com.maizuo.thirft.pool.ThriftSocketPool">
    <property name="serviceAddress">
     <map>
       <entry key="192.168.1.63">
       <value>11006</value>
       </entry>  
       <!-- 127.0.0.1  -->   
     </map>
    </property>
    <property name="maxActive" value="1000" />
    <property name="maxIdle" value="50" />
    <property name="testWhileIdle" value="true" />
    <property name="conTimeOut" value="10000" />
</bean>
```

- 封装示例

```java
public class CinemaClient {
    public CinemaService.Client getClient (TTransport transport){
        try {
            TProtocol protocol = new TBinaryProtocol(transport);
            CinemaService.Client client = new CinemaService.Client(protocol);
            if(!transport.isOpen()){
                transport.open();
            }
            return client;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    public void closeTransport(TTransport transport){
        if(transport != null){
            ThriftPoolProxy.returnThriftCon("CinemaSocketPool", transport);
        }
    }

    public List<Cinema> getCinemas(int cinemaId , int channelId) {
        TTransport socket = ThriftPoolProxy.getThriftCon("CinemaSocketPool");
        if (socket == null) {
            return null;
        }
        CinemaService.Client client = this.getClient(socket);
        try {
            return client.getCinemas(cinemaId, channelId);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                this.closeTransport(socket);
            }
        }
        return null;
    }
}

```

### 七.  Thrift 生成代码说明

Thrift 网络栈如下图：

![](/image/thrift.jpg)

#### 1. TTransport
TTransport层提供了一个简单的网络读写抽象层。这使得thrift底层的transport从系统其它部分（如：序列化/反序列化）解耦。
以下是一些Transport接口提供的方法：

- open
- close
- read
- write
- flush

除了以上几个接口，Thrift使用ServerTransport接口接受或者创建原始transport对象。正如名字暗示的那样，ServerTransport
用在server端，为到来的连接创建Transport对象。

- open
- listen
- accept
- close

该类负责数据传输方式，常见的实现类

- TFileTransport：文件（日志）传输类，允许client将文件传给server，允许server将收到的数据写到文件中。
- THttpTransport：采用Http传输协议进行数据传输
- TSocket：采用TCP Socket进行数据传输
- TZlibTransport：压缩后对数据进行传输，或者将收到的数据解压

下面几个类主要是对上面几个类地装饰（采用了装饰模式），以提高传输效率。
- TBufferedTransport：对某个Transport对象操作的数据进行buffer，即从buffer中读取数据进行传输，或者将数据直接写入buffer
- TFramedTransport：同TBufferedTransport类似，也会对相关数据进行buffer，同时，它支持定长数据发送和接收。
- TMemoryBuffer：从一个缓冲区中读写数据

#### 2. Protocol
Protocol抽象层定义了一种将内存中数据结构映射成可传输格式的机制。换句话说，Protocol定义了datatype怎样使用底层的
TTransport对自己进行编解码。因此，Protocol的实现要给出编码机制并负责对数据进行序列化。
Protocol接口的定义如下：

- writeMessageBegin(name, type, seq)
- writeMessageEnd()
- writeStructBegin(name)
- writeStructEnd()
- writeFieldBegin(name, type, id)
- writeFieldEnd()
- writeFieldStop()
- writeMapBegin(ktype, vtype, size)
- writeMapEnd()
- writeListBegin(etype, size)
- writeListEnd()
- writeSetBegin(etype, size)
- writeSetEnd()
- writeBool(bool)
- writeByte(byte)
- writeI16(i16)
- writeI32(i32)
- writeI64(i64)
- writeDouble(double)
- writeString(string)
- name, type, seq = readMessageBegin()
- readMessageEnd()
- name = readStructBegin()
- readStructEnd()
- name, type, id = readFieldBegin()
- readFieldEnd()
- k, v, size = readMapBegin()
- readMapEnd()
- etype, size = readListBegin()
- readListEnd()
- etype, size = readSetBegin()
- readSetEnd()
- bool = readBool()
- byte = readByte()
- i16 = readI16()
- i32 = readI32()
- i64 = readI64()
- double = readDouble()
- string = readString()

负责数据编码，主要有以下几个可用类：
- TBinaryProtocol：二进制编码
- TJSONProtocol：JSON编码
- TCompactProtocol：密集二进制编码
- TDebugProtocol：以用户易读的方式组织数据

#### 3. Processor
Processor封装了从输入数据流中读数据和向数据数据流中写数据的操作。读写数据流用Protocol对象表示。Processor的结构体非常
简单:

interface TProcessor {

bool process(TProtocol in, TProtocol out) throws TException

}

与服务相关的processor实现由编译器产生。Processor主要工作流程如下：从连接中读取数据（使用输入protocol），将处理授权
给handler（由用户实现），最后将结果写到连接上（使用输出protocol）。

#### 4. Server
Server将以上所有特性集成在一起：
    1. 创建一个transport对象
    2. 为transport对象创建输入输出protocol
    3. 基于输入输出protocol创建processor
    4. 等待连接请求并将之交给processor处理

常见可用类

- TSimpleServer：简单的单线程服务器，主要用于测试
- TThreadPoolServer：使用标准阻塞式IO的多线程服务器
- TNonblockingServer：使用非阻塞式IO的多线程服务器，TFramedTransport必须使用该类型的server










