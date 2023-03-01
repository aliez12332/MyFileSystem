package test;

public class A {
    {
        System.out.println("A的实例初始化块");
    }
    static {
        System.out.println("A的类初始化块");
    }
    public A() {
        System.out.println("A的构造方法");
    }

    public static void main(String[] args) {
        //new A();
    }
}
