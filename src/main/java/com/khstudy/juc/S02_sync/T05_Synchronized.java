package com.khstudy.juc.S02_sync;

public class T05_Synchronized {
    private int count = 10;
    private Object o = new Object();

    public void m() {

        synchronized (o) {//任何代码想要执行临界区的代码，必须先得到o这把锁，而跟m方法、count变量没有直接关系
            count--;
            System.out.println(count);
        }
    }
}
