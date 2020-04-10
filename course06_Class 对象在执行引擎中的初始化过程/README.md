>class 在初始化阶段，只会初始化与类相关的静态赋值语句和静态语句，也就是有 static 关键字修饰的信息，而没有 static 修饰的语句块在实例化对象的时候才会执行。

#### ClassInit.java
```
public class ClassInit{
    public static int value = 1;

    // 静态语句块在初始化阶段执行
    static{
        System.out.println("ClassInit static block!");
    }

    // 非静态语句块只在创建对象实例时被执行
    {
        System.out.println("ClassInit non-static block!");
    }
}
```

#### ClassInitTest.java
```
public class ClassInitTest{
    public static void main(String[] ags){

        ClassInit.value = 2;
    }
}
```
执行结果如下：
```
ClassInit static block!
```
只有静态语句被执行，但是如果在main方法中使用new创建ClassInit对象，如下：
```
public class ClassInitTest{
    public static void main(String[] ags){

        ClassInit.value = 2;

        ClassInit ci = new ClassInit();
    }
}
```
则非静态语句块也会被执行，运行结果如下：
```
ClassInit static block!
ClassInit non-static block!
```

#### InitOrder.java
对象的初始化顺序如下：
静态变量/静态代码块 -> 普通代码块 -> 构造函数
```
public class InitOrder {
    public static void main(String[] args){
        Child child = new Child();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
        child = new Child();
    }

    static class Child extends Parent{
        static {
            System.out.println("Child static block!");
        }

        {
            System.out.println("Child non-static block!");
        }

        public Child(){
            System.out.println("Child constructor!");
        }
    }

    static class Parent{
        static {
            System.out.println("Parent static block!");
        }

        {
            System.out.println("Parent non-static block!");
        }

        public Parent(){
            System.out.println("Parent constructor!");
        }
    }
}
```
执行结果如下：
```
Parent static block!
Child static block!
Parent non-static block!
Parent constructor!
Child non-static block!
Child constructor!
~~~~~~~~~~~~~~~~~~~~~~~
Parent non-static block!
Parent constructor!
Child non-static block!
Child constructor!
```
说明：
1. 父类静态变量和静态代码块；
2. 子类静态变量和静态代码块；
3. 父类普通成员变量和普通代码块；
4. 父类的构造函数；
5. 子类普通成员变量和普通代码块；
6. 子类的构造函数。
