package lesson3;

import java.io.*;

/**
 * Created by vidok on 20.09.17.
 */
public class io {

    public static void main(String[] args) {
        readFromFile();
    }

    public static void readFromFile(){
        try (
                FileReader fileReader = new FileReader("1.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        )
        {
            System.out.println(bufferedReader.readLine());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void writeToFile2() {
        try (
                FileWriter fileWriter = new FileWriter("1.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            )
        {
            bufferedWriter.write("Hello world!");
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void writeToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter("1.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Hello world!");
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(fileWriter != null) {
                    fileWriter.close();
                }
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}
