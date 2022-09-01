package OnJava.generics;

import OnJava.Rand;

import java.util.ArrayList;
import java.util.List;

//由于类型擦除，我们无法声明泛型异常，泛型类也无法继承异常
public class ThrowGenericExceptions {
    public static void main(String[] args) {
        ProcessorRunner<String, Failure1> processorRunner = new ProcessorRunner<>();
        for (int i = 0; i < 3; i++) {
            processorRunner.add(new Processor1());
        }
        try {
            System.out.println(processorRunner.processAll());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

interface Processor<T, E extends Throwable> {
    void process(List<T> resultCollector) throws E;
}

class ProcessorRunner<T, E extends Throwable> extends ArrayList<Processor<T, E>> {
    List<T> processAll() throws E {
        List<T> resultCollector = new ArrayList<>();
        for (Processor<T, E> processor : this) {
            processor.process(resultCollector);
        }
        return resultCollector;
    }
}

class Failure1 extends Exception {}

class Processor1 implements Processor<String, Failure1> {
    static int count = 3;
    @Override
    public void process(List<String> resultCollector) throws Failure1{
        if (count -- > 1) {
            resultCollector.add("Hep");
        } else {
            resultCollector.add("Ho!");
        }
        if (count < 0) {
            throw new Failure1();
        }
    }
}

class Failure2 extends Exception {}
class Processor2 implements Processor<Integer, Failure2> {
    static int count = 3;
    @Override
    public void process(List<Integer> resultProcessor) throws Failure2{
        if (count -- >= 1) {
            resultProcessor.add(47);
        } else {
            resultProcessor.add(31);
        }
        if (count < 0) {
            throw new Failure2();
        }
    }
}
