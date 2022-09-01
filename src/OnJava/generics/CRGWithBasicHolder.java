package OnJava.generics;

public class CRGWithBasicHolder {
    public static void main(String[] args) {
        SubType st1 = new SubType();
        SubType st2 = new SubType();
        st1.set(st2);
        SubType st3 = st1.get();
        st1.f();
    }
}

//奇异递归泛型,奇异递归指类奇怪地出现在自身地基类中
//基类BasicHolder用SubType替换了其参数。泛型基类成为了为其子类实现通用功能地模板，
//实现地功能会将派生类型用于所有地参数和返回值。
class SubType extends BasicHolder<SubType> {}


class BasicHolder<T> {
    T element;
    void set(T arg) {
        element = arg;
    }
    T get() { return element; }
    void f() {
        System.out.println(
                element.getClass().getSimpleName()
        );
    }
}