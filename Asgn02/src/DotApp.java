package Asgn02.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 02
 * @author Andrew Estrada
 * @author Jamie Luna
 * @author Mitashi Parikh
 * @version 1.0
 * DotApp Class - a dot plotting GUI with cluster and line capabilities
 */
public class DotApp extends JFrame implements ActionListener {
    DrawArea center;
    JCheckBox cluster;
    JCheckBox line;

    /**
     * Main creates a new DotApp window and allows it to be seen and closed properly.
     */
    public static void main(String[]args){
        DotApp window = new DotApp();
        window.setSize(900,500);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * DotApp constructor
     * Uses inheritance from JFrame to create a window with the elements
     * necessary for a GUI with dot plotting functionality.
     */
    public DotApp(){
        super("My Dot App");
        setLayout(new BorderLayout());

        //west
        JPanel selectionPanel = new JPanel();
        cluster = new JCheckBox("Cluster - K-means");
        line = new JCheckBox("Line - Nearest Neighbor");
        JButton run = new JButton("Run");
        selectionPanel.setLayout(new GridLayout(6, 1));
        selectionPanel.add(cluster);
        selectionPanel.add(line);
        selectionPanel.add(run);
        add(selectionPanel, BorderLayout.WEST);

        //center
        center = new DrawArea();
        add(center, BorderLayout.CENTER);
        center.setBackground(Color.ORANGE);
        center.setVisible(true);
        center.setOpaque(true);

        //listeners
        cluster.addActionListener(this);
        line.addActionListener(this);
        run.addActionListener(this);

    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides interactivity for the Dot App elements
     *      also logs what is the user is doing
     * @param e ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if (!e.getActionCommand().equals("Run"))
            System.out.println(((JCheckBox)e.getSource()).isSelected() ) ;
        else{
            List<String> options = new ArrayList<>();
            if (cluster.isSelected())
                options.add("Cluster");
            if (line.isSelected())
                options.add("Line");
            center.setOptionsSelected(options);
            //Call method to perform desired operations
        }
    }

}
