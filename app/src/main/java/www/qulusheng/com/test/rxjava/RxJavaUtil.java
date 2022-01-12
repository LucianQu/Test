package www.qulusheng.com.test.rxjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil<T,E> {
    private Context context = null ;
    public RxJavaUtil(Context context) {
        this.context = context ;
    }
    public  void rx(T t) {
        System.out.println(t);
    }
    public <T extends E> void doo(ArrayList<T> list) {
        list.get(0) ;

    }
    public  void rxjavatest() {
        Observable           //被观察者
                //api 操作符 just事件执行
                .just(getAsyData())
                //执行结果的一个转换
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Exception {
                        return s+"1";
                    }
                })
                //事件处理在子线程中处理
                .subscribeOn(Schedulers.io())
                //观察者在主线程中执行
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(context,o.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) ;
    }

    public void rxjavaTest1(){

        Observable.just(getAsyData1())
                .map(new Function<List<String>, Object>() {
                    @Override
                    public Object apply(List<String> strings) throws Exception {
                        Log.e("rxjava", strings.toString()) ;
                        return strings.get(0);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.e("rxjava", o.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                              Log.e("rxjava", throwable.toString());
                    }
                });
    }

    public void rxjavaTest2() {
        Observable.just(getAysBean())
                .map(new Function<Bean, Object>() {
                    @Override
                    public Object apply(Bean bean) throws Exception {

                        return bean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Bean b = (Bean) o;
                           Log.e("rxjava", b.getName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                                  Log.e("rxjava", throwable.toString());
                    }
                }) ;
         
         
         



    }

    private Bean getAysBean() {
        Bean bean = new Bean() ;
        bean.setName("getAysBean");
        return bean;
    }

    public class Bean {
        String name ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private  String getAsyData(){
        return "getAsyData" ;
    }

    private List<String> getAsyData1(){
        List<String> list = new ArrayList<>() ;
        list.add("getAsyData1") ;
        return list ;
    }
}