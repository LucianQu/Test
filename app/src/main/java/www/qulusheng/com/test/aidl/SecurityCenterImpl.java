package www.qulusheng.com.test.aidl;

import android.os.RemoteException;

import www.qulusheng.com.test.ISecurityCenter;

public class SecurityCenterImpl extends ISecurityCenter.Stub {
    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray() ;
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^='^' ;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}