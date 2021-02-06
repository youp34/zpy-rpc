package com.zpy.rpc;

import com.zpy.rpc.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 16:21
 */
public class SpringProviderMain {
    public static void main(String[] args) {
        System.out.println("服务端启动               ");
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) context).start();
    }
}
