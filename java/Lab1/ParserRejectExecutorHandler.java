package Lab1;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ParserRejectExecutorHandler extends Parser {

    public static ThreadPoolExecutor executorService;

    public ParserRejectExecutorHandler(String string){
        super(string);
    }

    protected void setExitCondition(){
        executorService.shutdown();
    }

    protected void append2Map(String key){
        lock.lock();
        try {
            Integer newValue = 1;
            if (resultMap.containsKey(key)) {
                newValue = resultMap.get(key) + 1;
                resultMap.replace(key, newValue);
            } else {
                resultMap.put(key, newValue);
            }
            //System.out.println("key[" + key + "] -> [" + newValue + "]");
        }
        finally {
            lock.unlock();
        }
    }

}
