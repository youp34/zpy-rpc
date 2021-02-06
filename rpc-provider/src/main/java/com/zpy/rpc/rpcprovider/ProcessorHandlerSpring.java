package com.zpy.rpc.rpcprovider;

import com.zpy.rpc.rpcrequest.RpcRequest;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/6 15:31
 */
public class ProcessorHandlerSpring implements Runnable{
    private Socket socket;
    //服务注册表
    private Map serviceMap;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public ProcessorHandlerSpring(Socket socket, Map serviceMap) {
        this.socket = socket;
        this.serviceMap = serviceMap;
    }

    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest)objectInputStream.readObject();
            Object result = invoke(request);
            //写回
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null){
                    objectOutputStream.close();
                }
                if (objectInputStream != null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //反射调用方法
    public Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String serviceName = request.getClassName();
        String version = request.getVersion();
        if (!StringUtils.isEmpty(version)){
            serviceName+="-"+version;
        }
        //上注册表Map中通过键获取值 服务名
        Object service = serviceMap.get(serviceName);
        //判空
        if (service == null){
            throw new RuntimeException("not found service-"+serviceName);
        }
        //反射获取对象
        Class clazz = Class.forName(request.getClassName());
        Object[] args = request.getParameters();
        Method method = null;
        //当方法没有参数的时候
        if (args == null){
            //获取该方法
            method = clazz.getMethod(request.getMethodName());
        }else {
            Class<?>[] types = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            method = clazz.getMethod(request.getMethodName(),types);
        }
        Object invoke = method.invoke(service,args);
        return invoke;
    }
}
