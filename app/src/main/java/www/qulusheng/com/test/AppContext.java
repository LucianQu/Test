package www.qulusheng.com.test;

import android.app.Application;
import android.content.Context;

import com.didichuxing.doraemonkit.DoraemonKit;

import www.qulusheng.com.test.view.ActivityTaskHelper;

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityTaskHelper.init(this);
        DoraemonKit.install(this);
    }

}
