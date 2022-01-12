package www.qulusheng.com.test.annotation;

@ClassAnnotation("Test1")
public class Test1 {
    @FieldAnnotation(name="id",type ="int")
    public int id ;
    @FieldAnnotation(name="name",type ="char")
    public String name  ;
    @FieldAnnotation(name="age",type ="int")
    public int age ;
    @Field1Annotation({"1-良好", "2-一般", "3-较差", "4-损坏", "5-其他"})
    @FieldAnnotation(name="完好程度",type ="int")
    public String completeness ;

    @MethodAnnotation(name = "hh")
    public static void test01(){

    }
}