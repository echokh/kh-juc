package com.khstudy.juc.sync;

public class T10_Synchronized_StaticAndNotStatic {
    public synchronized  static void m1() {
        System.out.println("m1 start..");
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end...");
    }

    public void m2() {
        System.out.println("m2 start");
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T10_Synchronized_StaticAndNotStatic t = new T10_Synchronized_StaticAndNotStatic();
        //从输出结果来看，同步方法可以调用非同步方法
//        new Thread(t::m1).start();
        new Thread(()->{
            T10_Synchronized_StaticAndNotStatic.m1();
        }).start();
        new Thread(t::m2).start();
    }

}
