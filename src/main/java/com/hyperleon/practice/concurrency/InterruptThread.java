package com.hyperleon.practice.concurrency;

/**
 * @author leon
 * @date 2021-05-04 16:58
 **/
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("threadA begin");
            while (Thread.interrupted()) {
                System.out.println("threadA has interrupt by other thread");
            }
            System.out.println("threadA quit");
        });
//        threadA.start();
//        threadA.interrupt();
//        threadA.join();
//        System.out.println("over");


        Thread threadB = new Thread(() -> {
//            while (Thread.currentThread().isInterrupted()) {
//                System.out.println("interrupted flag been set");
//            }
        });

        threadB.start();
        threadB.interrupt();
//        System.out.println(threadB.interrupt());
        System.out.println(threadB.isInterrupted());

        Thread.interrupted();
    }

}
