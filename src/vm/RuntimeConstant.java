package vm;

public class RuntimeConstant {
    public static void main(String[] args) {
        // 在java7后，字符串常量池已经被移到堆中，intern()方法存储的是首次出现的实例引用。
        // 在java6中. intern()方法会被首次出现的字符串实例复制到字符串常量池中，返回的是字符串常量池中的引用.
        String str1 = new StringBuilder("王").append("勇").toString();
        System.out.println(str1.intern() == str1);
        // String.intern()返回的是首次出现的实例引用，java这个字符串已经在sun.misc.Version类中出现过了
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
