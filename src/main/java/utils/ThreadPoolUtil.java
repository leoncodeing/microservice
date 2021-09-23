package utils;

import java.util.HashMap;
import java.util.concurrent.*;

public class ThreadPoolUtil {

    public static final int corePoolSize = 10;

    public static final int maxPoolSize = 50;

    public static final int keepAliveTime = 4000;

    public static final int queueSize = 1000;

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(queueSize));

    public static ListenerFuture listenerFuture = new ListenerFuture();

    public static void submitTask(Runnable runnable) {
        try {
            executor.submit(runnable);
        } catch (RejectedExecutionException ex) {

        }
    }

    public static void submitCallable(Callable callable) {
        try {
            Future future = executor.submit(callable);
            listenerFuture.onsuccess(future);
        } catch (Exception ex) {
            listenerFuture.onException(ex);
        }

    }


    public static class ListenerFuture implements Listener {


        @Override
        public void onsuccess(Future future) {
            try {
                Object result = future.get();
                if (result instanceof HashMap) {
                    System.out.println(result);
                } else if (result instanceof String) {
                    System.out.println(result);
                }else{
                    System.out.println(result);
                }
            } catch (ExecutionException exception) {

            } catch (InterruptedException ex) {
            }
        }

        @Override
        public void onException(Exception exception) {

        }
    }
}
