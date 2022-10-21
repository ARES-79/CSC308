package Asgn02.src;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 02
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * DotStorage Class - a singleton class for storing all the dots the user generates
 */
public class DotStorage extends MyObservable {
    private List<Dot> data = new ArrayList<>();
    private static DotStorage storage;

    /**
     * DotStorage private constructor
     */
    private DotStorage(){}

    /**
     * getStorage - creates a new object if there is none, else returns the single object
     * @return returns the DotStorage object
     */
    public static DotStorage getStorage() {
        if(storage == null){
            storage = new DotStorage();
        }
        return storage;
    }

    /**
     * getData - getter method to access the List of Dots
     * @return returns the List of Dots
     */
    public List<Dot> getData(){
        return data;
    }

    /**
     * updateData - calls notifying which then updates all the observers
     */
    public void updateData(){
        notifying();
    }
}
