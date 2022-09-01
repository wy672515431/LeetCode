package OnJava.generics;

//在非泛型中，参数的类型不能随子类型变化
class OrdinarySetter {
    void set(Base base) {

    }
}

class Setter extends OrdinarySetter {
    //只能重载，不能重写
    void set(Derived derived) {

    }
}
public class GenericsAndReturnTypes {
    void test(Getter g) {
        Getter g1 = g.get();
        GenericGetter g2 = g.get();
    }
}

class Base {}
class Derived extends Base {}

interface BaseInterface {
    Base get();
}
interface DerivedInterface extends BaseInterface {
    //not allowed in java 5 协变返回类型,但是参数不支持
    @Override
    Derived get();
}

interface GenericGetter<T extends GenericGetter<T>> {
    T get();
}

interface Getter extends GenericGetter<Getter> {

}
