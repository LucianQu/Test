package www.qulusheng.com.test.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class RemoteParcel implements Parcelable {
    public RemoteData packet ;

    // 读取对象需要提供一个类加载器去读取,因为写入的时候写入了类的相关信息
    protected RemoteParcel(Parcel in) {
        packet = (RemoteData)in.readValue(RemoteData.class.getClassLoader()) ;
    }
    //反序列化
    public RemoteParcel(RemoteData packet) {
        this.packet = packet ;
    }

    public static final Creator<RemoteParcel> CREATOR = new Creator<RemoteParcel>() {
        @Override
        public RemoteParcel createFromParcel(Parcel in) {
            return new RemoteParcel(in);
        }

        @Override
        public RemoteParcel[] newArray(int size) {
            return new RemoteParcel[size];
        }
    };
    //文件描述
    @Override
    public int describeContents() {
        return 0;
    }

    //实现序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(packet);
    }
}