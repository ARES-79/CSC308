package Asgn03;

import Asgn02.src.Dot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MyBoard extends BoardPanel implements MouseListener {
    ArrayList<Tile> myTiles;
    Boolean ships = true;

    public MyBoard() {
        myTiles = new ArrayList<>();
        addMouseListener(this);
//        for(int i = 0; i<size; i++){
//            myTiles.add(new Tile(i));
//            screen.add(myTiles.get(i));
//        }
    }

    public ArrayList<Tile> getMyTiles() {
        return myTiles;
    }

    public void setMyTiles(ArrayList<Tile> myTiles) {
        this.myTiles = myTiles;
    }

    public void addTile(Tile t) {myTiles.add(t);}



    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("here");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed at: (" + e.getX() + ", " + e.getY() + ")");

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
