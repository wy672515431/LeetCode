package OnJava.generics;

import java.util.function.Supplier;

/**
 * 通过工厂方法来创建类型实例
 */
public class InstantiateGenericType {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        System.out.println(fe.get());
    }
}

class ClassAsFactory<T> implements Supplier<T> {
    private Class<T> kind;
    public ClassAsFactory(Class<T> kind) {
        this.kind = kind;
    }
    @Override
    public T get() {
        try {
            return kind.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class Employee {
    public Employee() {}
    @Override
    public String toString() {
        return "Employee";
    }
}