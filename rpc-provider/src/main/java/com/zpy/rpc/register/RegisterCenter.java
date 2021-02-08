package com.zpy.rpc.register;

import org.I0Itec.zkclient.ZkClient;

import java.util.Collections;
import java.util.List;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/7 12:21
 */
public class RegisterCenter {
    private ZkClient zkClient = null ;
    private String SERVER_URL = "10.105.94.14:2181" ;
    private String ZK_REGISTER_ROOT_PATH ="/zpy-rpc";
    public RegisterCenter() {
        System.out.println("----初始化zookeeper----");
        zkClient  = new ZkClient(SERVER_URL);
        if(!zkClient.exists(ZK_REGISTER_ROOT_PATH)){
            zkClient.createPersistent(ZK_REGISTER_ROOT_PATH); // 这句话有异常，当你创建的节点存在时，它会有异常
        }
        System.out.println("----初始化完毕----");
    }

    /**
     * 服务的注册
     * 需要提供：
     *      服务的名称
     *      服务的地址
     */
    public void register(String serviceName ,String sericeAddress){
        if(!zkClient.exists(ZK_REGISTER_ROOT_PATH+"/"+serviceName)){
            zkClient.createPersistent(ZK_REGISTER_ROOT_PATH+"/"+serviceName);
        }
        if(!zkClient.exists(ZK_REGISTER_ROOT_PATH + "/" +serviceName + "/" + sericeAddress)){
            zkClient.createEphemeral(ZK_REGISTER_ROOT_PATH+"/"+serviceName+"/"+sericeAddress);
            System.out.println("服务serviceName，在：" +ZK_REGISTER_ROOT_PATH+ "/"+serviceName+"/"+sericeAddress+ "注册成功");
        }

    }
    /**
     * 服务的发现
     * 使用服务的名称得到服务的地址
     *
     */
    public List<String> discovery(String serviceName){
        if(zkClient.exists(ZK_REGISTER_ROOT_PATH+"/"+serviceName)){
            List<String> childrens = zkClient.getChildren(ZK_REGISTER_ROOT_PATH+"/" + serviceName);
            if(childrens!=null){
                return childrens ;
            }
        }else {
            throw new RuntimeException("没找到对应服务");
        }
        return  Collections.emptyList() ;
    }

}
