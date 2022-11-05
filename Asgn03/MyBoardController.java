package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Assignment 03
 * @author Andrew Estrada
 * ...
 * @version 1.0
 * Controller Class - the controller of the MVC pattern
 *          responsible for connecting the view and the model
 *          Singleton pattern
 */
public class MyBoardController implements ActionListener {
    //boolean ready = false;
    int numShipTiles = 10;
    List<Tile> tiles = Blackboard.getBlackboard().getTileList();
    List<List<Integer>> shipList = new ArrayList<>();

    private static MyBoardController myBoardController;

    protected MyBoardController(){}

    /**
     * Singleton Pattern getInstance()
     * @return static Controller instance
     */
    public static MyBoardController getInstance(){
        if (myBoardController == null){
            myBoardController = new MyBoardController();
        }
        return myBoardController;
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides interactivity for the BattleShip elements
     * @param e ActionEvent notifying that a screen item has been selected
     */

    /**
     * setting the ships for player 1
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Tile> shipTiles = new ArrayList<>();
//       System.out.println(e.getActionCommand());
        if (e.getSource() instanceof Tile && numShipTiles > 0){
            //prints out which tile was clicked
            Tile temp = (Tile) e.getSource();
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
//                if (shipList.size() == 0){
//                    ArrayList<Tile> ship1 = new ArrayList<>();
//                    ship1.add(t);
//                    shipList.add(ship1);
//                }
            }
        }
        else if (numShipTiles == 0){
            List<List<Integer>> ships = makeShipList(shipTiles);
            System.out.println(ships);
        }
    }

    // takes all the ship tiles and turns them into a 2D list of integers representing ships
    //SHOULD PROBABLY BE MOVED INTO MYBOARD CLASS
    public List<List<Integer>> makeShipList(List<Tile> shipTiles){

        List<List<Integer>> shipList = new ArrayList<>();
//        List<Integer> ship = new ArrayList<>();
//        ship.add(shipTiles.get(0).getIndex());
        while(shipTiles.size() > 0){
            System.out.println(shipTiles);
            Tile t = shipTiles.get(0);

            Tile up = Blackboard.getBlackboard().getTileList().get(t.getIndex() - 10);
            Tile down = Blackboard.getBlackboard().getTileList().get(t.getIndex() + 10);
            Tile left = Blackboard.getBlackboard().getTileList().get(t.getIndex() - 1);
            Tile right = Blackboard.getBlackboard().getTileList().get(t.getIndex() + 1);
            //need to check if in different rows

            //if nothing around ship tile, it is a standalone 1 square ship
            if (up.tileType != Tile.TileType.SHIP && down.tileType != Tile.TileType.SHIP
                && left.tileType != Tile.TileType.SHIP && right.tileType != Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex()); //to make 2D, put in its own list
                shipList.add(ship); // add to 2D list
                shipTiles.remove(t.getIndex()); //remove from shipTiles to avoid seeing again
            }
            //vertical only ship, ASSUMES WE WILL SEE TOPMOST SHIP FIRST
            else if(down.tileType == Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while(down.tileType == Tile.TileType.SHIP){
                    ship.add(down.getIndex());
                    shipTiles.remove(down);
                    down = t;
                }
                shipList.add(ship);
            }
            //horizontal only ship, ASSUMES WE WILL SEE RIGHTMOST SQUARE FIRST
            else if(right.tileType == Tile.TileType.SHIP){
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while(t.tileType == Tile.TileType.SHIP){
                    ship.add(right.getIndex());
                    shipTiles.remove(down);
                    right = t;
                }
                shipList.add(ship);
            }

        }
        System.out.println(shipList);
        return shipList;
    }
}
