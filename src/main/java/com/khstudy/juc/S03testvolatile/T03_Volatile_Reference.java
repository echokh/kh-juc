package com.khstudy.juc.S03testvolatile;

/**
 * 引用对象的线程可见,只是引用的可见
 */
public class T03_Volatile_Reference {
//    private static volatile boolean con = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("thread start...");
            while (T.con) {
            }
            System.out.println("thread end...");
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                T.con=false;
                System.out.println("con change..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class T{
        static boolean con=true;
    }
}
