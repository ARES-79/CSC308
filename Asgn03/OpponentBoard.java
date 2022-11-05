package Asgn03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;

public class OpponentBoard extends BoardPanel {

    List<Tile> enemyWaters = new ArrayList<>();

    public OpponentBoard() {
        setLayout(new GridLayout(11,11, -1, -1));
        setBorder(BorderFactory.createEmptyBorder(2,2,2,2));

        //List<Tile> tileList = new ArrayList<>();

        add(new JLabel(""));
        int value = 0;
        for( int i = 1; i<121; i++) {
            if (i < 11){
                add(new JLabel("     " + i));
            }
            else if (i % 11 == 0){
                int alpha = (i%10 == 0) ? 10 : i%10;
                add(new JLabel("   " + Character.toString((char) (alpha + 64))));
            }
            else{
                enemyWaters.add(new Tile(value));
                add(enemyWaters.get(enemyWaters.size() -1));
                enemyWaters.get(enemyWaters.size() -1).addActionListener(SendShotController.getInstance());
                value +=1;
            }
        }
    }

    @Override
    public void update(MyObservable ob) {

    }

}
