package Asgn03;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import javax.swing.*;
import java.awt.*;

/**
 * Assignment 03
 *
 * @author Jamie Luna
 * @version 1.0
 * BoardPanel - Abstraction to create hierarchy for different Board objects.
 */
public class BoardPanel extends JPanel implements MyObserver { //implements MouseEvent
    /**
     * BoardPanel constructor - calls JPanel constructor
     */
    public BoardPanel() {
        super();
    }

    /**
     * Following the Observer patter, BoardPanel has an update() method
     * since it is an observer
     * @param ob the blackboard object that has been updated
     */
    @Override
    public void update(MyObservable ob) {

    }
}
