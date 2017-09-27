package lesson3;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vidok on 20.09.17.
 */
public class terminal {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Commander commander = new Commander();
        CommandBody cmd = new CommandBody(Command.WORKING, "", "");
        while(!cmd.command.equals(Command.EXIT)) {
            try {
                byte[] buffer = new byte[1000];
                System.in.read(buffer);
                try{
                    cmd = parser.parse(new String(buffer));
                    commander.doIt(cmd);
                }
                catch(ParserException exp){
                    System.out.println("Command does not correct!!!");
                }
            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}