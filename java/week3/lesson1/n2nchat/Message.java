package week3.lesson1.n2nchat;

public class Message {

    private String message;
    private Integer haveNewMessage;
    private long Id;

    Message(){
        message = "";
        haveNewMessage = 0;
        Id = -1;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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
