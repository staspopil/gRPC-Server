package Storages;

public class Message {
    String sourcename;
    String messagebody;

    public Message(String name, String message)
    {
        this.messagebody = message;
        this.sourcename = name;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public String getSourcename() {
        return sourcename;
    }
}
