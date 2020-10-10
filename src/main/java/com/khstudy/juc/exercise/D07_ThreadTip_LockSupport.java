package com.khstudy.juc.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程1添加10个元素到容器中
 * 线程2实时监控元素，个数到5的时候，线程2给出提示并结束
 * 使用volatile不能实现目的
 */
public class D07_ThreadTip_LockSupport {
    volatile List<Object> list = new ArrayList<>();

    static Thread t1;

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        D07_ThreadTip_LockSupport c = new D07_ThreadTip_LockSupport();
        Thread t2 = new Thread(() -> {
            if (c.size() != 5) {
                LockSupport.park();
            }
            System.out.println("t2结束");
            LockSupport.unpark(t1);
        }, "t2");
        t2.start();

         t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                System.out.println("add" + i);
                c.add(o);
                if (i == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });
        t1.start();
    }
}
