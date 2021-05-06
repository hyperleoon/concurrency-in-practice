package com.hyperleon.practice.concurrency;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author leon
 * @date 2021-05-06 10:05
 **/
public class LongAddrRun {

    private static LongAdder longAdder = new LongAdder();

    private static volatile Long unsafeLong = 0L;

    private static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
       safeAdd();
       unsafeAdd();
        safeAddWithAtomic();
    }

    public static void safeAdd() throws InterruptedException {
        for (int i = 0;i < 10; ++i) {
            new Thread(() -> longAdder.add(1)).start();
        }
        Thread.sleep(2000);
        System.out.println(longAdder.longValue());
    }

    public static void unsafeAdd() throws InterruptedException {
        for (int i = 0;i < 10; ++i) {
            new Thread(() -> unsafeLong = unsafeLong + 1).start();
        }

        Thread.sleep(2000);
        System.out.println(unsafeLong);
    }

    public static void safeAddWithAtomic() throws InterruptedException {
        for (int i = 0;i < 10; ++i) {
            new Thread(() -> atomicLong.addAndGet(1)).start();
        }

        Thread.sleep(2000);
        System.out.println(unsafeLong);
    }
}
