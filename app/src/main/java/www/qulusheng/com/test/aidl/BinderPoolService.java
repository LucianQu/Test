package www.qulusheng.com.test.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BinderPoolService extends Service {
    private static final String TAG = "BinderPoolService" ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPoolImpl();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}