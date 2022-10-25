package Asgn02.src;

import java.util.List;

/**
 * Assignment 02
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * DotOperationHandler Class - a class for performing the operations of computing a path and/or computing the clusters using the list of dots
 */
public class DotOperationHandler implements Runnable {
    List<String> optionsSelected;

    /**
     * DotOperationHandler constructor
     * Receives the list of currently selected options
     * @param optionsSelected - The list of the options currently selected on the side panel
     */
    public DotOperationHandler(List<String> optionsSelected){
        this.optionsSelected = optionsSelected;
    }


    public void computePath(List<Dot> dots){
        System.out.println("Path computed");
    }

    public void computeClusters(List<Dot> dots){
        System.out.println("Clusters calculated");
    }

    /**
     * run - overridden method used to initiate a thread and assign it tasks
     */
    @Override
    public void run() {
        DotStorage storage = DotStorage.getStorage();
        for (String s : optionsSelected) {
            if (s.equals("Cluster")) {
                computeClusters(storage.getData());
            }
            if (s.equals("Line")) {
                computePath(storage.getData());
            }
        }
        storage.updateData();
    }
}
