package OnJava.generics;

class Building {}
class House extends Building {}
public class ClassTypeCapture<T> {
    private Class<T> kind;
    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }
    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        //由于类型参数我们使用instanceof会失败
        //我们显示地通过传入一个class对象来进行判断
        //编译器保证了类型标签和泛型参数相匹配
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));
    }
}
