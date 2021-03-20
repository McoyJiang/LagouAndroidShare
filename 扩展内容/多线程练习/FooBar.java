package com.danny.thread_practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class FooBar {

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();

    public static void main(String[] args) {
        FooBar fooBar = new FooBar();

        Thread t1 = new Thread(fooBar::foo);
        Thread t2 = new Thread(fooBar::bar);

        t1.start();
        t2.start();
    }

    public void foo() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                System.out.println("foo");
                c2.signal();
                if (i != 9) c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar() {
        for (int i = 0; i < 10; i++) {
            try {
                lock.lock();
                System.out.println("bar");
                c1.signal();
                if (i != 9) c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
