package www.qulusheng.com.test.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

import www.qulusheng.com.test.aidl.IBinderPool;

public class BinderPool {
    private static final String TAG = "BinderPool" ;
    public static final int BINDER_NONE = -1 ;
    public static final int BINDER_COMPUTE = 0 ;
    public static final int BINDER_SECURITY_CENTER = 1 ;

    private Context mContext ;
    private IBinderPool mBinderPool ;
    private static volatile BinderPool sInstance ;
    private CountDownLatch mConnectBinderPoolCountDownLatch ;

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context) ;
                }
            }
        }
        return sInstance ;
    }

    private BinderPool(Context context) {
        mContext = context.getApplicationContext() ;
        connectBinderPoolService();
    }

    private synchronized void connectBinderPoolService() {
        mConnectBinderPoolCountDownLatch = new CountDownLatch(1) ;
        Intent service = new Intent(mContext, BinderPoolService.class) ;
        mContext.bindService(service, mBinderPoolConnection, Context.BIND_AUTO_CREATE) ;
        try {
            //阻塞当前线程，直到计数器的值为0

            mConnectBinderPoolCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private ServiceConnection mBinderPoolConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service) ;
            try {
                mBinderPool.asBinder().linkToDeath(mBinderPoolDeathRecipient, 0);
            }catch (RemoteException e) {
                e.printStackTrace();
            }
            //当前线程调用此方法，则计数减一
            mConnectBinderPoolCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //Binder死亡监听，重连
    private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.w(TAG, "binder died .") ;
            mBinderPool.asBinder().unlinkToDeath(mBinderPoolDeathRecipient, 0) ;
            mBinderPool = null ;
            connectBinderPoolService();
        }
    } ;

    /*public static class BinderPoolImpl extends IBinderPool.Stub{
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null ;
            switch (binderCode) {
                case BINDER_COMPUTE :
                    binder = new ComputeImpl() ;
                    break;
                case BINDER_SECURITY_CENTER:
                    binder = new SecurityCenterImpl() ;
                    break;
                default:
                    break;
            }
            return binder;
        }
    }*/

    public IBinder queryBinder(int binderCode) {
        IBinder binder = null ;
        if (mBinderPool != null) {
            try {
                binder = mBinderPool.queryBinder(binderCode) ;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return binder ;
    }




    /*
    创建一个包含一个裁判的CountDownLatch=cdOrder
    创建一个包含四个选手的CountDownLatch=cdAnswer
    1、创建四个子线程
        1、等待裁判发出口令
        2、跑步
        3、跑完
    2、裁判发出口令
    3、等待选手跑完
    4、跑完统计结果
    *
    * public class CountdownLatchTest2 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(4);
        for (int i = 0; i < 4; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");
                        cdOrder.await();
                        System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("选手" + Thread.currentThread().getName() + "到达终点");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("裁判"+Thread.currentThread().getName()+"即将发布口令");
            cdOrder.countDown();
            System.out.println("裁判"+Thread.currentThread().getName()+"已发送口令，正在等待所有选手到达终点");
            cdAnswer.await();
            System.out.println("所有选手都到达终点");
            System.out.println("裁判"+Thread.currentThread().getName()+"汇总成绩排名");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
    * */
}