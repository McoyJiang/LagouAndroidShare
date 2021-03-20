package com.danny.thread_practice;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooOrderExecute {

    Foo foo = new Foo();

    public static void main(String[] args) {
        FooOrderExecute et = new FooOrderExecute();
        // et.testFooWithReentrantLock();
        et.testFooWithSemaphore();
    }

    private Semaphore two = new Semaphore(0);
    private Semaphore three = new Semaphore(0);

    public void testFooWithSemaphore() {
        Thread t1 = new Thread(() -> {
            foo.first();
            two.release();
        });

        Thread t2 = new Thread(() -> {
            try {
                two.acquire();
                foo.second();
                three.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                three.acquire();
                foo.third();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t1.start();
        t2.start();
    }

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    private boolean firstPrinted = false;
    private boolean secondPrinted = false;

    private void testFooWithReentrantLock() {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                foo.first();
                //firstPrinted = true;
                c1.signal();
                System.out.println("c1 already signaled");
            } catch (Exception ignored) {
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("c1 await here");
                c1.await();
//                if (!firstPrinted) {
//                    c1.await();
//                }
                foo.second();
                //secondPrinted = true;
                c2.signal();
            } catch (Exception ignored) {
            } finally {
                lock.unlock();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                lock.lock();
//                if (!secondPrinted) {
//                    c2.await();
//                }
                c2.await();
                foo.third();
            } catch (Exception ignored) {
            } finally {
                lock.unlock();
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }

    class Foo {
        public void first() {
            System.out.println("first");
        }
        public void second() {
            System.out.println("second");
        }
        public void third() {
            System.out.println("third");
        }
    }

}