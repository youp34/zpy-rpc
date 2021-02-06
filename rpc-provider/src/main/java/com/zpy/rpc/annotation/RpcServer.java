package com.zpy.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 14:15
 */
@Target(ElementType.TYPE) //作用于类接口
@Retention(RetentionPolicy.RUNTIME) // 生命周期
@Component   //被spring进行扫描
public @interface RpcServer {
    //存储服务的接口
    Class<?> value();
    // 存储版本号
    String version() default "";
}
