package www.qulusheng.com.test.broadcast;

import www.qulusheng.com.test.eventbus.MessageEvent;

public class TestProtected extends MessageEvent {
    public TestProtected(String i) {
        super(i);
    }
    final int getParentInt() {
        return i ;
    }
}