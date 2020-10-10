package com.khstudy.juc.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程1添加10个元素到容器中
 * 线程2实时监控元素，个数到5的时候，线程2给出提示并结束
 * 使用volatile不能实现目的
 */
public class D08_ThreadTip_Semaphore {
    volatile List<Object> list = new ArrayList<>();

    static Thread t1;

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        D08_ThreadTip_Semaphore c = new D08_ThreadTip_Semaphore();
        Semaphore sp = new Semaphore(1);

        Thread t2 = new Thread(() -> {
            if (c.size() != 5) {
                try {
                    sp.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2结束");
            sp.release();
        }, "t2");


        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                System.out.println("add" + i);
                c.add(o);
                if (i == 5) {
                    try {
                        sp.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
