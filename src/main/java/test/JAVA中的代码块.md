## Java中的代码块
我们在做笔试题的时候经常会遇到考察类中代码块运行顺序的题，所以我现在把它总结一下。

在Java中，代码块（Block）是用一对大括号括起来的一段代码，它可以包含多条语句。根据它的位置和修饰符，Java中的代码块可以分为以下几种：
类初始化块（Static Initialization Blocks）：用static关键字修饰，用于在类被加载时执行一些初始化操作，可以有多个类初始化块，按照它们在类中的位置依次执行。
```java
public class MyClass {
    static {
        // 类初始化块
    }
}
```

实例初始化块（Instance Initialization Blocks）：不用任何修饰符修饰，用于在每个对象被创建时执行一些初始化操作，可以有多个实例初始化块，按照它们在类中的位置依次执行。
```java
public class MyClass {
    {
        // 实例初始化块
    }
}
```

局部代码块（Local Blocks）：用于在方法中限定变量的作用域和生命周期，局部代码块内定义的变量只能在该块内访问，块执行完后变量会被销毁。
```java
public void myMethod() {
    {
        int x = 1; // 局部代码块
    }
}
```

同步代码块（Synchronized Blocks）：用synchronized关键字修饰，用于实现线程同步，同一时刻只能有一个线程进入同步代码块执行。
```java
public class MyClass {
    public synchronized void myMethod() {
        // 同步代码块
    }
}
```
代码块中的变量和语句可以访问和操作它们所属的作用域内的变量和对象，代码块的作用是提高代码的可读性和灵活性，使代码具有更细粒度的控制和处理能力。

下面我用代码对类初始化块和实例初始化块的运行顺序进行测试：
```java
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
}
```

```java
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
```
输出： ①A的类初始化块
②B的类初始化块
③A的实例初始化块
④A的构造方法
⑤B的实例初始化块
⑥B的构造方法
解释：类初始化块会在类被加载时执行，所以12先执行。实例初始化块在类被实例化时执行，并且先于构造方法执行。





