package OnJava.generics;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class NeedCast {
//    @SuppressWarnings("unchecked")
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(args[0]));
        List<Fudge> shapes = (List<Fudge>)in.readObject();

        List<Fudge> lw1 = (List<Fudge>)List.class.cast(in.readObject());
    }
}
