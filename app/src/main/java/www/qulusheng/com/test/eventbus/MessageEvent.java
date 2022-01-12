package www.qulusheng.com.test.eventbus;

public class MessageEvent {
    protected int i = 0 ;
    public final String message;


  public MessageEvent(String message) {
        this.message = message;
    }
}