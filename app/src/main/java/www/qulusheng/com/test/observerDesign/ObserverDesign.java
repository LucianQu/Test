package www.qulusheng.com.test.observerDesign;


import java.util.ArrayList;
import java.util.List;

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

    interface Observer{
        void watch(String movie) ;
    }

    interface Subject{
        void registerObserver(Observer ...o) ;
        void removeObserver(Observer ... o) ;
        void notifObserver() ;
    }

    class 苏景台 implements Subject{
        List<Observer> observers = new ArrayList<>() ;
        private String project;

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

    class 程文龙 implements Observer {
        String isDoing ;
        public 程文龙(Subject s) {
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
