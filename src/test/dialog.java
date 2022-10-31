package test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class dialog {
    public static void main(String[] args) {
        Stream.generate(new CoffeeSupplier())
                .limit(5)
                .forEach(System.out::println);
    }


    public int[] findDiagonalOrder(int[][] mat) {
        int[] ans = new int[mat.length * mat[0].length];
        int r = 0, c = 0;
        //遍历起始点
        for (int i = 0; i < ans.length; i++) {
            ans[i] = mat[r][c];
            //向上遍历,横坐标和纵坐标之间的奇偶性
            if ((r + c) % 2 == 0) {
                if (c == mat[0].length - 1) {
                    r++;
                } else if (r == 0) {
                    c++;
                } else {
                    c++;
                    r--;
                }
            } else {
                if (r == mat.length - 1)
                    c++;
                else if (c == 0) {
                    r++;
                } else {
                    r++;
                    c--;
                }

            }
        }
        return ans;
    }
}

class Foo<T> {
    T var;
}

class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " + id;
    }
}

class Latte extends Coffee {
    //必须显示的声明为public 或者其实public class
    public Latte() {}
}

class Mocha extends Coffee {
    public Mocha() {}
}

class Cappuccino extends Coffee {
    public Cappuccino() {}
}

class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee> {
    private final Class<?>[] types = {Latte.class, Mocha.class, Cappuccino.class};
    private static final Random rand = new Random(47);

    public CoffeeSupplier() {
    }

    private int size = 0;

    public CoffeeSupplier(int size) {
        this.size = size;
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    @Override
    public Coffee get() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].getConstructor().newInstance();
        } catch (InstantiationException |
                NoSuchMethodException |
                InvocationTargetException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeSupplier.this.get();
        }
    }
}




