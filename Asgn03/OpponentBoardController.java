package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpponentBoardController implements ActionListener {
    OpponentBoard opponentBoard;

    public void setOpponentBoard(OpponentBoard opponentBoard) {
        this.opponentBoard = opponentBoard;
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides functionality for tile objects within the opponent board
     * @param e - ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile && Blackboard.getBlackboard().isMyTurn() && Blackboard.getBlackboard().isReceivedShips()
                && Blackboard.getBlackboard().isSentShips() && !Blackboard.getBlackboard().isGameOver()) {
            System.out.println(e.getSource());
            opponentBoard.shootTile((Tile) e.getSource());
        }
    }
}
