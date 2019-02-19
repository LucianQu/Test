package www.qulusheng.com.test.strategyDesign;

/**************************************************************************
*
*  File name: DuckFly.java
*  Date: 2019/2/18 14:06
*  Copyright (c) 2013-2018 by Automic.
*  Author: Lucian_qls
*  E-mail: 1017557706@qq.com
*
*  Function:
*   .策略设计模式
 *   一只鸭子本来是正常的，翅膀坏了就不能飞了，好了又能飞
 *   运行时改变鸭子的行为方式
 *   java是一种无法改变类结构的语言
 *      类：java程序的基本组成单位，定义了对象的属性和方法，是具有共同属性和行为的对象的集合。
 *      对象的实质：属性和行为
 *      类基本结构：属性、方法、构造方法。内部类、块
 * ChangeLog:
 * v1.0
**************************************************************************/
public class DuckFly {

    /**
     * 接口
     */
    interface Flyable1{
        void fly() ;
    }

    /**
     * 内部类飞的高
     */
    class FlyHigh1 implements Flyable1 {
        @Override
        public void fly() {
            System.out.println("this is a super fly duck");
        }
    }

    /**
     * 内部类，正常飞
     */
    class FlyNormal1 implements Flyable1 {
        public void fly() {
            System.out.println("fly just as a normal duck");
        }
    }

    /**
     * 内部类，不能飞
     */
    class FlyBad1 implements Flyable1 {
        public void fly() {
            System.out.println("cooked duck connot fly ");
        }
    }

    /**
     * 内部类，鸭子
     */
    class Duck1 {
        //属性 飞对象
        protected Flyable1 flyBehavior1;

        //方法 执行飞
        public void performFly() {
            flyBehavior1.fly();
        }
    }

    /**
     * 红鸭子 继承了鸭子
     */
    class RedDuck extends Duck1 {
        //拥有飞对象
        //继承了执行飞
        //构造，创建一个正常的鸭子
        public RedDuck() {
            flyBehavior1=new FlyNormal1();
        }
        //改变鸭子的状态
        public void setFlyProperty(Flyable1 fly){
            flyBehavior1=fly;
        }

    }

    //动作变换
    public void flyStart() {
        RedDuck aRedDuck=new RedDuck();
        aRedDuck.performFly();
        System.out.println("i teach the red duck to be a genius air duck ...");
        aRedDuck.setFlyProperty(new FlyHigh1());
        aRedDuck.performFly();
        System.out.println("the duck 不乖   ,so i hit and cook it...");
        aRedDuck.setFlyProperty(new FlyBad1());
        aRedDuck.performFly();

    }
}
