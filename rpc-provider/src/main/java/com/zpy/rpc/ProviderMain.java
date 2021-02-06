package com.zpy.rpc;

import com.zpy.rpc.rpcprovider.RpcProxyServer;
import com.zpy.rpc.serviceImpl.HelloRpcImpl;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 23:00
 */
public class ProviderMain {
    public static void main(String[] args) {
        //注册对象
        System.out.println("服务端开启！");
        HelloRpcImpl helloRpc = new HelloRpcImpl();
        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(8866,helloRpc);
    }
}
