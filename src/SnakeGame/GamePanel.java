package SnakeGame;

import SnakeGUI.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {
    private static int WIDTH = 650;
    private static int HEIGHT = 500;
    //render
    private Graphics2D g2d;
    private BufferedImage image;

    //game loop
    public Thread thread;
    private boolean running ;
    private long targetTime;

    //game stuff
    private final int SIZE=12;
    private Square head;
    private Square food;
    private Square poison;
    private ArrayList<Square> snake;
    private int score;
    private int level;
    private boolean gameOver;
    private final int STOP = 1000000;
    private boolean exit;
    //movement
    private int dx,dy;
    public long wait;

    //outer class
    private modelGUI modelGUI;

    //key input
    private boolean up,down,right,left,start;
    public GamePanel(modelGUI modelGUI)
    {
        this.modelGUI = modelGUI;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }
    @Override
    public void addNotify() {
        super.addNotify();
        thread=new Thread(this);
        thread.start();
    }
    private void setFPS(int fps){
        targetTime=1000/fps;

    }
    public boolean getGameOver()
    {
        return gameOver;
    }

    public int getScore()
    {
        return this.score;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k=e.getKeyCode();
        //System.out.println(k);
        if(k== 38) up=true;
        if(k== 40) down=true;
        if(k== 37) left=true;
        if(k== 39) right=true;
        if(k== 10) right=true;


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k=e.getKeyCode();
        if(k== 38) up=false;
        if(k== 40) down=false;
        if(k== 37) left=false;
        if(k== 39) right=false;
        if(k== 10) right=false;



    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        if(running) return;
        init();
        long startTime;
        update();
        requestRender();
        long elapsed;
        exit = false;
        while(running && !exit){
            startTime=System.nanoTime();

            update();
            requestRender();

            elapsed=System.nanoTime()-startTime;
            wait=targetTime-elapsed/1000000;
            if(wait>0){

                if(gameOver)
                {
                    exit = true;
                    //wait = STOP;
                }
                try{
                    Thread.sleep(wait);
                }catch(Exception e  ){
                    e.printStackTrace();
                }
            }
        }
    }
    private void init(){
        image =new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d=image.createGraphics();
        running =true;
        setupLevel();
        gameOver = false;
        level = 1;
        setFPS(level * 10);
    }
    public void setupLevel(){
        snake =new ArrayList<Square>();
        head=new Square(SIZE);
        head.setPosition(WIDTH/2, HEIGHT/2);
        snake.add(head);

        for (int i = 1; i < 3; i++){
            Square e=new Square(SIZE);
            e.setPosition(head.getX()+(i*SIZE),head.getY());
            snake.add(e);
        }
        food = new Square(SIZE);
        setFood();
        score = 100;

        /*poison = new Square(SIZE);
        setPoison();*/

    }

    public void setFood()
    {
        int x = (int)(Math.random() * (WIDTH - SIZE));
        int y = (int)(Math.random() * (HEIGHT - SIZE));

        x = x - (x % SIZE);
        y = y - (y % SIZE);
        food.setPosition(x, y);
    }

    /*public void setPoison()
    {
        int x = (int)(Math.random() * (WIDTH - SIZE));
        int y = (int)(Math.random() * (HEIGHT - SIZE));
        poison.setPosition(x, y);
    }*/
    private void requestRender() {
        render(g2d);
        Graphics g=getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }
    private void update()
    {
        if(gameOver)
        {
            if(start)
            {
                setupLevel();
            }
        }
        if(up && dy==0){
            dy=-SIZE;
            dx=0;
        }
        if(down  && dy==0){
            dy=SIZE;
            dx=0;
        }
        if(left && dx==0){
            dy=0;
            dx=-SIZE;
        }
        if(right && dx==0 && dy != 0)
        {
            dy=0;
            dx=SIZE;
        }
        if(dx!=0 || dy!=0){
            for (int i=snake.size() - 1;i>0;i--){
                snake.get(i).setPosition(
                        snake.get(i-1).getX(),
                        snake.get(i-1).getY()
                );

            }
            head.move(dx, dy);
        }

        //check if the snake collides with itself
        for(Square e : snake)
        {
            if(e.isCollsion(head))
            {
                gameOver = true;
                setGameOver();
            }
        }


        if(food.isCollsion(head))
        {
            score++;
            setScore();

            setFood();

            Square e=new Square(SIZE);
            System.out.println(e);
            e.setPosition(-50, -100);
            snake.add(e);

            if(score % 10 == 0)
            {
                level ++;
                if(level > 10)
                    level = 10;
                setFPS(level * 10);

            }
        }
        if(head.getX()<0) head.setX(WIDTH);
        if(head.getY()<0) head.setY(HEIGHT);
        if(head.getX()>WIDTH) head.setX(0);
        if(head.getY()>HEIGHT) head.setY(0);
    }
    public void render(Graphics2D g2d) {
        g2d.clearRect(0, 0, WIDTH, HEIGHT);

        g2d.setColor(Color.GREEN);
        for (Square e : snake)
        {
            e.render(g2d);

        }

        g2d.setColor(Color.RED);
        food.render(g2d);


        if(gameOver)
        {
            g2d.drawString("GameOver!", 150, 200);
        }



        g2d.setColor(Color.WHITE);
        g2d.drawString("Score : " + score + " Level : " + level, 10, 10 );

        if(dx == 0 && dy == 0)
        {
            g2d.drawString("Ready!", 150, 200);
        }
    }

    public void setScore()
    {
        modelGUI.setScore(this.score);
    }

    public void setGameOver()
    {
        modelGUI.setGameOver(this.gameOver);
    }
}