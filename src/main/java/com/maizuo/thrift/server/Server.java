package com.maizuo.thrift.server;

import com.maizuo.config.ServerConfig;
import com.maizuo.thrift.thrift.CinemaService;
import com.maizuo.thrift.process.CinemaProcess;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by Administrator on 2016/7/15.
 */
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
