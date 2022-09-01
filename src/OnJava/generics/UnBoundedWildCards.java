package OnJava.generics;

import java.util.ArrayList;
import java.util.List;

public class UnBoundedWildCards {
    static List list;
    static List<?> list1;
    static List<? extends Object> list2;

    static void assign1(List tem) {
        list = tem;
        list1 = tem;
        list2 = tem;
    }

    static void assign2(List<?> tem) {
        list = tem;
        list1 = tem;
        list2 = tem;
    }

    static void assign3(List<? extends Object> tem) {
        list = tem;
        list1 = tem;
        list2 = tem;
    }

    public static void main(String[] args) {
        assign1(new ArrayList());
        assign2(new ArrayList<>());
        assign3(new ArrayList<>());
        //List<?> are reifiable, while List<? extends Objects> are not
        //A reifiable type is a type whose type information is fully available at runtime.
        // This includes primitives,
        // non-generic types, raw types, and invocations of unbound wildcards.
        List tem = new ArrayList();
        System.out.println(tem instanceof List<?>);
        //error
//        System.out.println(tem instanceof List<? extends Object>);
    }
}
