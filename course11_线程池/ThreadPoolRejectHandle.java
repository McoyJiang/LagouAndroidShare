package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Danny 姜.
 * 如果提交的任务，无法被核心线程直接执行，
 * 又无法加入等待队列，又无法创建“非核心线程”直接执行，
 * 线程池将根据拒绝处理器定义的策略处理这个任务
 */
public class ThreadPoolRejectHandle {

    public static void main(String[] args) {
        // 核心线程为2、最大线程数为10、等待队列长度为2
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));

        for (int i = 1; i <= 6; i++) {  // 提交6次任务
            final int taskId = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时5秒
                        Thread.sleep(5000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            System.out.println("此时等待队列中有 " + threadPool.getQueue().size() + " 个元素");
        }
    }
}
