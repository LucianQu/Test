package www.qulusheng.com.test.strategyDesign;

public class FlyNormal implements Flyable {
    @Override
    public void fly() {
        System.out.println("Lucian--->FlyNormal fly just as a normal duck") ;
    }
}
