package com.hyperleon.practice.concurrency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author leon
 * @date 2021-05-10 08:45
 **/
public class FIFOMutex {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<Thread>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        while (waiters.peek() != current || !locked.compareAndSet(false,true)) {
            LockSupport.park(this);
            //clean interrupted flag in Thread
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }

        waiters.remove();
        //current thread interrupted flag has been set by other thread
        if (wasInterrupted) {
            // recover to interrupted flag
            // may be other thread interest in
            current.interrupt();
        }

    }

    public void unLock() {
        //Unconditionally sets to false.
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

}
