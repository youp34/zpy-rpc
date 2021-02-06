package com.zpy.rpc.config;

import com.zpy.rpc.client.RpcProxyClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 17:06
 */
@Configuration
public class SpringConfig {
    @Bean(name = "rpcProxyClient")
    public RpcProxyClient rpcProxyClient(){
        return new RpcProxyClient();
    }
}
