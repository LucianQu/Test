package www.qulusheng.com.test.observerDesign;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**************************************************************************
*
*  File name: ObserverDesign.java
*  Date: 2019/2/18 15:08
*  Copyright (c) 2013-2018 by Automic.
*  Author: Lucian_qls
*  E-mail: 1017557706@qq.com
*
*  Function:
*   .观察者模式
 *   帮助对象知悉现状，不会错过感兴趣的事情
 *   ...代表可变的参数类型，也称为不定参数类型 varargus
 * ChangeLog:
 * v1.0
**************************************************************************/
public class ObserverDesign {

    /**
     * 所有潜在的观察者必须实现观察者接口
     * 这个接口只有update一个方法，当主题状态改变时它被调用
     */
    interface Observer{
        void watch(String movie) ;
    }

    /**
     * 主题接口：
     *  对象使用此接口注册为观察者或者吧自己从观察者中删除
     */
    interface Subject{
        void registerObserver(Observer ...o) ;
        void removeObserver(Observer ... o) ;
        void notifObserver() ;
    }

    class sujingtai extends Observable{
        @Override
        public synchronized void addObserver(java.util.Observer o) {
            super.addObserver(o);
        }

        @Override
        public synchronized void deleteObserver(java.util.Observer o) {
            super.deleteObserver(o);
        }

        @Override
        public void notifyObservers() {
            super.notifyObservers();
        }

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
    }
    class dalong implements java.util.Observer {
        private Observable observable ;
        public dalong(Observable o) {
            observable = o;
            observable.addObserver(this);
        }

        @Override
        public void update(Observable o, Object arg) {

        }
    }

    /**
     * 一个具体主题，总是实现主题接口，除了注册和撤销方法之外，具体主题还实现了notibyobserver方法，改变状态更新
     */
    class 苏景台 implements Subject{
        List<Observer> observers = null;
        private String project;

        public 苏景台() {
            observers = new ArrayList<>() ;
        }
        @Override
        public void registerObserver(Observer... o) {
            for (Observer o1: o) {
                observers.add(o1) ;
            }
        }

        @Override
        public void removeObserver(Observer... o) {
            for (Observer o1: o) {
                if (observers.contains(o1)) {
                    observers.remove(o1);
                    System.out.println("移除：" + o1.getClass().getSimpleName());
                }else {
                    System.out.println(o1.getClass().getSimpleName()+"不存在，移除失败!");
                }
            }
        }

        @Override
        public void notifObserver() {
            for (Observer o : observers) {
                o.watch(project);
            }
        }

        public void setProject(String movie) {
            this.project = movie;
        }
    }



    /**
     *
     * 具体观察者可以是实现接口的任意类，观察者必须注册主题，以便接收信息
     */
    class 程文龙 implements Observer {
        private Subject s ;
        String isDoing ;
        public 程文龙(Subject s) {
            this.s = s ;
            s.registerObserver(this);
        }
        public void setIsDoing(String isDoing) {
            this.isDoing = isDoing;
        }
        @Override
        public void watch(String project) {
            System.out.println("->我叫"+this.getClass().getSimpleName());
            System.out.println("-->正在做的项目是："+isDoing);
            System.out.println("--->观察苏景台发布的最新项目是："+project);
        }
        private void unRegisterObserver() {
            s.removeObserver(this);
        }
    }

    class 杨剑 implements Observer {
        String isDoing ;
        public 杨剑(Subject s) {
            s.registerObserver(this);
        }

        public void setIsDoing(String isDoing) {
            this.isDoing = isDoing;
        }

        @Override
        public void watch(String project) {
            System.out.println("->我叫"+this.getClass().getSimpleName());
            System.out.println("-->正在做的项目是："+isDoing);
            System.out.println("--->观察苏景台发布的最新项目是："+project);
        }
    }

    class 李承铭 implements Observer {
        String isDoing ;
        public 李承铭(Subject s) {
            s.registerObserver(this);
        }
        public void setIsDoing(String isDoing) {
            this.isDoing = isDoing;
        }
        @Override
        public void watch(String project) {
            System.out.println("->我叫"+this.getClass().getSimpleName());
            System.out.println("-->正在做的项目是："+isDoing);
            System.out.println("--->观察苏景台发布的最新项目是："+project);
        }
    }

    public void start() {
        苏景台 groupLeader = new 苏景台();
        程文龙 cwl = new 程文龙(groupLeader);
        cwl.setIsDoing("沙雅项目");
        杨剑 yj = new 杨剑(groupLeader);
        yj.setIsDoing("闸门项目");
        李承铭 lcm = new 李承铭(groupLeader);
        lcm.setIsDoing("唐徕渠项目");
        groupLeader.setProject("登月项目");
        groupLeader.setProject("火星项目");
        groupLeader.notifObserver();
        groupLeader.removeObserver(yj);
        groupLeader.notifObserver();
        groupLeader.removeObserver(yj,cwl);
        groupLeader.notifObserver();
    }

}
