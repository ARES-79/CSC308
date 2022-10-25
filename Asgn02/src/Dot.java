package Asgn02.src;

import java.awt.*;

/**
 * Assignment 02
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * Dot Class - a class for storing and communicating info about items on the GUI window
 */
public class Dot {
    private int x_coord;
    private int y_coord;
    private Color color;
    private boolean line;
    private static final int dotRadius = 7;

    /**
     * Dot constructor
     * @param x - int corresponding to the x-coordinate of the center of a dot
     * @param y - int corresponding to the y-coordinate of the center of a dot
     * @param color - Color of the dot
     */
    public Dot(int x, int y, Color color){
        this.x_coord = x;
        this.y_coord = y;
        this.color = color;
        this.line = false;
    }

    /**
     * setLine - setter for the line for a city
     *     has been drawn
     * @param line - boolean
     */
    public void setLine(boolean line) {
        this.line = line;
    }

    /**
     * isLine - indicates if the line has been drawn
     * @return boolean
     */
    public boolean isLine() {
        return line;
    }

    /**
     * setColor - setter for the color of a city
     *      to be used in clustering cities
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * getX-coord
     *      to be used in calculations in cluster and line operations
     * @return int - x coordinate of center of city
     */
    public int getX_coord() {return x_coord;}

    /**
     * getY-coord
     *      to be used in calculations in cluster and line operations
     * @return int - y coordinate of center of city
     */
    public int getY_coord() {return y_coord;}

    public void drawDot(Graphics g){
        g.setColor(color);
        g.fillOval(x_coord - dotRadius, y_coord - dotRadius, dotRadius*2, dotRadius*2);
    }
}
