package com.zpy.rpc.zkconfig;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/7 11:04
 * 10.105.94.14:2181
 */
public class ZKUtil {

    private static final String ZK_SERVER_URL = "10.105.94.14:2181";
    private static ZkClient zkClient = null  ;

    static{
        zkClient = new ZkClient(ZK_SERVER_URL);
        System.out.println("zk已经连接成功");
    }

    public static void main(String[] args) {
//        createNode();
       //deleteNode();
        //getSubNode();
        //subscribe();
//        subscribe();
        deleteAll();
    }

    public static void deleteAll(){
        System.out.println(zkClient.deleteRecursive("/zpy-rpc"));
        System.out.println("删除成功");
    }
    /**
     * 1 节点的创建
     */

    public static void createNode(){
        /**
         * 节点的类型：4 种
         *  a：顺序的节点（节点后面带有数字的标识）  无序的节点
         *  b：持久的 （创建后一直存在）临时的（创建它的客户端断开连接后，它自动删除）
         *
         */
        zkClient.createPersistent("/0812");
        zkClient.createEphemeral("/rpc");
        zkClient.createPersistentSequential("/0812","xxx");
        zkClient.createEphemeralSequential("/rpc","xxxx");
        System.out.println("创建完成！");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 2 节点的删除
     */
    public static void deleteNode(){
        boolean delete = zkClient.delete("/08120000000002");
        if (delete){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
    public static void creat(){
        zkClient.createEphemeral("/com.sxt.service.AddService/localhost:8866");
    }
    /**
     * 3 获取子节点
     */
    public  static void getSubNode(){
        zkClient.createPersistent("/com.sxt.service.AddService");
        zkClient.createEphemeral("/com.sxt.service.AddService/localhost:8888");
        zkClient.createEphemeral("/com.sxt.service.AddService/localhost:9999");
        zkClient.createEphemeral("/com.sxt.service.AddService/localhost:7777");
        System.out.println("节点创建完毕");
        List<String> childrens = zkClient.getChildren("/com.sxt.service.AddService");
        if(childrens!=null && !childrens.isEmpty()){
            childrens.forEach((k)-> System.out.println(k));
        }
    }
    /**
     * 4 节点的订阅
     */
    public static void  subscribe(){
        // 订阅子节点的改变？
        /**
         * 当父节点里面有子节点删除了，或者新增了，都会触发监听器
         */
        zkClient.subscribeChildChanges("/com.sxt.service.AddService", new IZkChildListener() {

            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("父节点：/com.sxt.service.AddServic,的子节点有改变");
                System.out.println("当前最新的父节点为:"+currentChilds);
            }
        }) ;
        System.out.println("开始监听");
        try {
            System.in.read() ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
