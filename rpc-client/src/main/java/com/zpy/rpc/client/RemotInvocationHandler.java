package com.zpy.rpc.client;

import com.zpy.rpc.rpcrequest.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 9:47
 */
public class RemotInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemotInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host,port);
        Object result = rpcNetTransport.send(request);
        return result;
    }
}
