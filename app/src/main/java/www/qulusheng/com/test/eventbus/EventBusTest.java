package www.qulusheng.com.test.eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusTest {
    public EventBusTest() {
        // 本类或子类必须有@Subscribe的pulic且非static的方法
        EventBus.getDefault().register(this);
    }

    public static void main(String[] args) {
        new EventBusTest() ;
        MessageEvent messageEvent = new MessageEvent("456") ;
        EventBus.getDefault().post(messageEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void action1(MessageEvent event) {
        System.out.println(event.message);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void action2(MessageEvent event) {
        System.out.println(event.message);
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void action3(MessageEvent event) {
        System.out.println(event.message);
    }
}