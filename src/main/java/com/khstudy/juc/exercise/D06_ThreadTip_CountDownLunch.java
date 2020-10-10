package com.khstudy.juc.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 线程1添加10个元素到容器中
 * 线程2实时监控元素，个数到5的时候，线程2给出提示并结束
 * 使用volatile不能实现目的
 */
public class D06_ThreadTip_CountDownLunch {
    volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        D06_ThreadTip_CountDownLunch c = new D06_ThreadTip_CountDownLunch();
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        new Thread(() -> {
            if (c.size() != 5) {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2结束");
            cdl2.countDown();
        }, "t2").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Object o = new Object();
                System.out.println("add" + i);
                c.add(o);
                if (i == 5) {
                    cdl.countDown();
                    try {
                        cdl2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
