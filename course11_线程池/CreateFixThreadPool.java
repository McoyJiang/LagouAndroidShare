package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Danny 姜.
 */
public class CreateFixThreadPool {

    public static void main(String[] args) {
        // 创建线程数量为3的线程池
        ExecutorService singleThreadExecutor = Executors.newFixedThreadPool(3);
        // 提交10个任务交给线程池执行
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;

            singleThreadExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName()
                            + " 正在执行 task: " + taskId);
                }

            });
        }
    }
}
