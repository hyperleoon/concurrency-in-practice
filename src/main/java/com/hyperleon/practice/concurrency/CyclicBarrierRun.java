package com.hyperleon.practice.concurrency;

import java.util.concurrent.CyclicBarrier;

/**
 * @author leon
 * @date 2021-05-06 17:06
 **/
public class CyclicBarrierRun {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("cyclicBarrier effect!");
        });

        for (int i = 0; i < 3; ++i) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getId() + "-begin");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getId() + "-over");
            }).start();
        }

        Thread.sleep(20000);
    }
}


