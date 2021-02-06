package com.zpy.rpc.rpcprovider;

import com.zpy.rpc.rpcrequest.RpcRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 20:32
 */
public class ProcessorHandler implements Runnable{
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    // 服务提供者实现的方法对象
    private Object obj;

    public ProcessorHandler(Socket socket,Object obj) {
        this.socket = socket;
        this.obj = obj;
    }

    public void run() {
        try {
            //获取Socket的输入流
            InputStream inputStream = socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            try {
                //接收数据类型 RpcRequest
                RpcRequest request = (RpcRequest)objectInputStream.readObject();
                try {
                    // 反射调用对象
                    Object res = invoke(request,obj);
                    //返回结果
                    OutputStream outputStream = socket.getOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(res);
                    // 清空缓存区
                    objectOutputStream.flush();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                finally {
                    if (objectOutputStream != null){
                        objectOutputStream.close();
                    }
                    if (objectInputStream != null){
                        objectInputStream.close();
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 通过反射获取并调用 实现方法 Impl
    private Object invoke(RpcRequest request,Object impl) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取方法的参数
        Object[] args = request.getParameters();
        //获取参数类型
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        // 反射获取对象
        Object clazz = Class.forName(request.getClassName());
        //反射获取方法
        Method method = ((Class) clazz).getMethod(request.getMethodName(),types);
        // 反射调用方法
        Object result = method.invoke(impl, args);
        return result;
    }
}
