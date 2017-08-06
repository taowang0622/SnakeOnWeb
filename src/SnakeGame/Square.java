package SnakeGame;

import java.awt.*;

/**
 * Created by Jay C on 7/25/2017.
 */
public class Square
{
    private int x, y, size;
    public Square(int size)
    {
        this.size = size;
    }
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy)
    {
        x += dx;
        y += dy;
    }

    public Rectangle getBound()
    {
        return new Rectangle(x, y, size, size);
    }

    public boolean isCollsion(Square o)
    {
        if(o == this)
        {
            return false;
        }
        if(o == null)
            return false;
        return getBound().intersects(o.getBound());
    }

    public void render(Graphics2D graphics)
    {
        graphics.fillRect(x + 1, y +1, size - 2, size - 2);
    }


    @Override
    public String toString()
    {
        return "(" + this.getX() + ", " + this.getY() + ")";
    }
}
