package com.zpy.rpc.loadbalance;

import com.zpy.rpc.config.SpringConfig;
import com.zpy.rpc.register.RegisterDiscovery;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Random;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/7 13:20
 */
public class LoadBalanceUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext = null;
    private final Random random = new Random();
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String loadBalance(String serverName){
        RegisterDiscovery registerDiscovery = applicationContext.getBean(RegisterDiscovery.class);
        List<String> discovery = registerDiscovery.discovery(serverName);
        String res;
        int i = discovery.size();
        if (i<1){
            throw new RuntimeException("没找到对应服务");
        } else if (i == 1){
            res = discovery.get(0);
        }else {
            res = discovery.get(random.nextInt(i)-1);
        }
        return res;
    }
}
