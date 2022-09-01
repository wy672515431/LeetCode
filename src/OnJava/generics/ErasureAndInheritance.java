package OnJava.generics;

import LeetCode_297.D;

public class ErasureAndInheritance {
    public static void main(String[] args) {
        /* 由于泛型擦除和Derived2并没有包含泛型参数, 当我们向derived2进行set时，会触发警告
        Derived2 derived2 = new Derived2();
        Object obj = derived2.get();
        derived2.set(obj);
        */
        Derived1<Integer> derived1 = new Derived1<>();
        Integer obj = derived1.get();
        derived1.set(obj);
    }
}

class GenericBase<T> {
    private T element;
    public void set(T arg) {
        this.element = arg;
    }
    public T get() {
        return this.element;
    }
}

class Derived1<T> extends GenericBase<T> {}

class Derived2 extends  GenericBase {}



