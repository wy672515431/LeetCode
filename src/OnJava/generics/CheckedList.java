package OnJava.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckedList {
    static void oldStyledMethod(List probablyDogs) {
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogList = new ArrayList<>();
        //error。向dog List传入了一个cat只有在使用的时候才会提示
        oldStyledMethod(dogList);
        //保证动态类型安全
        List<Dog> dogList1 = Collections.checkedList(new ArrayList<>(), Dog.class);
        try {
            oldStyledMethod(dogList1);
        } catch (Exception e) {
            System.out.println("Exception in: "  + e);
        }
    }
}
class Dog {}
class Cat {}
