package com.zzq.core.test.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhou Zhong Qing
 * @Title: HashMap测试
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2019/11/516:57
 */
public class TestHashMap {

    public static void main(String[] args) {

        /*jdk8的HashMap 在hashCode一致,equals不相等时会转成链表，采用尾插法,链表深度大于等于8个时会转成红黑树*/
        final Map<StringTest,String> map = new HashMap<>();

        map.put(new StringTest("1"),"1");
        map.put(new StringTest("2"),"2");
        map.put(new StringTest("3"),"3");
        map.put(new StringTest("4"),"4");
        map.put(new StringTest("5"),"5");
        map.put(new StringTest("6"),"6");
        map.put(new StringTest("7"),"7");
        map.put(new StringTest("8"),"8");
        map.put(new StringTest("9"),"9");

    }

    static class StringTest{
        private  String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public StringTest(String id) {
            this.id = id;
        }

        /*为了测试HashMap转链表，转树  ,把equals强行false*/
        @Override
        public boolean equals(Object obj) {
            return false;
        }

        /*为了测试HashMap转链表，转树  ,把hashCode强行改为一样的*/
        @Override
        public int hashCode() {
            return Integer.MAX_VALUE;
        }
    }
}
