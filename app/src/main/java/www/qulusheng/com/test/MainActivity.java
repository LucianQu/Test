package www.qulusheng.com.test;

import android.os.Bundle;
import android.util.Log;

import www.qulusheng.com.test.baseActivity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        print();
    }

    @Override
    public void print() {
        super.print();
        Log.e("Lucian--->MainActivity","print") ;
    }


}
