package OnJava.generics;

public class SelfBounding {
}

//对参数进行了自限定，表明参数必须处于继承关系中
class SelfBounded<T extends SelfBounded<T>> {
    T element;
    SelfBounded<T> set(T arg) {
        element = arg;
        return this;
    }
    T get() {
        return element;
    }
}

class A extends SelfBounded<A> {}
class B extends SelfBounded<A> {}
class C extends SelfBounded<C> {
    C setAndGet(C arg) {
        set(arg);
        return get();
    }
}
class D {}
/* cannot do that
class E extends SelfBounded<D> {}

*/
class F extends SelfBounded {}
