package com.khstudy.juc.S04_newlock.d03_CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环屏障，什么时候线程攒够了，屏障放开，出去之后屏障又竖起来，然后再攒够线程之后放倒屏障，一直循环
 * 有两个参数，第一个指定攒够的线程数量，第二个参数可以不写，定义了线程攒够之后需要执行的操作。类型是Runnable对象
 * 应用场景：有个业务场景既需要连接数据库，又需要完成文件读取，也需要完成网络读取都完成之后才继续进行，此时可以使用cyclicBarrier
 */
public class T01_testCyclicBarrier {

    public static void main(String[] args) {
        //只定义了线程数量
        CyclicBarrier cb=new CyclicBarrier(20);
        //定义线程数量，然后指定线程满了之后需要做的事
        CyclicBarrier cb1=new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("准备工作已经完成");
            }
        });
        CyclicBarrier cb2 = new CyclicBarrier(20, () -> {
            System.out.println("准备工作已经完成");
        });

        for (int i = 0; i < 90; i++) {
            new Thread(()->{
                try {
                    cb2.await();//如果线程数量不足则会一直阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();//线程必须先启动才能被阻塞住

        }
    }


}
