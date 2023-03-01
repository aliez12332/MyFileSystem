package test;

public class B extends A{
    static {
        System.out.println("B的类初始化块");
    }
    {
        System.out.println("B的实例初始化块");
    }

    public B() {
        System.out.println("B的构造方法");
    }

    public static void main(String[] args) {
        new B();
    }
}
