package Lab1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.lang.System.exit;

/**
 * Created by vidok on 23.09.17.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        FileGen fg = new FileGen();
        fg.generator();
        exit (0);
        */

        long startTime = System.currentTimeMillis();

        String [] Args = new String [1000];
        for(int i=0; i<1000; i++){
            Args [i] = new String("test-" + i + ".txt");
        }

        ParserRejectExecutorHandler.executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(Args.length);
        MYExecutionHandler myExecutionHandler = new MYExecutionHandler();
        ParserRejectExecutorHandler.executorService.setRejectedExecutionHandler(myExecutionHandler);
        for (String arg : Args) {
            /**
             * Parser
             * 5010 4824 4043 4442 4760 4689 4740 5253 4645
             * ParserRejectExecutorHandler
             * 5298 5056 5042 4535 4672 5003 4523 4498 5012
             */
            ParserRejectExecutorHandler.executorService.execute(new ParserRejectExecutorHandler(arg));
        }
        ParserRejectExecutorHandler.executorService.shutdown();
        while(! ParserRejectExecutorHandler.executorService.isTerminated() ) {}

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time = " + totalTime);
    }
}
