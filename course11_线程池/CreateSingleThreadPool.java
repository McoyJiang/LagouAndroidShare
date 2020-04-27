package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Danny 姜.
 */
public class CreateSingleThreadPool {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            singleThreadExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread().getName()
                            + " 正在执行 task: " + taskId);
                }

            });
            Thread.sleep(1000);
        }
    }
}
