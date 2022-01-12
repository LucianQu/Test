package www.qulusheng.com.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import www.qulusheng.com.test.MainActivity;
import www.qulusheng.com.test.eventbus.MessageEvent;

//声明广播
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent intent1 = new Intent(context, MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }
        if ("com.broadcast.qulusheng".equals(intent.getAction())){
            Log.e("Lucian--->接收qulussheng", "1213") ;
        }else if ("com.broadcast.qulusheng1".equals(intent.getAction())) {
            Log.e("Lucian--->接收qulussheng1", "1213") ;
        }
        //不能做耗时操作
        Log.e("Lucian--->广播数据", intent.getStringExtra("qulusheng")) ;
        String message = new MessageEvent("").message ;
        TestProtected m = new TestProtected("12");
        int j = m.getParentInt() ;

        //receive sms
        /*Bundle bundle = intent.getExtras() ;
        if (bundle != null) {
            Object[] objects = (Object[]) bundle.get("pdus") ;
            SmsMessage[] messages = new SmsMessage[objects.length] ;
            for (int i = 0; i < objects.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[])objects[i]) ;
                String s = "手机号：" +messages[i].getOriginatingAddress() + messages[i].getDisplayMessageBody() ;
            }
        }*/
        //屏幕关闭
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("Lucian--->屏幕关闭", "123") ;
        }
    }
}