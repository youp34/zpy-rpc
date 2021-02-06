package com.zpy.rpc.rpcrequest;

import java.io.Serializable;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 20:58
 */
public class RpcRequest implements Serializable {
    //类名
    private String className;
    //方法名
    private String methodName;
    //参数
    private Object[] parameters;

    //版本号
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
