package www.qulusheng.com.test.baseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BaseActivity extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Lucian--->BaseActivity","onCreate") ;
    }
    public void print() {
        Log.e("Lucian--->BaseActivity","print") ;
    }
}
