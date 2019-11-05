package com.zzq.core.test.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author Zhou Zhong Qing
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2019/11/5 15:30
 */
public class TestUnsafe {

    public static void main(String[] args) {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            System.out.println(unsafe);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
