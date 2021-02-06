package com.zpy.rpc.rpcrequest;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/5 21:54
 */
public class Main {
    public static void main(String[] args) {
        String a = "1";
        String b = "2";
        System.out.println(a);
        System.out.println(b);
        a = b;
        System.out.println(a == b);
        System.out.println(b.hashCode());
    }
}
