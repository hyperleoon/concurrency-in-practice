package com.hyperleon.practice.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ForkJoinPool;

/**
 * @author leon
 * @date 2021-05-04 16:13
 **/
public class ProducerAndConsumer {

    private volatile Queue<Object> queue = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ProducerAndConsumer demo = new ProducerAndConsumer();
        forkJoinPool.submit(demo::producer);
        forkJoinPool.submit(demo::consumer);
        Thread.sleep(1000000);
    }

    public void producer() {
        synchronized (queue) {
            try {
                for (;;) {
                    while (queue.size() == 10) {
                            queue.wait();
                    }
                    System.out.println("Thread-" + Thread.currentThread().getId() + ":" + "begin produce");
                    queue.add(new Object());
                    queue.notifyAll();
                    System.out.println("Thread-" + Thread.currentThread().getId() + ":" + "size after produce: " + queue.size());
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void consumer() {
        synchronized (queue) {
            try {
                for (;;) {
                    while (queue.isEmpty()) {
                            queue.wait();
                    }
                    System.out.println("Thread-" + Thread.currentThread().getId() + ":" + "begin consumer");
                    queue.poll();
                    queue.notifyAll();
                    System.out.println("Thread-" + Thread.currentThread().getId() + ":" + "size after consumer: " + queue.size());
                    Thread.sleep(1000);
                }
            }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


}
