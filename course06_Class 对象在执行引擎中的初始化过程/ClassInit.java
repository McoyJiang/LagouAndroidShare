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
