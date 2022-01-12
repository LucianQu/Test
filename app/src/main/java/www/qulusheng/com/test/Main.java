package www.qulusheng.com.test;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import www.qulusheng.com.test.annotation.ClassAnnotation;
import www.qulusheng.com.test.annotation.Field1Annotation;
import www.qulusheng.com.test.annotation.FieldAnnotation;
import www.qulusheng.com.test.annotation.MethodAnnotation;
import www.qulusheng.com.test.annotation.Test1;
import www.qulusheng.com.test.bean.TestBean;
import www.qulusheng.com.test.eventbus.MessageEvent;
import www.qulusheng.com.test.interfacess.Animal;
import www.qulusheng.com.test.interfacess.Dog;
import www.qulusheng.com.test.memory.Variable;
import www.qulusheng.com.test.observerDesign.ObserverDesign;
import www.qulusheng.com.test.other.Recursion;
import www.qulusheng.com.test.rxjava.RxJavaUtil;
import www.qulusheng.com.test.strategyDesign.DuckFly;
import www.qulusheng.com.test.strategyDesign.FlyBad;
import www.qulusheng.com.test.strategyDesign.FlyHigh;
import www.qulusheng.com.test.strategyDesign.RedDuck;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        //designPattern1();
        //designPattern2();
        //observer();
        //recursion();
        //int[] data = StraightInsertionSort.insertSort() ;
        //System.out.println("Arrays.toString(data)");
        //testMessageEvent() ;
        //testString();
        //testReflect() ;
        //testSerializable() ;
        /*Integer s = new Integer(1) ;
        Integer s1 = new Integer(1);
        System.out.println(Integer.valueOf(s)==Integer.valueOf(s1));
        System.out.println(s.equals(s1));
        Scanner in = new Scanner(System.in) ;
        while (in.hasNext()) {
            System.out.println(in.next());
            break;
        }*/
        //testAnnotation() ;
        //int[] i = Variable.ss ;
       /* Variable variable = new Variable();
        Variable variable1 = new Variable() ;
        System.out.println("variable:" + "x= "+Variable.x +",y="+variable.y+",z="+Variable.z);
        System.out.println("variable1:" + "x= "+Variable.x +",y="+variable1.y+",z="+Variable.z);
        Variable.x = Variable.x+1;
        variable.y = 2;
        variable1.y = 3;
        System.out.println("variable:" + "x= "+Variable.x +",y="+variable.y+",z="+Variable.z);
        System.out.println("variable1:" + "x= "+Variable.x +",y="+variable1.y+",z="+Variable.z);*/
       /* Demo<Dog> dogDemo = new Demo<>(new Dog());
        run(dogDemo);
        List<Demo<Dog>> list = new ArrayList<>() ;
        list.add(dogDemo) ;
        test1(list);*/
       Integer i3 = 100 ;
       Integer i4 = 100 ;
       System.out.println(i3 == i4); //true
        // Integer i3 = Integer.valueOf(100)
       Integer i5 = 1000 ;
       Integer i6 = 1000 ;
       System.out.println(i5 == i6);// false
    }

    private static <T extends Animal> void run(@NonNull Demo<T> animalDemo) {
        animalDemo.run();

    }
    private static  void run1(@NonNull Demo<?> animalDemo) {
        animalDemo.run();

    }

    private static <T> void test1(List<T> list) {
        T t = list.get(0) ;
        System.out.println("T的类型是"+t.getClass().getName());
    }

    public static class Demo<T extends Animal>{
        private T ob;
        public T getOb() {
            return ob ;
        }
        public void setOb(T ob) {
            this.ob = ob;
        }
        public Demo(T ob) {
            super();
            this.ob = ob;
        }

        private void run() {
            System.out.println("T的类型是"+ob.getClass().getName());
            ob.run();
        }
    }



    public static void testAnnotation() throws NoSuchMethodException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Test1 test1 = new Test1();
        test1.age = 123 ;
        test1.id = 10 ;
        test1.completeness = "良好";
        test1.name ="this is a name" ;
        for (Field field : test1.getClass().getDeclaredFields()) {
            System.out.println(field.get(test1));
            System.out.println("------------");
        }
        System.out.println(test1.getClass().getAnnotations()[0].toString());
        System.out.println(test1.getClass().getDeclaredAnnotations()[0].toString());
        Method method = test1.getClass().getMethod("test01") ;
        MethodAnnotation annotation1 =(MethodAnnotation) method.getAnnotation(MethodAnnotation.class) ;
        System.out.println(annotation1.name());
        ClassAnnotation classAnnotation = (ClassAnnotation)  test1.getClass().getAnnotation(ClassAnnotation.class) ;
        System.out.println(classAnnotation.value());

        Field name =  test1.getClass().getDeclaredField("name") ;
        FieldAnnotation fieldAnnotation = (FieldAnnotation) name.getAnnotation(FieldAnnotation.class) ;
        System.out.println(name.get(test1));
        System.out.println(fieldAnnotation.name());
        System.out.println(fieldAnnotation.type());
        Field field = test1.getClass().getField("completeness") ;
        Field1Annotation classAnnotation1 = (Field1Annotation)  field.getAnnotation(Field1Annotation.class) ;
        if (classAnnotation != null) {
            for (String val : classAnnotation1.value()){
                String[] split = val.split("-");
                if (split.length >= 2) {
                    String fieldVal = String.valueOf(field.get(test1)) ;
                    if (split[1].equals(fieldVal)) {
                        System.out.println(split[1]);
                    }
                }
            }
        }
    }

    private static void testReflect() {
        try {
            Object obj = TestBean.class.newInstance() ;
            Class paramClass = Class.forName("[Ljava.lang.String;") ;
            String[] param = {"吃", "喝", "玩", "乐"} ;
            Method method = TestBean.class.getMethod("setTest", paramClass) ;
            method.invoke(obj, (Object)param) ;
            TestBean bean = (TestBean) obj ;
            System.out.println(bean.getTest()[0]);
            int count = 0 ;
            String f  ;
            f = "1" ;
            System.out.println(System.identityHashCode(f));
            String ff ="2"  ;

            System.out.println(System.identityHashCode(ff));
            ff =  f+"1" ;
            System.out.println(System.identityHashCode(ff));
            System.out.println(System.identityHashCode(f+"1"));
            System.out.println(System.identityHashCode(f));

            for(int i = 0; i < 10; i++) {
                count = count++ ;
            }
            System.out.println(count);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private static void testSerializable() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("result.obj")) ;
            objectOutputStream.writeObject(new Test());
            objectOutputStream.close();
            Test.staticVar = 10 ;
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("result.obj")) ;
            Test t = (Test) objectInputStream.readObject() ;
            objectInputStream.close();
            System.out.println(t.staticVar);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class Test implements Serializable{
        private static final long serialVersionUID = 1L ;
        private static int staticVar = 5 ;
    }

    public static void testLayoutInflater() {
        LayoutInflater layoutInflater = null ;
    }

    public static void testString() {
        //String对象的值不能被修改,只能改变引用
        //jvm看到“hello”，在String池没找到“hello”，创建string对象，并存储它，并将他的引用返回给s
        String s = "hello" ;
        //jvm看到“1234”，在String池创建string对象，并存储它，并将他的引用返回给s，“hello”并没有消失
        s = "1234" ;
        //找到了hello，不创建新的hello给s1
        String s1 = "hello" ;
        //遇到了new，在内存上创建string对象，并将string池的hello引用给new String，内存上的string，返回给s2
        String s2 = new String("hello") ;
        System.out.println(s1==s2);
    }

    public static void designPattern1() {
        RedDuck redDuck = new RedDuck() ;

        redDuck.performFly();
        redDuck.setFlyProperty(new FlyHigh());
        redDuck.performFly();
        redDuck.setFlyProperty(new FlyBad());
        redDuck.performFly();
        String s = new String("abc") ;
    }

    private static void testMessageEvent() {
        MessageEvent messageEvent = new MessageEvent("123") ;
        System.out.println(messageEvent.message);
    }

    public static void designPattern2() {
        DuckFly duckFly = new DuckFly() ;
        duckFly.flyStart();
    }

    public static void observer() {
        ObserverDesign observerDesign = new ObserverDesign() ;
        observerDesign.start();
    }

    public static void recursion() {
        Recursion recursion = new Recursion() ;
        recursion.start1();
    }
}
