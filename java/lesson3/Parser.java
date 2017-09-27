package lesson3;

/**
 * Created by vidok on 20.09.17.
 */

class ParserException extends Exception {
    public ParserException(String s) {
        super(s);
    }
}

public class Parser {

    public CommandBody parse(String command) throws ParserException {
        String [] explode = command.split(",");
        switch(explode[0].trim()){
            case "read":
                if(explode.length != 2) {
                    throw new ParserException("Command READ must have 2 arguments!");
                }
                else {
                    return new CommandBody(Command.READ, explode[1].trim(), "");
                }
            case "write":
                if(explode.length != 3) {
                    throw new ParserException("Command WRITE must have 3 arguments!");
                }
                else {
                    return new CommandBody(Command.WRITE, explode[1].trim(), explode[2].trim());
                }
            case "append":
                if(explode.length != 3) {
                    throw new ParserException("Command APPEND must have 3 arguments!");
                }
                else {
                    return new CommandBody(Command.APPEND, explode[1].trim(), explode[2].trim());
                }
            case "parse":
                if(explode.length != 2) {
                    throw new ParserException("Command PARSE must have 2 arguments!");
                }
                else {
                    return new CommandBody(Command.PARSE, explode[1].trim(), "");
                }
            case "serialize":
                if(explode.length != 2) {
                    throw new ParserException("Command SERIALIZE must have 2 arguments!");
                }
                else {
                    return new CommandBody(Command.SERIALIZE, explode[1].trim(), "");
                }
            case "deserialize":
                if(explode.length != 2) {
                    throw new ParserException("Command DESERIALIZE must have 2 arguments!");
                }
                else {
                    return new CommandBody(Command.DESERIALIZE, explode[1].trim(), "");
                }
            case "exit":
                return new CommandBody(Command.EXIT, "", "");
            default:
                return new CommandBody(Command.UNDEFINED, "", "");
        }
    }

}
