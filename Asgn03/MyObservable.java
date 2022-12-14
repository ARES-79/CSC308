package Asgn03;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Assignment 03
 * @author Mitashi Parikh
 * @version 1.0
 * Ass3.MyObservable Abstract Class - an abstract class which contains the list of observers and a notifying method
 */
public abstract class MyObservable {
    private LinkedList<MyObserver> observers = new LinkedList<MyObserver>();

    /**
     * addObserver - receives the Ass2.MyObserver type object to be added to the list of observers and adds it
     * @param observer - Ass2.MyObserver object to be added to the list of observers
     */
    public void addObserver(MyObserver observer){
        observers.add(observer);
    }

    /**
     * removeObserver - receives the Ass2.MyObserver type object to be removed from the list of observers and removes it
     * @param observer - Ass2.MyObserver object to be removed from the list of observers
     */
    public void removeObserver(MyObserver observer){
        observers.remove(observer);
    }

    /**
     * notifying - updates all the observers
     */
    public void notifying() throws IOException {
        for (MyObserver ob : observers){
            ob.update(this);
        }
    }
}
