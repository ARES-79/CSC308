package Asgn02.src;

import java.util.LinkedList;

/**
 * Assignment 02
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * MyObservable Abstract Class - an abstract class which contains the list of observers and a notifying method
 */
public abstract class MyObservable {
    private LinkedList<MyObserver> observers = new LinkedList<MyObserver>();

    /**
     * addObserver - receives the MyObserver type object to be added to the list of observers and adds it
     * @param observer - MyObserver object to be added to the list of observers
     */
    public void addObserver(MyObserver observer){
        observers.add(observer);
    }

    /**
     * removeObserver - receives the MyObserver type object to be removed from the list of observers and removes it
     * @param observer - MyObserver object to be removed from the list of observers
     */
    public void removeObserver(MyObserver observer){
        observers.remove(observer);
    }

    /**
     * notifying - updates all the observers
     */
    public void notifying(){
        for (MyObserver ob : observers){
            ob.update(this);
        }
    }
}
