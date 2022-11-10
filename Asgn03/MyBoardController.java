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
        myboard.actionPerformed(e);
    }
}
