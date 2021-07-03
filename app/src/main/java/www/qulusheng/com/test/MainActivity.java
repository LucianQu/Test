package www.qulusheng.com.test;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import www.qulusheng.com.test.aidl.BinderPool;
import www.qulusheng.com.test.aidl.IMyAidlInterface;
import www.qulusheng.com.test.aidl.MyService;
import www.qulusheng.com.test.aidl.SecurityCenterImpl;
import www.qulusheng.com.test.baseActivity.BaseActivity;
import www.qulusheng.com.test.broadcast.MyBroadcastReceiver;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver() ;
    private IMyAidlInterface iMyAidlInterface = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_hello) ;
        textView.setOnClickListener(this);
        //bindService() ;
        testBinderPool() ;
    }

    @Override
    public void print() {
        super.print();
        Log.e("Lucian--->MainActivity","print") ;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hello:
                //Log.e("Lucian-->tv_hello","123") ;
                //actionTest() ;
                //sendBroadcast();
                //getAidlMessage() ;
                binderPoolDoWork() ;
                break;
        }
    }
    private void bindService() {
        Intent intent = new Intent();
        //8.0以后要指定包名+类名
        intent.setComponent(new ComponentName("www.qulusheng.com.test",////这个参数是另外一个app的包名
                "www.qulusheng.com.test.aidl.MyService")) ;////这个是要启动的Service的全路径名
        //设置服务所在的APP的包名
        //intent.setPackage("www.qulusheng.com.test");
        //设置服务所在APP的AndroidManifest的服务内容过滤器的action
        //intent.setAction("www.qulusheng.com.test.aidl.MyService1");
        //Intent intent1 = createExplicitFromImplicitIntent(MainActivity.this, intent) ;
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e("Lucian-->bindService", "已和待通讯APP的服务连接") ;
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service) ;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e("Lucian-->bindService", "已和待通讯APP的服务断开") ;

            }
        }, BIND_AUTO_CREATE);
    }

    private void getAidlMessage() {
        //发送一个广播到接收到需要3ms，执行到该函数需要2ms，aidl如果想获取到数据，需延时3ms
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (iMyAidlInterface != null) {
                    try {
                        Log.e("Lucian-->Aidl通讯获取广播信息", "data:"+iMyAidlInterface.getName()) ;
                    } catch (RemoteException e) {
                    }
                }else {
                    Log.e("Lucian-->Aidl通讯获取广播信息", "aidl未建立通讯连接") ;
                }
            }
        },3) ;


    }
    private int broadNum =1 ;
    private void sendBroadcast() {
       /* IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction("broadcast.qulusheng");
        MainActivity.this.registerReceiver(myBroadcastReceiver, intentFilter) ;*/
        Intent intent = new Intent() ;
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        //针对xml注册的广播，android8.0以后要对广播和服务更精确指向，需要指定我们的广播和服务的包名+类名
        //setComponent针对xml注册
        /*intent.setComponent(new ComponentName("www.qulusheng.com.test",
                "www.qulusheng.com.test.broadcast.MyBroadcastReceiver")) ;*/
        intent.setAction("com.broadcast.qulusheng") ;
        String message = "广播信息："+broadNum++  ;
        intent.putExtra("qulusheng",message) ;
        Log.e("Lucian-->sendBroadcast",message) ;
        sendBroadcast(intent);
    }

    private void actionTest() {
        //Activity Action
        Log.e("Lucian-->actionTest","123") ;
        Intent intent = new Intent() ;
        intent.setAction(Intent.ACTION_INSERT) ;
        //intent.setAction(Intent.ACTION_SEARCH) ;
        startActivity(intent);
    }
    ISecurityCenter mSecurityCenter = null ;
    private void testBinderPool() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("Lucian-->testBinderPool", "绑定");
                BinderPool binderPool = BinderPool.getInstance(MainActivity.this) ;
                IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER) ;
                mSecurityCenter = (ISecurityCenter) SecurityCenterImpl.asInterface(securityBinder) ;
            }
        }).start();
    }

    private void binderPoolDoWork() {
        if (mSecurityCenter != null) {
            String msg = "hello";
            try {
                String password = mSecurityCenter.encrypt(msg);
                Log.e("Lucian--->password", password);
                Log.e("Lucian--->password", mSecurityCenter.decrypt(password));
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e("Lucian--->password", e.getMessage());
            }
        }else {
            Log.e("Lucian--->password", "mSecurityCenter为空");
        }
    }
}
