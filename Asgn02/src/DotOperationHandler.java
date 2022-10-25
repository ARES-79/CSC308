package Asgn02.src;

import Asgn02.src.Dot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<String> optionsSelected;

    /**
     * DotOperationHandler constructor
     * Receives the list of currently selected options
     * @param optionsSelected - The list of the options currently selected on the side panel
     */
    public DotOperationHandler(List<String> optionsSelected){
        this.optionsSelected = optionsSelected;
    }

    /**
     * distance - finds the Euclidean distance between two Dot objects
     * @param d1 - Dot
     * @param d2 - Dot
     * @return double - the distance between d1 and d2
     */
    public double distance(Dot d1, Dot d2){
        return Math.sqrt((d2.getY_coord() - d1.getY_coord()) *
                (d2.getY_coord() - d1.getY_coord()) +
                (d2.getX_coord() - d1.getX_coord()) *
                        (d2.getX_coord() - d1.getX_coord()));
    }

    /**
     * computePath - takes the list of Dots which have been drawn and
     *      sorts them so that each Dot is next to its closest Dot
     * @param data - the list of Dots which have been drawn
     * @param path - the sorted list of Dots to draw lines between
     * @param start - the Dot to start the line at
     * @return List<Dot> - the ordered list of Dots
     */
    public List<Dot> computePath(List<Dot> data, List<Dot> path, Dot start) {
        if (data.size() == 1) {
            return path;
        }

        double min = 100000000;
        Dot save = null;
        for (int i = 0; i < data.size(); i++) {
            if(data.indexOf(start) != i){
                double dist = distance(start, data.get(i));
                if (dist < min) {
                    min = dist;
                    save = data.get(i);
                }
            }
        }
        path.add(save);
        data.remove(start);
        computePath(data, path, save);

        return path;
    }

    /**
     * computePathData - wrapper function to change the Dot list
     *      to be the sorted Dot list returned from computePath
     * @param data - List<Dot> unsorted
     * @param path - List<Dot> sort
     */
    public void computePathData(List<Dot> data, List<Dot> path) {
        data.remove(0);
        for (int i = 0; i< path.size(); i++){
            data.add(path.get(i));
        }

        for (Dot d : data) {d.setLine(true);}
    }

    /**
     * computeClusters - computes the 2 clusters to divide the dots.
     *          Takes the dots furthest from each other and loops
     *          through every other dot to see which end it is closer to
     * @param data - List<Dot>
     */
    public void computeClusters(List<Dot> data){
        double max_dist = 0;
        Dot d1 = new Dot(0, 0, Color.BLACK);
        Dot d2 = new Dot(0, 0, Color.BLACK);
        for (int i = 0; i < data.size(); i++){
            for (int j = 0; j < data.size(); j++){
                double dist = distance(data.get(i), data.get(j));

                if (dist > max_dist){
                    max_dist = dist;
                    d1 = data.get(i);
                    d2 = data.get(j);
                }
            }
        }

        for (int k = 0; k < data.size(); k++){
            double distance_d1 = distance(d1, data.get(k));
            double distance_d2 = distance(d2, data.get(k));

            if (distance_d1 <= distance_d2){
                data.get(k).setColor(Color.BLUE);
            }else{
                data.get(k).setColor(Color.RED);
            }
        }
    }

    /**
     * run - overridden method used to initiate a thread and assign it tasks
     */
    @Override
    public void run() {
        DotStorage storage = DotStorage.getStorage();
        if (optionsSelected.contains("Cluster")) {
                computeClusters(storage.getData());
            }
        if (optionsSelected.contains("Line")) {
            int min = 100000000;
            Dot save = new Dot(0,0,Color.BLACK);
            for (Dot d : storage.getData()){
                if (d.getX_coord() + d.getY_coord() < min){
                    min = d.getX_coord() + d.getY_coord();
                    save = d;
                }
            }
            int i = storage.getData().indexOf(save);
            Dot start = storage.getData().get(i);
            ArrayList<Dot> path = new ArrayList<>();
            path.add(start);
            List<Dot> sorted = computePath(storage.getData(), path, start);
            computePathData(storage.getData(), sorted);

        }
        if (!optionsSelected.contains("Cluster")){
            for (Dot d : storage.getData()) {
                d.setColor(Color.BLACK);
            }
        }
        storage.updateData();
    }
}
