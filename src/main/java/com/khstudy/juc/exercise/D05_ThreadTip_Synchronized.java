package com.khstudy.juc.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程1添加10个元素到容器中
 * 线程2实时监控元素，个数到5的时候，线程2给出提示并结束
 * 使用volatile不能实现目的
 */
public class D05_ThreadTip_Synchronized {
    volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        D05_ThreadTip_Synchronized c = new D05_ThreadTip_Synchronized();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束");
                lock.notify();
            }
        }, "t2").start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    Object o = new Object();
                    System.out.println("add" + i);
                    c.add(o);
                    if (i == 5) {
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }
}
