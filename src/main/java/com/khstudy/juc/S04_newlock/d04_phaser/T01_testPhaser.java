package com.khstudy.juc.S04_newlock.d04_phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 直译为相位器，更像是结合了cyclicBarrier和CountDownLunch,
 * countDownLunch 根据实际业务定义数量，当数量减少为0则可以继续执行否则阻塞
 * cyclicBarrier 定义线程数量，没攒够就一直阻塞，攒够了就放行
 */
public class T01_testPhaser {

    static Random rd = new Random();

    static MarriagePhaser mph = new MarriagePhaser();

    static void threadSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(rd.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mph.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            threadSleep();
            System.out.println(name + "已经到达");
            mph.arriveAndAwaitAdvance();
        }

        public void eat() {
            threadSleep();
            System.out.println(name + "已经吃完");
            mph.arriveAndAwaitAdvance();
        }

        public void leave() {
            threadSleep();
            if (name.startsWith("p")) {
                System.out.println(name + "离开了");
                mph.arriveAndAwaitAdvance();
            } else {
                System.out.println(name + "是主角");
                mph.arriveAndAwaitAdvance();
            }
        }

        public void inDonfang() {
            threadSleep();
            if (name.startsWith("p")) {
                System.out.println(name + "已经离开");
                mph.arriveAndDeregister();
            } else {

                System.out.println(name + "进入洞房");
                mph.arriveAndAwaitAdvance();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            inDonfang();
        }
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人都到达了。。" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束，新郎新娘入洞房。。" + registeredParties);
                default:
                    return true;
            }
        }
    }
}

