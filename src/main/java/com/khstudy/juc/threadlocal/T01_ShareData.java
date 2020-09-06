package com.khstudy.juc.threadlocal;

/**
 * 共享数据
 */
public class T01_ShareData {
    volatile static Person p=new Person();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();
        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name="LiSi";
        });
    }
    static class Person{
        String name="ZhangSan";
    }
}
