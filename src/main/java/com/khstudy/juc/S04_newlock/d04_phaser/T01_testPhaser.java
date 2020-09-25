package com.khstudy.juc.S04_newlock.d04_phaser;

/**
 * 直译为相位器，更像是结合了cyclicBarrier和CountDownLunch,
 * countDownLunch 根据实际业务定义数量，当数量减少为0则可以继续执行否则阻塞
 * cyclicBarrier 定义线程数量，没攒够就一直阻塞，攒够了就放行
 *
 */
public class T01_testPhaser {
}
