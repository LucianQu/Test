package www.qulusheng.com.test;

import android.app.Application;

import com.didichuxing.doraemonkit.DoraemonKit;

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DoraemonKit.install(this);
    }
}
