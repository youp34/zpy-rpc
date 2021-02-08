package com.zpy.rpc;

import com.zpy.rpc.api.HelloRpc;
import com.zpy.rpc.client.RpcProxyClient;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 9:50
 */
public class ClientMain {
    public static void main(String[] args) {
        System.out.println("服务消费者使用");
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        //HelloRpc helloRpc = rpcProxyClient.proxyClient(HelloRpc.class,"127.0.0.1",8866);
        //System.out.println(helloRpc.sayHello("hello zpy!"));
    }
}
