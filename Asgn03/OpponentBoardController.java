package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpponentBoardController implements ActionListener {
    OpponentBoard opponentBoard;

    public void setOpponentBoard(OpponentBoard opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        opponentBoard.actionPerformed(e);
    }
}
