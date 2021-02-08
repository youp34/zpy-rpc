package com.zpy.rpc;

import com.zpy.rpc.api.HelloRpc;
import com.zpy.rpc.client.RpcProxyClient;
import com.zpy.rpc.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 17:11
 * 用Spring容器进行管理
 */
public class SpringClientMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient bean = applicationContext.getBean(RpcProxyClient.class);
        HelloRpc helloRpc = bean.proxyClient(HelloRpc.class, applicationContext);
        System.out.println(helloRpc.sayHello("赵朋玉鸭！！"));
        User user = new User();
        user.setAge(22);
        user.setName("赵朋玉");
        System.out.println(helloRpc.saveUser(user));
    }
}
