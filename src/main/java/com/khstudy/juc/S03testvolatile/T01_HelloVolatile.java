package com.khstudy.juc.S03testvolatile;

/**
 * 写程序证明volatile的可见性
 * volatile 关键字使一个变量在多个线程间可见，这就是可见性。也可以禁止指令重排
 * 出现线程不可见的原因：
 * 当线程工作的时候，会把主存中的变量拷贝一份到自己的工作内存中使用，当工作内存中的值改变时，其他线程的工作内存中的值并没有受到影响
 *
 * 缓存一致性协议
 * 英特尔使用MESI协议来保证可见性：Modify Exclusion Shared Invalid
 *
 * volatile 并不能保证原子性，所以不能代替synchronized，
 * 但是synchronized可以替换volatile，因为synchronized既可以保证原子性也可以保证可见性
 *
 */
public class T01_HelloVolatile {
    private static volatile boolean con = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("thread start...");
            while (con) {
            }
            System.out.println("thread end...");
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                con=false;
                System.out.println("con change..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}
