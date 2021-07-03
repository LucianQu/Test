package www.qulusheng.com.test.aidl;

import android.os.RemoteException;

import www.qulusheng.com.test.ICompute;

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}