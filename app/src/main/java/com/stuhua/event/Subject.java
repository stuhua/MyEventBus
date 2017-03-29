package com.stuhua.event;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

class Subject {
    private List<Observer> observers;
    private static final Subject ourInstance = new Subject();

    static Subject getInstance() {
        return ourInstance;
    }

    private Subject() {
    }

    public void attach(Observer observer) {
        observers.add(observer);

    }

    public void dettach(Observer observer) {
        observers.remove(observer);
    }

    public void post(Object obj) {
        for(Observer observer:observers){
            observer.update(obj);
        }
    }
}
