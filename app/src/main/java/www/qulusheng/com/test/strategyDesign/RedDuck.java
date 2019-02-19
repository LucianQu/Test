package www.qulusheng.com.test.strategyDesign;

public class RedDuck extends Duck {
    public RedDuck() {
        flyable = new FlyNormal() ;
    }
    public void setFlyProperty(Flyable flyable) {
        this.flyable = flyable ;
    }
}
