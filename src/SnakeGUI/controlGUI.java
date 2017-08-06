package SnakeGUI;

import SnakeGame.GamePanel;
import sun.awt.SunHints;

import javax.swing.*;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by victo on 7/24/2017.
 */
public class controlGUI {

    private modelGUI myModel;

    public controlGUI(){

        this.myModel = new modelGUI();

    }

    public void openingDial(){

        final JFrame welcome = new JFrame();

        int result = JOptionPane.showConfirmDialog(welcome," Welcome to Snake on Web!!!  Would you like to login ? \n"+" (Therefore you can submit your highest scores as well as enjoy Online Multi-player mode)","Snake on Web",JOptionPane.YES_NO_OPTION);

        if(result == 0) {
            System.out.print("Yes, Login");
            loginDial();
        }
        else if(result == 1) {
            System.out.println("No, skip");
        }

    }

    public void loginDial(){

        final JFrame signIn = new JFrame();

        String userID = JOptionPane.showInputDialog(signIn, "Please type in your user name!");

        modelGUI.setUserID(userID);

        System.out.printf("The user's name is '%s'.\n", modelGUI.getUserID());

    }

    public int singleDial(){

        final JFrame startGame = new JFrame();

        int result = JOptionPane.showConfirmDialog(startGame," Ready to Start ?","Snake on Web",JOptionPane.YES_NO_OPTION);

        return result;
    }

    public int multiDial(){

        final JFrame startGame = new JFrame();

        int result = JOptionPane.showConfirmDialog(startGame," Look for online players ?","Snake on Web",JOptionPane.YES_NO_OPTION);

        return result;

    }

    public void scoreDial(){

        final JFrame scoreShow = new JFrame();


        //myModel.serverData.forEach( k -> System.out.println("Name: "+ k + "----- Score: "));

        String s = "" + myModel.getScore();

        JOptionPane.showMessageDialog(scoreShow, s);

    }

    public GamePanel startGame(){
        myModel.game = new GamePanel(this.myModel);
        return myModel.game;
    }

    public static void main(String[] args) {

/*        controlGUI control = new controlGUI();

        control.scoreDial();*/
    }

}
