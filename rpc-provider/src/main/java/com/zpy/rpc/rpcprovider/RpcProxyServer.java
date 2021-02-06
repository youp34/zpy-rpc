package com.zpy.rpc.rpcprovider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 20:11
 * 实现服务调用 基于socket
 */
public class RpcProxyServer {
    ExecutorService pool = Executors.newCachedThreadPool();
    public void publisher(int port,Object obj){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                pool.execute(new ProcessorHandler(socket,obj));
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
}
