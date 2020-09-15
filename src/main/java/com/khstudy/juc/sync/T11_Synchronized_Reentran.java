package com.khstudy.juc.sync;

/**
 * 一个同步方法调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍会得到该对象的锁
 * 也就是synchronized获得的锁是可重入的
 */
public class T11_Synchronized_Reentran {
    public synchronized void m1(){
        System.out.println("m1方法执行。。。");
    }
    public synchronized void m2(){
        System.out.println("m2 start....");
        m1();
        System.out.println("m2 end....");
    }

    public static void main(String[] args) {
        new Thread(()->{
            new T11_Synchronized_Reentran().m2();
        }).start();
    }
}
