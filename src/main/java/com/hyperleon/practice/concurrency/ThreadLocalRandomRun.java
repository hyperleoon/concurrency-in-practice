package com.hyperleon.practice.concurrency;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author leon
 * @date 2021-05-06 09:53
 **/
public class ThreadLocalRandomRun {

    private static ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    public static void main(String[] args) {
        for (;;) {
            new Thread(() -> System.out.println(threadLocalRandom.nextInt(Thread.currentThread().hashCode()))).start();
        }
    }
}
