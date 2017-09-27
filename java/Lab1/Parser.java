package Lab1;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vidok on 23.09.17.
 */
public class Parser extends Thread {
    volatile public static boolean workingAll = true;
    protected static final HashMap<String, Integer> resultMap = new HashMap<>();
    protected final ReentrantLock lock = new ReentrantLock();
    final String fname;
    final Boolean exist;
    final Boolean isFile;
    final File file;

    Parser(String resource){
        fname = resource;
        file = new File(fname);
        if(exist = file.exists()){
            isFile = file.isFile();
        }
        else{
            isFile = false;
        }
    }

    protected void readingCycle(BufferedReader reader) throws IOException {
        String temp;
        while((temp = reader.readLine()) != null){
            parseLine(temp);
        }
    }

    public void run(){
        if(exist && isFile){
            try(
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
               )
            {
                readingCycle(reader);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getStackTrace());
            }
        }
        else if(!exist){
            System.out.println("file " + fname + " not found");
        }
        else{
            System.out.println("file " + fname + " is directory");
        }
    }

    protected void setExitCondition(){
        workingAll = false;
    }

    protected void append2Map(String key){
        synchronized (resultMap){
            Integer newValue = 1;
            if (resultMap.containsKey(key)) {
                newValue = resultMap.get(key) + 1;
                resultMap.replace(key, newValue);
            } else {
                resultMap.put(key, newValue);
            }
            System.out.println("key[" + key + "] -> [" + newValue + "]");
        }
    }

    protected void parseLine(String line){
        String [] items = line.split(" ");
        Pattern p = Pattern.compile("^[а-яА-Я]+$");
        for(int i=0; i<items.length && workingAll; i++){
            String key = items[i].trim();
            Matcher m = p.matcher(key);
            if(m.find()){
                /**
                 * @ здесь нужно добавить в общий ресурс
                 */
                append2Map(key);
            }
            else{
                /**
                 * @ здесь нужно выйти всем
                 */
                //System.out.println(key + " -----------> exit");
                //this.setExitCondition();
            }
        }
    }

}
