package com.zpy.rpc.client;

import com.zpy.rpc.config.SpringConfig;
import com.zpy.rpc.loadbalance.LoadBalanceUtil;
import com.zpy.rpc.register.RegisterDiscovery;
import com.zpy.rpc.rpcrequest.RpcRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 9:47
 */
public class RemotInvocationHandler implements InvocationHandler{
    private ApplicationContext applicationContext = null;


    public RemotInvocationHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameters(args);
        request.setVersion("v1.0");

        //System.out.println("method.getDeclaringClass().getName() = "+method.getDeclaringClass().getName());

        String serverName=method.getDeclaringClass().getName()+"-"+request.getVersion();
        //负载均衡
        LoadBalanceUtil loadBalanceUtil = applicationContext.getBean(LoadBalanceUtil.class);
        String s = loadBalanceUtil.loadBalance(serverName);
        String[] data = s.split(":");
        RpcNetTransport rpcNetTransport = new RpcNetTransport(data[0],Integer.parseInt(data[1]));
        Object result = rpcNetTransport.send(request);
        return result;
    }
}
