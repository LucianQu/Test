package www.qulusheng.com.test.aidl;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import www.qulusheng.com.test.broadcast.MyBroadcastReceiver;

public class MyService extends Service {

    private String message = "" ;
    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public String getName() throws RemoteException {
            return message;
        }

        @Override
        public String getData() throws RemoteException {
            return null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver, new IntentFilter("com.broadcast.qulusheng")) ;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
    BroadcastReceiver receiver = new MyBroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            message = intent.getStringExtra("qulusheng") ;
        }
    } ;

   /* public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            message = intent.getStringExtra("qulusheng") ;
            Log.e("Lucian-->onReceive","Service接收广播信息-->"+message) ;
        }
    } ;*/


    /*
    *
    *
package www.qulusheng.com.client1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import www.qulusheng.com.test.aidl.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {

    //包名要和服务所在的APP的aidl包名一致
    private IMyAidlInterface iMyAidlInterface ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(MainActivity.this, "已和待通讯APP的服务连接", Toast.LENGTH_SHORT).show();
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service) ;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(MainActivity.this, "已和待通讯APP的服务断开", Toast.LENGTH_SHORT).show();
            }
        }, BIND_AUTO_CREATE);
        TextView textView = (TextView)findViewById(R.id.tv_hello) ;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (iMyAidlInterface != null) {
                        Log.e("Lucian", "1231--" + iMyAidlInterface.getName());
                        Toast.makeText(MainActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "服务为绑定，无法通讯，请先启动待通讯APP!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName("www.qulusheng.com.test",////这个参数是另外一个app的包名
                                "www.qulusheng.com.test.aidl.MyService")) ;////这个是要启动的Service的全路径名
                        bindService(intent, new ServiceConnection() {
                            @Override
                            public void onServiceConnected(ComponentName name, IBinder service) {
                                Toast.makeText(MainActivity.this, "已和待通讯APP的服务连接", Toast.LENGTH_SHORT).show();
                                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service) ;
                            }

                            @Override
                            public void onServiceDisconnected(ComponentName name) {
                                Toast.makeText(MainActivity.this, "已和待通讯APP的服务断开", Toast.LENGTH_SHORT).show();
                            }
                        }, BIND_AUTO_CREATE);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

}
    *
    *
    * */
}