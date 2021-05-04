package com.hyperleon.practice.concurrency;

/**
 * @author leon
 * @date 2021-05-04 16:33
 **/
public class WaitNotifyInterrupt {

    public static Object resource = new Object();

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                synchronized (resource) {
                    System.out.println(Thread.currentThread().getId() + " get resource");
                    resource.wait(2000);
                }
            }catch (InterruptedException e) {
                System.out.println("interrupt exception occur: "  + e);
            }
            System.out.println(Thread.currentThread().getId() + " quit wait status");
        });
        thread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
//        thread.interrupt();
    }
}
