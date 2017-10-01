package Lab1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.System.exit;
import static java.lang.System.setOut;

/**
 * Created by vidok on 23.09.17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*
        FileGen fg = new FileGen();
        fg.generator();
        exit (0);
        */
        String [] Args = new String [1000];
        for(int i=0; i<1000; i++){
            Args [i] = new String("../../test/test-" + i + ".txt");
        }
        Class<?> [] clazzes = {Class.forName("Lab1.Parser"), Class.forName("Lab1.ParserRejectExecutorHandler")};
        for(Class<?> clazz : clazzes) {
            Constructor<?> ctor = clazz.getConstructor(String.class);
            float classAverageTotalTime = 0;
            for (int i = 10; i < 1000; i += 10) {
                float totalTime = 0;
                for(int k=0; k<10; k++) {
                    long startTime = System.currentTimeMillis();
                    ParserRejectExecutorHandler.executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(i);
                    MYExecutionHandler myExecutionHandler = new MYExecutionHandler();
                    ParserRejectExecutorHandler.executorService.setRejectedExecutionHandler(myExecutionHandler);
                    for (String arg : Args) {
                        ParserRejectExecutorHandler.executorService.execute((Runnable) ctor.newInstance(arg));
                    }
                    ParserRejectExecutorHandler.executorService.shutdown();
                    while (!ParserRejectExecutorHandler.executorService.isTerminated()) {
                    }
                    long endTime = System.currentTimeMillis();
                    totalTime += (endTime - startTime);
                }
                classAverageTotalTime += totalTime / 10;
                System.out.println(clazz.getName() + " [" + i + "] : " + totalTime / 10);
            }
            System.out.println("\t" + " AVERAGE FOR CLASS " + clazz.getName() + " : " + classAverageTotalTime);
        }
        System.out.println("END");
    }
}
