package Asgn03;

import Asgn02.src.Dot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MyBoard extends BoardPanel implements ActionListener {
    int numShipTiles = 10;
    List<List<Integer>> shipList = new ArrayList<>();
    ArrayList<Tile> shipTiles = new ArrayList<>();

    public MyBoard() {
//        setLayout(new GridLayout(11,11, -1, -1));
//        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
//
//        //List<Tile> tileList = new ArrayList<>();
//
//        add(new JLabel(""));
//        int value = 0;
//        for(int i = 1; i<121; i++) {
//            if (i < 11)
//                add(new JLabel("     " + i));
//            else if (i % 11 == 0){
//                int alpha = (i%10 == 0) ? 10 : i%10;
//                add(new JLabel("   " + Character.toString((char) (alpha + 64))));
//            }
//            else{
//                Blackboard.getBlackboard().addTile(new Tile(value));
//                value +=1;
//                add(Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() -1));
////                Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() -1).addActionListener(MyBoardController.getInstance());
//            }
//        }
    }


//        for(int i = 0; i<size; i++){
//            myTiles.add(new Tile(i));
//            screen.add(myTiles.get(i));
//        }


//    public void placeShip(){
//        List<Tile> tiles = Blackboard.getBlackboard().getTileList();
//
//        //MyBoardController.getInstance().actionPerformed();
//    }

    /**
     * Shows where the opponent has taken a shot
     * @param idx the index of the tile where the opponent took a shot
     */
    public void placeOppShot(Integer idx){
        Tile t = Blackboard.getBlackboard().getTileList().get(idx);
        for (List<Integer> tiles : shipList){
            //check if whole ship is sunken
            int size = tiles.size();
            for (Integer tile : tiles){
                if (Blackboard.getBlackboard().getTileList().get(tile).shot == Tile.ShotType.HIT){
                    size -= 1;
                }
            }
            //change view if whole ship is sunken
            if (size == 0){
                for (Integer tile : tiles) {
                    Blackboard.getBlackboard().getTileList().get(tile).setTileType(Tile.TileType.SUNK);
                    Blackboard.getBlackboard().getTileList().get(tile).updateView();
                }
            }
            //check if hit or miss
            if (tiles.contains(idx)){
                t.setShot(Tile.ShotType.HIT);
            } else{
                t.setShot(Tile.ShotType.MISS);
            }
        }


    }

    public List<List<Integer>> getShipList() {
        return shipList;
    }

    @Override
    public void update(MyObservable ob) {
        if(Blackboard.getBlackboard().getShotIndex() != -1) {
            Integer idx = Blackboard.getBlackboard().getShotIndex();
            placeOppShot(idx);
        }
    }


    /**
     * setting the ships for player 1
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile temp && numShipTiles > 0){
            //prints out which tile was clicked
            System.out.println(temp);

            //placing ships on the screen
            if (((Tile) e.getSource()).tileType != Tile.TileType.SHIP) {
                Tile t = (Tile)e.getSource();
                Blackboard.getBlackboard().getTileList().get(t.getIndex()).setTileType(Tile.TileType.SHIP);
                numShipTiles -= 1;
                System.out.println(Blackboard.getBlackboard().getTileList().get(t.getIndex()));
                System.out.println("Updating view");
                Blackboard.getBlackboard().getTileList().get(t.getIndex()).updateView();

                shipTiles.add(t);
            }
        }
        else if (numShipTiles == 0){
            List<List<Integer>> ships = null;
            try {
                ships = makeShipList(shipTiles);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Blackboard.getBlackboard().updateData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(ships);
        }
    }



    // takes all the ship tiles and turns them into a 2D list of integers representing ships
    public List<List<Integer>> makeShipList(List<Tile> shipTiles) throws IOException {
        List<List<Integer>> shipList = new ArrayList<>();

        shipTiles.sort(new Comparator<Tile>() {
            @Override
            public int compare(Tile o1, Tile o2) {
                return 0;
            }
        });

        while(shipTiles.size() > 0){
            Tile t = shipTiles.get(0);

            Tile up = (t.getIndex() - 10 >=0) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() - 10): new Tile(-1);
            Tile down = (t.getIndex() + 10 >=0) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() + 10): new Tile(-1);
            Tile left = (t.getIndex() - 1 >=0) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() - 1): new Tile(-1);
            Tile right = (t.getIndex() + 1 >=0) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() + 1): new Tile(-1);
            //need to check if in different rows

            //if nothing around ship tile, it is a standalone 1 square ship
            if (up.tileType != Tile.TileType.SHIP && down.tileType != Tile.TileType.SHIP
                    && left.tileType != Tile.TileType.SHIP && right.tileType != Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex()); //to make 2D, put in its own list
                shipList.add(ship); // add to 2D list
                shipTiles.remove(t); //remove from shipTiles to avoid seeing again
            }
            //vertical only ship, ASSUMES WE WILL SEE TOPMOST SHIP FIRST
            else if(down.tileType == Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while(down.tileType == Tile.TileType.SHIP){
                    ship.add(down.getIndex());
                    shipTiles.remove(down);
                    if ((down.getIndex() + 10) <= 99){
                        down = Blackboard.getBlackboard().getTileList().get(down.getIndex() + 10);
                    } else {break;}
                }
                shipList.add(ship);
            }
            //horizontal only ship, ASSUMES WE WILL SEE RIGHTMOST SQUARE FIRST
            else if(right.tileType == Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while(right.tileType == Tile.TileType.SHIP){
                    ship.add(right.getIndex());
                    shipTiles.remove(right);
                    if ((right.getIndex() + 1) <= 99){ //CHECK IF DIFFERENT ROWS HERE
                        right = Blackboard.getBlackboard().getTileList().get(right.getIndex() + 1);
                    } else {break;}
                }
                shipList.add(ship);
            }
        }
        Blackboard.getBlackboard().setMyShipTiles(shipList);
        Blackboard.getBlackboard().updateData();
        return shipList;
    }


}
