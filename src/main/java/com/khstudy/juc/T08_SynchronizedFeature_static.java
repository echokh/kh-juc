package com.khstudy.juc;

public class T08_SynchronizedFeature_static {
    private static int count = 10;
    private Object o = new Object();

    public synchronized static void m() { //等价于synchronized（T06_SynchronizedFeature_static.class）
        count--;
        System.out.println(count);
    }

    public static void mm() {
        synchronized (T08_SynchronizedFeature_static.class) {
            count--;
        }
    }
}
