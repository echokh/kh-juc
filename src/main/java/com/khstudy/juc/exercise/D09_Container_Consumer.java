package com.khstudy.juc.exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程和10个消费者线程的阻塞调用
 */
public class D09_Container_Consumer {
    final LinkedList list = new LinkedList<Object>();
    final static int MAX = 10;
    final static int MIN = 0;

    private int count = 0;

    public synchronized void put(Object o) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        ++count;
        this.notifyAll();
    }

    public synchronized void get() {
        while (list.size() == MIN) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.removeFirst();
        count--;
        this.notifyAll();
    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        D09_Container_Consumer c = new D09_Container_Consumer();

        Thread[] producers = new Thread[2];
        Thread[] consumers = new Thread[10];
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(() -> {

                for (int j = 0; j < 25; j++) {
                    c.put(new Object());
                    System.out.println("生产者" + c.getCount());
                }
            });
            producers[i].start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    c.get();
                    System.out.println("消费者" + c.getCount());
                }
            });
            consumers[i].start();
        }
    }
}
