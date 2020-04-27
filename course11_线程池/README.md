### 线程池的创建
##### CreateSingleThreadPool.java
创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按先进先出的顺序执行。

##### CreateCachedThreadPool.java
创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。

##### CreateFixThreadPool.java
创建一个固定数目的、可重用的线程池。

##### CreateScheduledThreadPool,java
创建一个定时线程池，支持定时及周期性任务执行。

### 线程池流程分析
##### LessThanCoreCount.java
当前线程池中运行的线程数量还没有达到 corePoolSize 大小时，线程池会创建一个新线程执行提交的任务，无论之前创建的线程是否处于空闲状态。

##### MoreThanCoreCount.java
当前线程池中运行的线程数量已经达到 corePoolSize 大小时，线程池会把任务加入到等待队列中，直到某一个线程空闲了，线程池会根据我们设置的等待队列规则，从队列中取出一个新的任务执行。

##### NoCoreThread.java
如果线程数大于 corePoolSize 数量但是还没有达到最大线程数 maximumPoolSize，并且等待队列已满，则线程池会创建新的线程来执行任务。

##### ThreadPoolRejectHandle.java
如果提交的任务，无法被核心线程直接执行，又无法加入等待队列，又无法创建“非核心线程”直接执行，线程池将根据拒绝处理器定义的策略处理这个任务。

### 线程池造成OOM
##### FixedThreadPoolOOM.java
使用无界阻塞队列，当核心线程执行时间很长，则新提交的任务还在不断地插入到阻塞队列中，最终造成OOM。

##### CachedThreadPoolOOM.java
缓存线程池的最大线程数为 Integer 最大值。当核心线程耗时很久，线程池会尝试创建新的线程来执行提交的任务，当内存不足时就会报无法创建线程的错误。
