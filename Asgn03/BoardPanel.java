package Asgn03;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Assignment 03
 *
 * @author Jamie Luna
 * @version 1.0
 * BoardPanel - Abstraction to create hierarchy for different Board objects.
 */
public class BoardPanel extends JPanel implements MyObserver { //implements MouseEvent
    MyBoardController myBoardController;
    OpponentBoardController opponentBoardController;
    List<Tile> genericList = new ArrayList<>();
    /**
     * BoardPanel constructor - calls JPanel constructor
     */
    public BoardPanel(String boardType) {
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
                genericList.add(new Tile(value));
                add(genericList.get(genericList.size() -1));
                if (boardType.equals("MyBoard")){
                    genericList.get(genericList.size() -1).addActionListener(myBoardController);
                } else if (boardType.equals("OpponentBoard")){
                    genericList.get(genericList.size() -1).addActionListener(opponentBoardController);
                }
                value += 1;
            }
        }
    }

    public List<Tile> getGenericList() {
        return genericList;
    }

    /**
     * Following the Observer pattern, BoardPanel has an update() method
     * since it is an observer
     * @param ob the blackboard object that has been updated
     */
    @Override
    public void update(MyObservable ob) {

    }
}
