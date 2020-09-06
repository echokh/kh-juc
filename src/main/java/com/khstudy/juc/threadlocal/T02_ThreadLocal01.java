package com.khstudy.juc.threadlocal;

/**
 * 程序的输出结果是null，ThreadLocal中设置的值是线程独有的，只有线程自己设置的值才能自己取到
 */
public class T02_ThreadLocal01 {
    //    volatile static Person p=new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        });
    }

    static class Person {
        String name = "San_Zhang";
    }
}
