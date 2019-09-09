package www.qulusheng.com.test.baseActivity;

import java.util.Arrays;

import www.qulusheng.com.test.arithmetic.StraightInsertionSort;
import www.qulusheng.com.test.observerDesign.ObserverDesign;
import www.qulusheng.com.test.other.Recursion;
import www.qulusheng.com.test.strategyDesign.DuckFly;
import www.qulusheng.com.test.strategyDesign.FlyBad;
import www.qulusheng.com.test.strategyDesign.FlyHigh;
import www.qulusheng.com.test.strategyDesign.RedDuck;

public class Main {
    public static void main(String[] args) {
        //designPattern1();
        //designPattern2();
        //observer();
        //recursion();
        int[] data = StraightInsertionSort.insertSort() ;
        //System.out.println(Arrays.toString(data));

    }

    public static void designPattern1() {
        RedDuck redDuck = new RedDuck() ;
        redDuck.performFly();
        redDuck.setFlyProperty(new FlyHigh());
        redDuck.performFly();
        redDuck.setFlyProperty(new FlyBad());
        redDuck.performFly();
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
