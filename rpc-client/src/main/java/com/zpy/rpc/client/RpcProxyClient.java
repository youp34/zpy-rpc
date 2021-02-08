package com.zpy.rpc.client;

import com.zpy.rpc.register.RegisterDiscovery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Proxy;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 9:35
 * 生成代理对象
 */
public class RpcProxyClient {
    public <T> T proxyClient(final Class<T> interfaceObj,final ApplicationContext applicationContext){
        return (T) Proxy.newProxyInstance
                (interfaceObj.getClassLoader(),new Class<?>[]{interfaceObj},new RemotInvocationHandler(applicationContext));
    }
}
