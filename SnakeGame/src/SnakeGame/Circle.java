package SnakeGame;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jay C on 7/8/2017.
 */
public class Circle
{
    private Point2 position;
    private static boolean isGenerate;
    //field
    private static final int DELTA = 50;
    private static final Map<String, Circle> circles = new HashMap();

    private Circle(Point2 pos)
    {
        position = new Point2(pos);
        isGenerate = true;
    }

    public void clear()
    {
        circles.clear();
    }
    public static Circle generate(Point2 pos)
    {
        Circle cir = new Circle(pos);
        String key = pos.toString();
        boolean containValue = circles.containsValue(pos);
        boolean inRange = inRange(cir);

        if(inRange == false && containValue == false)
        {
            circles.put(key, cir);
        }
        else
            isGenerate = false;

        System.out.println(circles.size());
        return cir;
    }

    public static void main(String[] args)
    {
        Point2 point = new Point2(1, 2);
        Circle cir = Circle.generate(point);
        boolean actual = cir.isGenerate();
        System.out.println( actual );

        point = new Point2(3, 4);
        cir = Circle.generate(point);
        actual = cir.isGenerate();

        System.out.println(actual);


        point = new Point2(3.4, 4);
        cir = Circle.generate(point);
        actual = cir.isGenerate();

        System.out.println(actual);

    }
    private static boolean checkBoundaryX(Circle c, int x)
    {
        /*if(c.getX() < x && c.getX() > x + DELTA)
            return true;
        return false;*/

        return (c.getX() > x && c.getX() < x + DELTA) ? true : false;

    }

    private static boolean checkBoundaryY(Circle c, int y)
    {
        return (c.getY() > y && c.getY() < y+ DELTA) ? true : false;
    }
    private static boolean inRange(Circle circle)
    {
        boolean result = false;
        boolean xBound = false;
        boolean yBound = false;
        int valueX = 0;
        int valueY = 0;
        if(circles.size() > 0)
        {
            for (Circle cir : circles.values())
            {
                valueX = (int) Math.ceil(cir.getX());
                valueY = (int) Math.ceil(cir.getY());

                xBound = checkBoundaryX(circle, valueX);
                yBound = checkBoundaryY(circle, valueY);

                if (xBound == true || yBound == true)
                    result = true;
            }
        }
        return result;
    }

    public double getX()
    {
        return (position.getX());
    }

    public double getY()
    {
        return (position.getY());
    }

    public int getDelta()
    {
        return this.DELTA;
    }

    public boolean isGenerate()
    {
        return isGenerate;
    }
}
