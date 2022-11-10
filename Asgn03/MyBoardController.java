package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyBoardController implements ActionListener {
    MyBoard myboard;

    public void setMyboard(MyBoard myboard) {
        this.myboard = myboard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile){
            System.out.println(e.getSource());
            myboard.placeShipPiece((Tile) e.getSource());
        }
    }
}
