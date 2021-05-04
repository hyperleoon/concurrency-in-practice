package com.hyperleon.practice.concurrency;

import sun.misc.Contended;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author leon
 * @date 2021-05-04 18:47
 **/
public class UnsafeRun {

    /**
     * -XX:-RestrictContended
     */
    @Contended
    private Object resource = new Object();

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Class<Unsafe> unsafeClass = Unsafe.class;
        Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(unsafeClass);

        Thread threadA = new Thread(() -> {
            System.out.println("thread begin...");
            unsafe.park(true,System.currentTimeMillis() + 2000);
            System.out.println("thread over");
        });
        threadA.start();

        Thread threadB = new Thread(() -> {
            System.out.println("thread begin...");
            unsafe.park(false,0);
            System.out.println("thread over");
        });
        threadB.start();
        Thread.sleep(10000);
        unsafe.unpark(threadB);

        Field resource = UnsafeRun.class.getDeclaredField("resource");
        System.out.println("offset of resource: " + unsafe.objectFieldOffset(resource));
    }
}
