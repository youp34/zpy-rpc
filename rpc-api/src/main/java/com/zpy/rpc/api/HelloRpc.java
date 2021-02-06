package com.zpy.rpc.api;

import com.zpy.rpc.User;

public interface HelloRpc {
    String sayHello(String content);
    String saveUser(User user);
}
