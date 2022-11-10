package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Assignment 03
 *
 * @author Jamie Luna
 * @version 1.0
 * MyBoard - controls the logic behind placing ships and seeing where your opponent shoots on your board
 */
public class MyBoard extends BoardPanel implements ActionListener, MyObserver {
    private int numShipTiles = 17;
    private final List<List<Integer>> shipList = new ArrayList<>();
    private final ArrayList<Tile> shipTiles = new ArrayList<>();

    /**
     * MyBoard constructor - adds an Observer to know when an opponent clicks this board
     */
    public MyBoard() {
        super();
        Blackboard.getBlackboard().addObserver(this);
        setLayout(new GridLayout(11, 11, -1, -1));
        setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        Blackboard.getBlackboard().addObserver(this);

        add(new JLabel(""));
        int value = 0;
        for (int i = 1; i < 121; i++) {
            if (i < 11) {
                add(new JLabel("     " + i));
            } else if (i % 11 == 0) {
                int alpha = (i % 10 == 0) ? 10 : i % 10;
                add(new JLabel("   " + (char) (alpha + 64)));
            } else {
                Blackboard.getBlackboard().addTile(new Tile(value));
                value += 1;
                add(Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() - 1));
                Blackboard.getBlackboard().getTileList().get(Blackboard.getBlackboard().getTileList().size() - 1).addActionListener(this);
            }
        }
    }

    /**
     * Shows where the opponent has taken a shot
     *
     * @param idx the index of the tile where the opponent took a shot
     */
    public void placeOppShot(Integer idx) {
        Tile t = Blackboard.getBlackboard().getTileList().get(idx);
        for (List<Integer> tiles : shipList) {
            //check if whole ship is sunken
            int size = tiles.size();
            for (Integer tile : tiles) {
                if (Blackboard.getBlackboard().getTileList().get(tile).shot == Tile.ShotType.HIT) {
                    size -= 1;
                }
            }
            //change view if whole ship is sunken
            if (size == 0) {
                for (Integer tile : tiles) {
                    Blackboard.getBlackboard().getTileList().get(tile).setTileType(Tile.TileType.SUNK);
                    Blackboard.getBlackboard().getTileList().get(tile).updateView();
                }
            }
            //check if hit or miss
            if (tiles.contains(idx)) {
                t.setShot(Tile.ShotType.HIT);
            } else {
                t.setShot(Tile.ShotType.MISS);
            }
        }


    }

    /**
     * Places the opponents shot on the screen whenever they take a shot
     *
     * @param ob the blackboard object that has been updated
     */
    @Override
    public void update(MyObservable ob) {
        if (Blackboard.getBlackboard().isReceivedShips()) {
            Integer idx = Blackboard.getBlackboard().getShotIndex();
            placeOppShot(idx);
        }
    }

    private boolean checkIfTileInShipList(List<List<Integer>> shipList, int checkTile) {
        for (List<Integer> ship : shipList) {
            for (int tile : ship) {
                if (checkTile == tile) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * setting the ships for player 1
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile temp && numShipTiles >= 0) {
            //prints out which tile was clicked
            System.out.println(temp);

            //placing ships on the screen
            if (((Tile) e.getSource()).tileType != Tile.TileType.SHIP && !Blackboard.getBlackboard().isGameOver()) {
                Tile t = (Tile) e.getSource();
                Blackboard.getBlackboard().getTileList().get(t.getIndex()).setTileType(Tile.TileType.SHIP);
                numShipTiles -= 1;
                System.out.println(Blackboard.getBlackboard().getTileList().get(t.getIndex()));
                System.out.println("Updating view");
                Blackboard.getBlackboard().getTileList().get(t.getIndex()).updateView();

                shipTiles.add(t);
            }
            if (numShipTiles == 0) {
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
                numShipTiles -= 1;
                System.out.println(ships);
            }
        }
    }


    /**
     * Takes all the ship tiles and turns them into a 2D list of integers representing ships
     *
     * @param shipTiles the tiles that have been turned into ships
     * @return the list of list of indices ships grouped together
     * @throws IOException
     */
    public List<List<Integer>> makeShipList(List<Tile> shipTiles) throws IOException {
        List<List<Integer>> shipList = new ArrayList<>();

        Collections.sort(shipTiles, (s1, s2) -> (int) (s1.getIndex() - s2.getIndex()));

        while (shipTiles.size() > 0) {
            Tile t = shipTiles.get(0);


            Tile up = (t.getIndex() - 10 >= 0) && (t.getIndex() - 10 <= 99) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() - 10) : new Tile(-1);
            Tile down = (t.getIndex() + 10 >= 0) && (t.getIndex() + 10 <= 99) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() + 10) : new Tile(-1);
            Tile left = (t.getIndex() - 1 >= 0) && (t.getIndex() - 1 <= 99) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() - 1) : new Tile(-1);
            Tile right = (t.getIndex() + 1 >= 0) && (t.getIndex() + 1 <= 99) ? Blackboard.getBlackboard().getTileList().get(t.getIndex() + 1) : new Tile(-1);


            //if nothing around ship tile, it is a standalone 1 square ship
            if ((up.tileType != Tile.TileType.SHIP || checkIfTileInShipList(shipList, up.getIndex())) &&
                    (down.tileType != Tile.TileType.SHIP || checkIfTileInShipList(shipList, down.getIndex())) &&
                    (left.tileType != Tile.TileType.SHIP || checkIfTileInShipList(shipList, left.getIndex())) &&
                    (right.tileType != Tile.TileType.SHIP || checkIfTileInShipList(shipList, right.getIndex()))) {
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex()); //to make 2D, put in its own list
                shipList.add(ship); // add to 2D list
                shipTiles.remove(t); //remove from shipTiles to avoid seeing again
            }

            //vertical only ship, ASSUMES WE WILL SEE TOPMOST SHIP FIRST
            else if (down.tileType == Tile.TileType.SHIP) {

                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while (down.tileType == Tile.TileType.SHIP && !checkIfTileInShipList(shipList, down.getIndex())) {
                    ship.add(down.getIndex());
                    shipTiles.remove(down);
                    if ((down.getIndex() + 10) <= 99) {
                        down = Blackboard.getBlackboard().getTileList().get(down.getIndex() + 10);
                    } else {
                        break;
                    }
                }
                shipList.add(ship);
            }
            //horizontal only ship, ASSUMES WE WILL SEE LEFTMOST SQUARE FIRST
            else if (right.tileType == Tile.TileType.SHIP) {
                ArrayList<Integer> ship = new ArrayList<>();
                ship.add(t.getIndex());
                shipTiles.remove(t);
                while (right.tileType == Tile.TileType.SHIP && (Math.floorDiv(t.getIndex(), 10) == Math.floorDiv(right.getIndex(), 10)) && !checkIfTileInShipList(shipList, right.getIndex())) {
                    ship.add(right.getIndex());
                    shipTiles.remove(right);
                    if ((right.getIndex() + 1) <= 99 && (Math.floorDiv(t.getIndex(), 10) == Math.floorDiv(right.getIndex(), 10))) { //CHECK IF DIFFERENT ROWS HERE
                        right = Blackboard.getBlackboard().getTileList().get(right.getIndex() + 1);
                    } else {
                        break;
                    }
                }
                shipList.add(ship);
            }
        }
        Blackboard.getBlackboard().setMyShipTiles(shipList);
        Blackboard.getBlackboard().setReadyToSendShips(true);
        return shipList;
    }

}
