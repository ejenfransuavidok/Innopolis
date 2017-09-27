package lesson3;

import java.io.*;

/**
 * Created by vidok on 20.09.17.
 */
public class Commander {

    public boolean doIt(CommandBody cmd){
        switch(cmd.command){
            case PARSE:
                System.out.println("PARSE COMMAND");
                try {
                    SerializatorDeserializator.collectFromTextFile(cmd.filename);
                } catch (SourceFileFormatException e) {
                    System.out.println(e.getStackTrace());
                    return false;
                }
                return false;
            case SERIALIZE:
                System.out.println("SERIALIZE COMMAND");
                SerializatorDeserializator.serializeCollection(cmd.filename);
                return true;
            case DESERIALIZE:
                System.out.println("DESERIALIZE COMMAND");
                SerializatorDeserializator.deSerializeCollection(cmd.filename);
                return true;
            case APPEND:
                appendToFile(cmd.filename, cmd.data);
                System.out.println("COMMAND APPEND");
                return true;
            case READ:
                System.out.println("COMMAND READ :\n" + readFromFile(cmd.filename));
                return true;
            case WRITE:
                writeToFile2(cmd.filename, cmd.data);
                System.out.println("COMMAND WRITE");
                return true;
            case EXIT:
                System.out.println("COMMAND EXIT");
                return true;
            default:
                System.out.println("COMMAND UNDEFINED");
                return false;
        }
    }

    public static String readFromFile(String fname){
        try (
                FileReader fileReader = new FileReader(fname);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        )
        {
            String outp = "";
            String temp = "";
            while((temp = bufferedReader.readLine()) != null){
                outp += (temp + '\n');
            }
            return outp;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static void writeToFile2(String fname, String data) {
        try (
                FileWriter fileWriter = new FileWriter(fname);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        )
        {
            bufferedWriter.write(data);
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void appendToFile(String fname, String data) {
        try (
                FileWriter f = new FileWriter(fname, true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);
        ) {
            p.println(data);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}


