package com.zpy.rpc;

import com.zpy.rpc.api.HelloRpc;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author zhao peng yu
 * @version 1.0
 * @date 2021/2/7 13:25
 */
public class Ma {
    public static void main(String[] args) {
        System.out.println(HelloRpc.class.getName());
        Random random = new Random();
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("1");
        System.out.println(strings.size());
        System.out.println(strings.get(1));
    }
}
