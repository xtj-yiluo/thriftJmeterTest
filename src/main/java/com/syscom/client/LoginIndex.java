package com.syscom.client;

import com.syscom.thrift.ILogin;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

public class LoginIndex {

    private static int SERVER_PORT = 8899 ;
    private static String SERVER_IP = "localhost" ;

    /**
     *
     * 前台登录方法，将调用远程服务器的方法
     *
     * @param username
     */
    public String loginByName(String username){

        String rtn = "" ;
        TFramedTransport transport = null ;
        try {

            //定义传输入方式，要与后台保持一持,也即是这儿定义 传到什么 ip 哪个端口

            transport = new TFramedTransport(new TSocket(SERVER_IP,SERVER_PORT),1000) ;


            //定义传输入协议，压缩方式

            TCompactProtocol protocol = new TCompactProtocol(transport) ;


            //调用后台的方法 Client 方法了,将协议和传输方式，传入到 后台的 Client 方法 注册
            //注册后，那么前台就把后台实现的类已经实例化，拿到前台了
            //把后台的方法拿过来

            ILogin.Client client = new ILogin.Client(protocol) ;


            //开始传输，就是socket 传输，打开流

            transport.open();

            //现在就可以直接调用后台你的方法了,像调用本地的方法一样

            rtn = client.login(username) ;

        } catch (Exception e) {
            e.printStackTrace();
            rtn = "" ;
        } finally {
            transport.clear() ;
            transport.close() ;
        }
        return  rtn ;
    }


    public static void main(String[] args){

        LoginIndex login = new LoginIndex() ;

        String userName = "smile" ;

        for (int i=1;i<10;i++){
            System.out.println("*************开始登录*************");
            System.out.println("用户 "+userName+i+" 开始登录...");
            System.out.println("----------连接后台----------");
            System.out.println();
            System.out.println("----------等待后台响应----------");
            System.out.println();
            String rtn = login.loginByName(userName+i);
            System.out.println("---------后台返回结果--------");
            System.out.println();
            System.out.println("用户"+userName+i+rtn);
            System.out.println("*************登录结束*************");
            try {
                System.out.println();
                Thread.sleep(5000);
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("*************登录异常*************");
                e.printStackTrace();
            }
        }


    }


}
