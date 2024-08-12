package bytedance.patterndesign;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 请在下方实现一个观察者模式的例子
 */
interface Observer {
    void update(String message);
}

class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Observer " + name + " received message: " + message);
    }
}







