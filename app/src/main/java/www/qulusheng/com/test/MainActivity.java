package www.qulusheng.com.test;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import www.qulusheng.com.test.aidl.BinderPool;
import www.qulusheng.com.test.aidl.IMyAidlInterface;
import www.qulusheng.com.test.aidl.ISecurityCenter;
import www.qulusheng.com.test.aidl.MyService;
import www.qulusheng.com.test.aidl.SecurityCenterImpl;
import www.qulusheng.com.test.baseActivity.BaseActivity;
import www.qulusheng.com.test.broadcast.MyBroadcastReceiver;
import www.qulusheng.com.test.recyclerview.RVAdapter;
import www.qulusheng.com.test.rxjava.RxJavaUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ListView ls ;
    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver() ;

    RecyclerView recyclerView = null ;
    RVAdapter rvAdapter = null ;
    List<Bitmap> listBitmap = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv) ;
        List<Bitmap> bitmaps = new ArrayList<>() ;
        for (int i=0; i<20;i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher) ;
            bitmaps.add(bitmap) ;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listBitmap.addAll(bitmaps) ;
        rvAdapter = new RVAdapter(this, listBitmap) ;
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
        /*TextView textView = findViewById(R.id.tv_hello) ;
        textView.setOnClickListener(this);*/
        /*TextView button = findViewById(R.id.bt_1) ;
        button.setOnClickListener(this);*/
        //registerForContextMenu(button);
        //bindService() ;
        //testBinderPool() ;
        //ObjectAnimator.ofFloat(new Object(),"translationX", 0, 100).setDuration(100).start();
        /*ClockView clockView = (ClockView) findViewById(R.id.clock_view) ;
        clockView.setCompleteDegree(30f);*/
        //sendBroadcast();
        //sendBroadcast1();
        //ls = findViewById(R.id.lv_1) ;
        /*MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver() ;
        IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(myBroadcastReceiver, intentFilter) ;
        SharedPreferences sharedPreferences = getSharedPreferences("123", Activity.MODE_PRIVATE) ;*/

        /*IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(receiver, intentFilter) ;*/
        //testContact() ;
        RxJavaUtil<Object, Integer> rxJavaUtil = new RxJavaUtil(this) ;
        ArrayList<Object> d = new ArrayList<>() ;
        d.add(12) ;
        //rxJavaUtil.doo(d);
        rxJavaUtil.rxjavatest();
        rxJavaUtil.rxjavaTest1();
        rxJavaUtil.rxjavaTest2();
    }



    //按钮点击
    private void onBtnClick() {
        blueInit() ;
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Lucian--blue-receiver","action "+ intent.getAction()) ;
        }
    } ;
    /*private void testReflect() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<TelephonyManager> telephonyManagerClass = TelephonyManager.class ;
        //通过java反射技术获取getITelephony方法对应的Method对象
        Method telephonyMethod = telephonyManagerClass.getDeclaredMethod("getITelephony", (Class[])null) ;
        //允许访问该方法
        telephonyMethod.setAccessible(true);

        Object obj = telephonyMethod.invoke(telephonyManagerClass, (Object[])null) ;
        //SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,) ;
        Method endCallMethod = obj.getClass().getMethod("endCall", null) ;
        endCallMethod.setAccessible(true);
        endCallMethod.invoke(obj, null) ;

        Method answerRingingCallMethod = obj.getClass().getMethod("answerRingingCall", null) ;
        answerRingingCallMethod.setAccessible(true);
        answerRingingCallMethod.invoke(obj, null) ;
    }*/
    private void testContact() {

        String[] permissions = {Manifest.permission.READ_CONTACTS};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, 3);
        }

        Cursor cursor = getContentResolver().query(Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "data/phones")
                ,null, null, null, null) ;
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                new String[]{"display_name","data1"}, new int[]{android.R.id.text1,android.R.id.text2}) ;
        ls.setAdapter(simpleCursorAdapter);
    }

    private void blueInit() {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE) ;
        BluetoothAdapter adapter =  bluetoothManager.getAdapter() ;
        if (!adapter.isEnabled()) {
            adapter.enable() ;
        }
        Set<BluetoothDevice> bluetoothDevices = adapter.getBondedDevices() ;
        for (BluetoothDevice device :
                bluetoothDevices) {
            Log.e("Lucian--blue", device.getAddress() + "  " +device.getName() + " " +device.getBondState() + device.getUuids()[0]) ;
        }
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, 2);
        }

    }
    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(this)
                            .setTitle("notifyTitle")
                            .setMessage("gpsNotifyMsg")
                            .setNegativeButton("cancel",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                            .setPositiveButton("setting",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, 1);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {
                    BluetoothAdapter.getDefaultAdapter().startDiscovery() ;
                    BluetoothAdapter.getDefaultAdapter().startLeScan(new BluetoothAdapter.LeScanCallback() {
                        @Override
                        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

                        }
                    });
                }
                break;
        }
    }
    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("Lucian--requestresult","" + requestCode) ;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        Toast.makeText(this, "startActivityForResult "+requestCode, Toast.LENGTH_SHORT).show();
    }

    //最佳handler使用
    private HandlerThread handlerThread ;
    private Handler mHandler;
    public void initHandler() {
        handlerThread = new HandlerThread("123") ;
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        }) ;
    }
    public void sendMessage() {
        mHandler.sendEmptyMessageAtTime(0,2000) ;
    }

    private void quitHandler() {
        handlerThread.quitSafely() ;
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null ;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"选项1") ;
        menu.add(1,2,2,"选项1") ;
        menu.add(1,3,3,"选项1") ;
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this,item.getItemId()+"", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this,item.getItemId()+"", Toast.LENGTH_SHORT).show();
        return true ;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1,1,1,"选项1") ;
        menu.add(1,2,2,"选项1") ;
        menu.add(1,3,3,"选项1") ;
    }

    @Override
    public void print() {
        super.print();
        Log.e("Lucian--->MainActivity","print") ;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.tv_hello:
                //Log.e("Lucian-->tv_hello","123") ;
                //actionTest() ;
                //sendBroadcast();
                //getAidlMessage() ;
                binderPoolDoWork() ;
                break;*/
            /*case R.id.bt_1:
                onBtnClick();
                break;*/
        }
    }

    MyService.MyBinder iMyAidlInterface = null;
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
                iMyAidlInterface = (MyService.MyBinder)IMyAidlInterface.Stub.asInterface(service) ;
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
        intent.setComponent(new ComponentName("www.qulusheng.com.test",
                "www.qulusheng.com.test.broadcast.MyBroadcastReceiver")) ;
        //指定广播 动作
        intent.setAction("com.broadcast.qulusheng") ;
        String message = "广播信息："+broadNum++  ;
        intent.putExtra("qulusheng",message) ;
        Log.e("Lucian-->sendBroadcast",message) ;
        sendBroadcast(intent);
    }
    private void sendBroadcast1() {
       /* IntentFilter intentFilter = new IntentFilter() ;
        intentFilter.addAction("broadcast.qulusheng");
        MainActivity.this.registerReceiver(myBroadcastReceiver, intentFilter) ;*/
        Intent intent = new Intent() ;
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
        //针对xml注册的广播，android8.0以后要对广播和服务更精确指向，需要指定我们的广播和服务的包名+类名
        //setComponent针对xml注册
        intent.setComponent(new ComponentName("www.qulusheng.com.test",
                "www.qulusheng.com.test.broadcast.MyBroadcastReceiver")) ;
        intent.setAction("com.broadcast.qulusheng1") ;
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
