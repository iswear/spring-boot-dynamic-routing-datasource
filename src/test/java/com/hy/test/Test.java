package com.ygg.test;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/1/21.
 */
public class Test {


    public static class CLA1 {

        public void sayHello() {
            this.hello();
        }

        private void hello() {
            System.out.println("hello");
        }

    }

    public static class CLA2 extends CLA1 {

        public void sayHello () {
            super.sayHello();
        }

        private void hello() {
            System.out.println("hello world");
        }
    }


    public int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        CLA1 a = new CLA1();
        CLA2 b = new CLA2();
        a.sayHello();
        b.sayHello();
    }

}
