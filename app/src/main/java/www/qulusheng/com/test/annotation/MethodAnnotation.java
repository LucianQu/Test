package www.qulusheng.com.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//注解使用范围 TYPE 类、接口  FIELD 字段 METHOD 方法  CONSTRUCTOR构造器
@Retention(RetentionPolicy.RUNTIME)
public //注解生命周期 SOURCE<CLASS<RUNTIME
@interface MethodAnnotation {
    String name() default "" ;
}
