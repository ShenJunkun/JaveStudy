package com.example.multiprocesstest.BasicGrammer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyAnnotation{
    String value() default "Default value";
}

class Test {
    @MyAnnotation(value = "hello annotation")
    public void annotatedMethod() {
        System.out.println("this is annotated method");
    }
}

class Myclass extends Object{
    Integer integer;
    String string;

    @Override
    public String toString() {
        return "MyClass{" +
                "integer=" + integer.toString() +
                ", string = " + string +" }";
    }

    Myclass(){
        this.integer = 0;
        this.string = "";
    }

    Myclass(Integer integer, String s) {
        this.integer = integer;
        this.string = s;
    }


}
public class newobj {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Myclass.class;
        Myclass myclass = (Myclass) clazz.getDeclaredConstructor().newInstance();
        System.out.println(myclass);

        Myclass myclass1 = (Myclass) clazz.getDeclaredConstructor(Integer.class, String.class).newInstance(1, "hello" );
        System.out.println(myclass1);

        Method method = Test.class.getMethod("annotatedMethod");
        if (method.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
            System.out.println("Annotation value: "+ annotation.value());
        }

        System.out.println("hello world");


    }
}
