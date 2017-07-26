package SnakeGUI;

import SnakeGame.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by victo on 7/24/2017.
 */
public class mainGUI extends JFrame{

    //Connection to Controller
    private static controlGUI myControl = new controlGUI();

        //Startup Windows Size
        public static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        private int jFrameWidth = (int) (screenDimension.width * 0.6);
        private int jFrameHeight = (int) (screenDimension.height * 0.6);

        //Scale screen size to 50% of the client's screen resolution
        //intro Screen

        private Dimension screenSizeDefault = new Dimension(jFrameWidth, jFrameHeight);

        private static Dimension singleSizeDefault = new Dimension((int) (screenDimension.width * 0.5), (int) (screenDimension.height * 0.6));
        private static Dimension multiSizeDefault = new Dimension((int) (screenDimension.width * 0.75), (int) (screenDimension.height * 0.6));

        Container contentPane;


        public mainGUI() {

            super("Second Version");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //this.setMinimumSize(screenSizeDefault);
            this.setLocation(jFrameWidth / 3, jFrameHeight / 4);
            this.pack();
            this.setBackground(Color.yellow);

            contentPane = getContentPane();

            myControl.openingDial();
            introGui();

        }

        /**
         * Helper class / Inner Class
         *
         * This is a game place holder that will draw the snake game inside the GUI.
         */
        //====================================================================================
        public static class gameArea extends JPanel
        {

