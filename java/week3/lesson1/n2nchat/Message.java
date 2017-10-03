package week3.lesson1.n2nchat;

public class Message {

    private String message;
    private Integer haveNewMessage;

    Message(){
        message = "";
        haveNewMessage = 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHaveNewMessage() {
        return haveNewMessage;
    }

    public void setHaveNewMessage(Integer haveNewMessage) {
        this.haveNewMessage = haveNewMessage;
    }
}
