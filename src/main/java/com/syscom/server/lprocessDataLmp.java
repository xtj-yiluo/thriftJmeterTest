package com.syscom.server;

import com.syscom.thrift.lprocessData;
import org.apache.thrift.TException;

/**
 * @author xtj
 * @date 2021/2/23 14:14
 */
public class lprocessDataLmp  implements lprocessData.Iface{

// 实现接口 ，自定义逻辑
    @Override
    public String processData(String para) throws TException {

        System.out.println(para);

        return "success";
    }
}
