package com.zpy.rpc.rpcprovider;

import com.zpy.rpc.annotation.RpcServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 14:36
 *
 * 此类在之前的基础上支持了接口版本号设定
 * 支持注解化注册服务
 */
public class RpcProxyServerSpring implements ApplicationContextAware, InitializingBean {
    //服务端口
    private int port;

    //map<版本号＋类名，服务名>
    private Map<String,Object> handlerMap = new HashMap();

    public RpcProxyServerSpring(int port) {
        this.port = port;
    }

    /**
     * 当一个类实现这个接口之后，Spring启动时，初始化Bean时，若该Bean实现InitializingBean接口，
     * 则会自动调用afterPropertiesSet()方法，完成一些用户自定义的初始化操作。
     */
    public void afterPropertiesSet() throws Exception {
        //创建线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                pool.execute(new ProcessorHandlerSpring(socket,handlerMap));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (pool != null){
                pool.shutdown();
            }
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Spring发现某个Bean实现了ApplicationContextAware接口，Spring容器会在创建该Bean之后，
     * 自动调用该Bean的setApplicationContextAware()方法，调用该方法时，会将容器本身ApplicationContext对象作为参数传给该方法。
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //获取所有用@RpcServer注解修饰的类
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcServer.class);
        if (!beansWithAnnotation.isEmpty()){
            //遍历出集合的所有的值
            for (Object serverBean : beansWithAnnotation.values()) {
                //获取注解
                RpcServer rpcServer = serverBean.getClass().getAnnotation(RpcServer.class);
                //获取类名
                String serverName = rpcServer.value().getName();
                //rpcServer.value().getName() == com.zpy.rpc.api.HelloRpc
                //rpcServer.value() == interface com.zpy.rpc.api.HelloRpc
                //获取注解中的版本号
                String serverVersion = rpcServer.version();
                if (!StringUtils.isEmpty(serverName)){
                    serverName+="-"+serverVersion;
                }
                handlerMap.put(serverName,serverBean);
            }
        }

    }
}
