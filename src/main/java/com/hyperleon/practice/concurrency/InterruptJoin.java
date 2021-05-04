package com.hyperleon.practice.concurrency;

/**
 * @author leon
 * @date 2021-05-04 16:48
 **/
public class InterruptJoin {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread threadA = new Thread(() -> {
            for (;;) {

            }
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt();
        });

        threadA.start();
        threadB.start();

        try {
            threadA.join();
        } catch (InterruptedException e) {
            System.out.println("main thread: " + e);
        }
    }

}
