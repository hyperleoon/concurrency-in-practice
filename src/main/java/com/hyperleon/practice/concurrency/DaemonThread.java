package com.hyperleon.practice.concurrency;

import java.util.concurrent.ForkJoinPool;

/**
 * @author leon
 * @date 2021-05-04 17:16
 **/
public class DaemonThread {

    public static void main(String[] args) {
        Thread daemonThread = new Thread(() -> System.out.println("demon thead"));
        Thread userThread = new Thread(() -> System.out.println("user thead"));
        daemonThread.setDaemon(true);
        daemonThread.start();
        userThread.start();
    }
}
