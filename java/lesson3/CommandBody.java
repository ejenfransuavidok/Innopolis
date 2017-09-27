package lesson3;

/**
 * Created by vidok on 20.09.17.
 */
public class CommandBody {

    public Command command;
    public String filename;
    public String data;

    CommandBody(Command cmd, String fname, String d){
        command = cmd;
        filename = fname;
        data = d;
    }

}
