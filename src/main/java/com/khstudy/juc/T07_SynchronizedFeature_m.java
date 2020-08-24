package com.khstudy.juc;

public class T07_SynchronizedFeature_m {
    private int count = 10;
    private Object o = new Object();

    public synchronized void m() { //等价于synchronized（this）
        count--;
        System.out.println(count);
    }
}
