package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Danny 姜
 * 当前线程池中运行的线程数量已经达到 corePoolSize 大小时，
 * 线程池会把任务加入到等待队列中，直到某一个线程空闲了，
 * 线程池会根据我们设置的等待队列规则，从队列中取出一个新的任务执行。
 */
public class MoreThanCoreCount {

    public static void main(String[] args) throws InterruptedException{
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时4秒
                        Thread.sleep(4000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            System.out.println("此时等待队列中有 " + fixedThreadPool.getQueue().size() + " 个元素");
            // 每500毫秒向线程池中提交任务
            Thread.sleep(500);
        }
        fixedThreadPool.shutdown();
    }
}
