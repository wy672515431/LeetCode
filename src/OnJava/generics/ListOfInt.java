package OnJava.generics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//由于类型擦除讲类型擦除为Object,所有泛型并不能应用在基本类型上
//我们只能通过自动装箱来实现
public class ListOfInt {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(1, 10).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
