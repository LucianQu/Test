package www.qulusheng.com.test.aidl;

import java.io.Serializable;

public class RemoteData implements Serializable {
    private static final long serialVersionUID = 202106021337000L ;
    public String strData ;
    public byte[] byteDatas ;

    public RemoteData() {
    }
    public RemoteData(byte[] byteDatas) {
        this.byteDatas = byteDatas ;
    }

    public RemoteData(String str ) {
        this.strData = str ;
    }
}