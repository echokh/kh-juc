package com.khstudy.juc.S01_hello_thread;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName());
            }
        }
    }

    public static void main(String[] args) {
        //   new T1().run();//这个不是线程，只是普通的run方法
        T1 t1 = new T1();
        t1.setName("First Thread");//给线程设置名称
        t1.getId();//获取线程的ID
        System.out.println("before start.."+t1.isAlive());//获取一条线程存活状态
        t1.start();
        System.out.println("after start.."+t1.isAlive());
        //两个方法的执行结果不同。start是交替打印，说明程序在交替执行
        for (int i = 0; i < 2; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
