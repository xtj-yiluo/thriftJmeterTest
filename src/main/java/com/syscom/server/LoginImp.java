package com.syscom.server;

import com.syscom.thrift.ILogin;
import org.apache.thrift.TException;

/**
 *
 * 登录方法的具体实现
 *
 */
public class LoginImp implements ILogin.Iface{

    @Override
    public String login(String para) throws TException {
        String rtn = "" ;
        try{

            System.out.println();

            System.out.println(" ************* someone is logging ************* ");

            System.out.println("connect db to check username["+para+"] and password ");

            Thread.sleep(8000);

            int i = Integer.parseInt(para.substring(5,6)) ;
            if(i%2==0){
                rtn = "user ["+para+"] login success. " ;
            }else{
                rtn = "user ["+para+"] login fail!" ;
            }

            System.out.println(rtn);

            System.out.println(" ************* logging done ************* ");

        }catch(Exception e){
            rtn = para+"logging error....."+e.getMessage() ;
            System.out.println(rtn);
        }finally {
            rtn = "登录: " +rtn;
        }

        return rtn ;
    }
}
