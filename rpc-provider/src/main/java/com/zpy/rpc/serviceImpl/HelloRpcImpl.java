package com.zpy.rpc.serviceImpl;

import com.zpy.rpc.User;
import com.zpy.rpc.annotation.RpcServer;
import com.zpy.rpc.api.HelloRpc;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 19:34
 * 服务端接口实现
 */
@RpcServer(value = HelloRpc.class,version = "v1.0")
public class HelloRpcImpl implements HelloRpc {
    public String sayHello(String content) {
        System.out.println("Hello RPC! v1.0");
        return content;
    }

    public String saveUser(User user) {
        System.out.println(user);
        return user.toString();
    }
}
