package SnakeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

/**
 * Created by Jay C on 7/7/2017.
 */
public class SnakeController
{

    private SnakeController()
    {
        SnakeView view = new SnakeView(640, 480);
        view.play();
    }

    public static void main(String[] args) {
        SnakeController x = new SnakeController();

    }
}
