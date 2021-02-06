package com.zpy.rpc.config;

import com.zpy.rpc.rpcprovider.RpcProxyServerSpring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 14:25
 */
@Configuration
@ComponentScan(basePackages = "com.zpy.rpc")
public class SpringConfig {
    /**
     * 注册Bean
     * 相当于启动 服务提供者的服务
     * @return
     */
    @Bean(name="RpcProxyServerSpring")
    public RpcProxyServerSpring RpcProxyServerSpring(){
        return new RpcProxyServerSpring(8866);
    }
}
