package com.zzq.core.test.threadlocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

/**
 * @author Zhou Zhong Qing
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: FastThreadLocal
 * @date 2019/10/9 17:59
 */
public class FastThreadLocalTest {

   /* private static final FastThreadLocal<Object> threadLocal = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() throws Exception {
            return new Object();
        }
    };
    private static final FastThreadLocal<Object> threadLoca2 = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() throws Exception {
            return new Object();
        }
    };*/

    private static final FastThreadLocal<String> threadLoca3 = new FastThreadLocal<String>();


    public static void main(String[] args) {
        new FastThreadLocalThread() {
            @Override
            public void run() {
                threadLoca3.set("thread1");
                Object object = threadLoca3.get();
                System.out.println(object);
            }
        }.start();

        try {
            Thread.sleep(19000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new FastThreadLocalThread() {
            @Override
            public void run() {
                threadLoca3.set("thread2");
                Object object = threadLoca3.get();
                threadLoca3.set("thread3");
                System.out.println(object);
            }
        }.start();

    }

}
