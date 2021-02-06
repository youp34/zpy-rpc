package com.zpy.rpc.rpcrequest;

import java.io.Serializable;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 20:58
 */
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] parameters;

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
