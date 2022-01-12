package www.qulusheng.com.test.aidl;

import android.os.IBinder;
import android.os.RemoteException;


import static www.qulusheng.com.test.aidl.BinderPool.BINDER_COMPUTE;
import static www.qulusheng.com.test.aidl.BinderPool.BINDER_SECURITY_CENTER;

public class BinderPoolImpl extends IBinderPool.Stub {
    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null ;
        switch (binderCode) {
            case BINDER_COMPUTE :
                break;
            case BINDER_SECURITY_CENTER:
                binder = new SecurityCenterImpl() ;
                break;
            default:
                break;
        }
        return binder;
    }
}