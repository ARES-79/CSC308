package Asgn03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/** CURRENTLY NOT IN USE*/
public class SendShotController implements ActionListener {

    private static SendShotController sendShotController;
    private Client client;

    protected SendShotController() {}

    public void setClient(Client client){
        this.client = client;
    }

    /**
     * Singleton Pattern getInstance()
     *
     * @return static Controller instance
     */
    public static SendShotController getInstance() {
        if (sendShotController == null) {
            sendShotController = new SendShotController();
        }
        return sendShotController;
    }

    /**
     * actionPerformed - implementation from ActionListener interface
     *      provides interactivity for the shooting element of the battleship game
     * @param e ActionEvent notifying that a screen item has been selected
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Tile) {
            //prints out which tile was clicked
            Tile temp = (Tile) e.getSource();
            System.out.println("From sendshotcontroller" + temp);
            if (temp.getShot() != Tile.ShotType.DEFAULT){

            }
        }
    }
}
