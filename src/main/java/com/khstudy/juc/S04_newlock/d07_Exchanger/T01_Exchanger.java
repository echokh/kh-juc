package com.khstudy.juc.S04_newlock.d07_Exchanger;

import java.util.concurrent.Exchanger;

/**
 * 两个线程之间交换数据
 */
public class T01_Exchanger {
    static Exchanger<String> exchanger=new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s="T1";
            try {
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t1").start();
        new Thread(()->{
            String s="T2";
            try {
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+s);
        },"t2").start();
    }
}
