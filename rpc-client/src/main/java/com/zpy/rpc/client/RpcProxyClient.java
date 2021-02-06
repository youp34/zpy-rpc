package com.zpy.rpc.client;

import java.lang.reflect.Proxy;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 9:35
 * 生成代理对象
 */
public class RpcProxyClient {
    public <T> T proxyClient(final Class<T> interfaceObj,final String host,final int port){
        return (T) Proxy.newProxyInstance
                (interfaceObj.getClassLoader(),new Class<?>[]{interfaceObj},new RemotInvocationHandler(host,port));
    }
}
