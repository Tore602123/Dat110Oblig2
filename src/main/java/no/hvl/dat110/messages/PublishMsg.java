package no.hvl.dat110.messages;

public class PublishMsg extends Message {

    String topic;
    String message;

    public PublishMsg(String user, String topic, String message){
        super(MessageType.PUBLISH, user);
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Message [topic=" + topic + ", type=" + getType() + ", user=" + getUser() + ", message=" + message + "]";
    }
}
