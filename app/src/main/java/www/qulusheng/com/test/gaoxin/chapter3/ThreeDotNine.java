package www.qulusheng.com.test.gaoxin.chapter3;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;

import www.qulusheng.com.test.R;

public class ThreeDotNine extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent("action") ;
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE) ;

        //拨号
        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:12321")) ;
        //号码传入拨号
        Intent callIntent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:12321")) ;
        //调用拨号程序
        Intent callIntent2 = new Intent("com.android.phone.action.TOUCH_DIALER") ;
        //调用系统浏览器
        Intent callIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("123")) ;
        //程序查看联系人
        Intent callIntent4 = new Intent("com.android.contacts.action.LIST_CONTRACTS");
        //系统设置界面
        Intent callIntent5 = new Intent("android.settings.SETTINGS");
        //显示WIFI设置界面
        Intent callIntent6 = new Intent("android.settings.WIFI_SETTINGS");
        startActivity(callIntent);
        //显示和关闭动画效果
        //overridePendingTransition();
    }

   static class Data implements Serializable {
        String id ;
        String name ;
    }

    public static void main(String[] args) throws IOException {
        String s = "2021-07-11" ;
        System.out.println(s.substring(s.length()-2));
        HashMap map = new HashMap() ;
        map.put(12,"12") ;
        map.put(12,"13") ;
        System.out.println(map.get(12));
        System.out.println(map.get(13));
        Data data = new Data() ;
        data.id = "12123" ;
        data.name = "1212" ;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream() ;
        ObjectOutputStream objectOutputStream = null ;

        String base64Str = "" ;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream) ;
            objectOutputStream.writeObject(data);
            base64Str = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()) ;
            System.out.println(base64Str);
            objectOutputStream.close();
            byteArrayOutputStream.close();

            byte[] bytes = Base64.getDecoder().decode(base64Str) ;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes) ;
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream) ;
            Data data1 = (Data) objectInputStream.readObject() ;
            System.out.println(data1.id + "-" +data1.name);
            byteArrayInputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            objectOutputStream.close();
            byteArrayOutputStream.close();
        }
    }
}