package com.khstudy.juc.S02_sync;

public class T06_SynchronizedFeature {
    private int count = 10;
    private Object o = new Object();

    public void m() {

        synchronized (this) {//给当前类加锁
            count--;
            System.out.println(count);
        }
    }
}
