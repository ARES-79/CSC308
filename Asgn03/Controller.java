package Asgn03;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private static Controller controller;

    protected Controller(){}

    public static Controller getInstance(){
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getActionCommand());
        if (e.getSource() instanceof Tile){
            System.out.println( ((Tile) e.getSource()).getIndex() );
        }
    }
}
