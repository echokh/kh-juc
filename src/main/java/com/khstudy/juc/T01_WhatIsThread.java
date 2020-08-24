package com.khstudy.juc;

import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {

    private static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1");
            }
        }
    }

    public static void main(String[] args) {
//        new T1().run();//这个不是线程，只是普通的run方法
        new T1().start();
        //两个方法的执行结果不同。start是交替打印，说明程序在交替执行
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
