### synchronized & ReentrantLock

##### LagouSynchronizedMehtods.java
synchronized可以作用于3个层面
1. 修饰实例方法； 
2. 修饰静态类方法； 
3. 修饰代码块。

> 需要注意如果synchronized锁对象时当前实例对象this，如果调用不同对象的同步方法，不会产生互斥效果

##### LagouReentrantLockTest.java
ReentrantLock需要手动加锁和释放锁，因此一般在finally语句块中调用unlock操作，释放锁对象。

##### LagouFairLockTest.java
ReentrantLock可以实现公平锁

##### ReadWriteTest.java
ReentrantReadWriteLock读写锁可以实现读写操作分离，当读操作时不产生互斥，只有写操作时才会阻塞其它线程的读写操作
