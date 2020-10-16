package com.khstudy.juc.exercise;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步容器，拥有put和get方法，以及getCount方法，能够支持2个生产者线程和10个消费者线程的阻塞调用
 * 将生产者和消费者分开唤醒
 */
public class D10_PC_Condition {
    final LinkedList list = new LinkedList<Object>();
    final static int MAX = 10;
    final static int MIN = 0;

    ReentrantLock lock = new ReentrantLock();

    Condition csm = lock.newCondition();
    Condition pdc = lock.newCondition();

    private int count = 0;

    public  void put(Object o) {

        try {
            lock.lock();
            while (list.size() == MAX) {
                pdc.await();
            }
            list.add(new Object());
            ++count;
            csm.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get() {

        try {
            lock.lock();
//                this.wait();
            while (list.size() == MIN) {
                csm.await();
            }
            list.removeFirst();
            count--;
            pdc.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public int getCount() {
        return count;
    }


    public static void main(String[] args) {
        D10_PC_Condition c = new D10_PC_Condition();

        Thread[] producers = new Thread[2];
        Thread[] consumers = new Thread[10];


        /**
         * 先启动消费者
         */
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    c.get();
                    System.out.println("消费者" + c.getCount());
                }
            });
            consumers[i].start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者
        for (int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(() -> {

                for (int j = 0; j < 25; j++) {
                    c.put(new Object());
                    System.out.println("生产者" + c.getCount());
                }
            });
            producers[i].start();
        }


    }
}