            public gameArea() {

                /*int w = singleSizeDefault.width - 200;

                int h = singleSizeDefault.height - 200;*/


                add(mainGUI.myControl.startGame());


                // add(mainGUI.myControl.startGame();


                //setPreferredSize(new Dimension(w,h));
            }

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                String a = "This is the width = " + singleSizeDefault.width;
                g.drawString(a, 20, 20);

            }
        }

        //=======================================================================================

    // Graphical User Interface ================================================================

        public void introGui() {

            //main page

            contentPane.removeAll();

            super.setSize(jFrameWidth, jFrameHeight);
            setVisible(true);

            JPanel intro = new JPanel(new GridBagLayout());

            contentPane.add(intro);
            intro.setSize(screenSizeDefault);
            intro.setBackground(new Color(0,0,0,0));

            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("src/image/snakeIntro.gif"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            BackgroundPanel panel = new BackgroundPanel(image, BackgroundPanel.SCALED, 0.0f, 0.0f);
            //GradientPaint paint = new GradientPaint(0, 0, Color.BLUE, 600, 0, Color.RED);
            //panel.setPaint(paint);
            contentPane.add(panel);

            //Layout
            GridBagConstraints introGrid = new GridBagConstraints();
            introGrid.anchor = GridBagConstraints.FIRST_LINE_START;


            //Button : Login
            introGrid.insets = new Insets(20, 50, 0, 0);
            introGrid.gridx = 0;
            introGrid.gridy = 0;
            //introGrid.weighty = 1;

            //Make button taller and wider
            introGrid.ipadx = 150;
            introGrid.ipady = 20;

            JButton buttonLogin = loginButton();
            intro.add(buttonLogin, introGrid);

            introGrid.anchor = GridBagConstraints.CENTER;

            //Button : Score
            introGrid.insets = new Insets(20, 0, 0, 0);
            introGrid.gridx = 1;
            introGrid.gridy = 0;
            //introGrid.weighty = 1;

            JButton buttonScore = scoreButton();
            intro.add(buttonScore, introGrid);

            //Reset ipad size
            introGrid.ipadx = 0;
            introGrid.ipady = 0;

            //Button 1 single player
            introGrid.insets = new Insets(0, 0, -200, 100);
            introGrid.gridx = 0;
            introGrid.gridy = 1;
            introGrid.weighty = 1;

            JButton buttonSingle = singleButton();
            intro.add(buttonSingle, introGrid);

            //Button 2 Multi player
            introGrid.insets = new Insets(0, 0, -200, 0);
            introGrid.gridx = 1;
            introGrid.gridy = 1;
            introGrid.weighty = 1;

            JButton buttonMulti = multiButton();
            intro.add(buttonMulti, introGrid);

            contentPane.validate();
            contentPane.repaint();

        }

        public void singlePlay() {

            contentPane.removeAll();

            super.setSize((int) singleSizeDefault.getWidth(), (int) singleSizeDefault.getHeight());
            setVisible(true);

            JPanel singleMode = new JPanel(new GridBagLayout());
            GridBagConstraints singleGrid = new GridBagConstraints();
            singleGrid.anchor = GridBagConstraints.NORTH;

            contentPane.add(singleMode);

            singleMode.setBackground(Color.blue);


            // title
            JLabel label1 = new JLabel("Single Player Mode");
            singleGrid.gridx = 0;
            singleGrid.gridy = 0;
            singleGrid.weighty = 1;

            singleMode.add(label1, singleGrid);

            // Game Area
            singleGrid.gridx = 0;
            singleGrid.gridy = 1;

            //Calling inner class (game place holder)
            gameArea area = new gameArea();
            singleMode.add(area, singleGrid);

            System.out.println(mainGUI.myControl.startGame().getScore());
            System.out.println(mainGUI.myControl.startGame().getGameOver());

            //Quit button
            singleGrid.gridx = 0;
            singleGrid.gridy = 2;

            singleMode.add(quitButton(), singleGrid);

            validate();

        }


        public void multiPlay() {


            contentPane.removeAll();

            super.setSize((int) multiSizeDefault.getWidth(), (int) multiSizeDefault.getHeight());
            setVisible(true);

            JPanel multiMode = new JPanel(new GridBagLayout());
            GridBagConstraints multiGrid = new GridBagConstraints();
            multiGrid.anchor = GridBagConstraints.NORTH;

            contentPane.add(multiMode);

            multiMode.setBackground(Color.green);


            // title
            JLabel label1 = new JLabel("Multi Player Mode");
            multiGrid.gridx = 0;
            multiGrid.gridy = 0;
            multiGrid.weighty = 1;

            multiMode.add(label1, multiGrid);

            // Game Area 1
            multiGrid.gridx = 0;
            multiGrid.gridy = 1;

            gameArea area = new gameArea();
            multiMode.add(area, multiGrid);

            // Game Area 2

            multiGrid.insets = new Insets(0, 50, 0, 0);
            multiGrid.gridx = 1;
            multiGrid.gridy = 1;

            gameArea area2 = new gameArea();
            multiMode.add(area2, multiGrid);


            //Quit button
            multiGrid.gridx = 0;
            multiGrid.gridy = 2;

            multiMode.add(quitButton(), multiGrid);

            validate();

        }

        // ==========================================================================

        // BUTTON ====================================================================

    public JButton loginButton(){

        JButton account = new JButton("Login");

        if (modelGUI.getUserID() == null) {
            account.setText("Login");}
        else{
            account.setText(modelGUI.getUserID());
        }

        account.setVerticalTextPosition(AbstractButton.BOTTOM);
        account.setHorizontalTextPosition(AbstractButton.CENTER);

        account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myControl.loginDial();
                if (modelGUI.getUserID() == null) {
                    account.setText("Login");}
                else{
                    account.setText(modelGUI.getUserID());
                }

            }
        });

        return account;
    }

    public JButton scoreButton(){
        JButton score = new JButton("ScoreBoard");
        score.setVerticalTextPosition(AbstractButton.BOTTOM);
        score.setHorizontalTextPosition(AbstractButton.CENTER);

        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Pop up a new JPanel on top of the current Panel to show scoreboard
                myControl.scoreDial();
            }
        });
        return score;
    }

    public JButton singleButton(){

        ImageIcon singleIcon = new ImageIcon("src/image/icon/single.png");
        JButton single = new JButton("Single",singleIcon);
        single.setVerticalTextPosition(AbstractButton.BOTTOM);
        single.setHorizontalTextPosition(AbstractButton.CENTER);
        //single.setBackground(Color.);

        single.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myControl.singleDial()==0){
                    singlePlay();
                }
            }
        });

        return single;
    }

    public JButton multiButton(){

        ImageIcon multiIcon = new ImageIcon("src/image/icon/multi.png");
        JButton multi = new JButton("Multi",multiIcon);
        multi.setVerticalTextPosition(AbstractButton.BOTTOM);
        multi.setHorizontalTextPosition(AbstractButton.CENTER);

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myControl.multiDial()==0){
                    multiPlay();
                }
            }
        });

        return multi;
    }

    public JButton quitButton(){

        // ImageIcon singleIcon = new ImageIcon("src/image/icon/single.png");
        JButton quit = new JButton("QUIT");
        // quit.setVerticalTextPosition(AbstractButton.BOTTOM);
        // quit.setHorizontalTextPosition(AbstractButton.CENTER);

        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introGui();
            }
        });

        return quit;
    }

    //===================================================================================



    public static void main(String[] args) {


    }

}
