## 添加多线程相关题目的练习，并在练习的过程中提高对Java 锁、并发的理解


[FooOrderExecute.java](https://github.com/McoyJiang/LagouAndroidShare/blob/master/%E6%89%A9%E5%B1%95%E5%86%85%E5%AE%B9/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%BB%83%E4%B9%A0/FooOrderExecute.java)

```Java
public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}


三个不同的线程 A、B、C 将会共用一个 Foo 实例。

一个将会调用 first() 方法
一个将会调用 second() 方法
还有一个将会调用 third() 方法
请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。

```


[FooBar](https://github.com/McoyJiang/LagouAndroidShare/blob/master/%E6%89%A9%E5%B1%95%E5%86%85%E5%AE%B9/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%BB%83%E4%B9%A0/FooBar.java)
```
class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}

两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
请设计修改程序，以确保 "foobar" 被输出 n 次。
```

[H2OFactory](https://github.com/McoyJiang/LagouAndroidShare/blob/master/%E6%89%A9%E5%B1%95%E5%86%85%E5%AE%B9/%E5%A4%9A%E7%BA%BF%E7%A8%8B%E7%BB%83%E4%B9%A0/H2OFactory.java)
```
现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。

存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。

氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。

这些线程应该三三成组突破屏障并能立即组合产生一个水分子。

你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。

换句话说:

如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
书写满足这些限制条件的氢、氧线程同步代码。
```
