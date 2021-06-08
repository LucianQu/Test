package www.qulusheng.com.test.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//声明广播
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //不能做耗时操作
        Log.e("Lucian--->广播数据", intent.getStringExtra("qulusheng")) ;
    }
}