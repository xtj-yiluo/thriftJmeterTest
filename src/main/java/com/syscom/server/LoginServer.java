package com.syscom.server;

import com.syscom.thrift.ILogin;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 *
 * 相当于后台一个进程服务
 * 一直处于运行状态，一直监听前端的请求，
 * 这个服务是否死循环
 *
 * 这个服务将默认调用用户写的代码
 * 底层还是 是多线程 线程池的处理，socket 传输
 *
 */
public class LoginServer {
    public static int SERVER_PORT = 8899 ;
    public static String SERVER_IP = "localhost" ;


    public void start(){

        try {
            //定义服务传输服务方式,及接受前端请求的服务方式，顺带把端口定义好

            TNonblockingServerSocket socket = new TNonblockingServerSocket(SERVER_PORT) ;

            //定义服务
            THsHaServer.Args args = new THsHaServer.Args(socket) ;

            //处理进程->定义处理进程，将关联你自己写的your core code
            TProcessor process = new ILogin.Processor<LoginImp>(new LoginImp()) ;
            //ILogin.Processor process = new ILogin.Processor(new LoginImp()) ;

            args.protocolFactory(new TCompactProtocol.Factory()) ;    //压缩传输协议
            args.transportFactory(new TFramedTransport.Factory()) ;  //文件块的传输方式
            args.processorFactory(new TProcessorFactory(process)) ;  //定义传输服务->传输方法->传输进程—>指向你实现的具体的方法

            THsHaServer server = new THsHaServer(args) ; // 一定要与TFramedTransport 同时使用

            server.serve() ;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void      main(String[] args){

        LoginServer server = new LoginServer() ;
        System.out.println("----------服务端程序-start-ok-------------") ;
        server.start() ;
    }
}
