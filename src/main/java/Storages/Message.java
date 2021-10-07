package Storages;

public class Message {
    String sourcename;
    String messagebody;
    String topic;
    public Message(String name, String message, String topic)
    {
        this.messagebody = message;
        this.sourcename = name;
        this.topic = topic;
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

    public String getTopic() { return topic;}
}

