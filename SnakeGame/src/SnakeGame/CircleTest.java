package SnakeGame;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jay C on 7/8/2017.
 */
public class CircleTest {
    @org.junit.Test
    public void test1_getX() throws Exception
    {
        Point2 point1 = new Point2(1, 2);
        Point2 point2 = new Point2(3.4, 9);
        Circle cir1 = Circle.generate(point2);
        double actual = cir1.getX();
        double expected = point2.getX();
        assertEquals(expected, actual, 0.01);

    }

    @org.junit.Test
    public void test2_getY() throws Exception
    {
        Point2 point1 = new Point2(1, 2);
        Point2 point2 = new Point2(3.4, 9);
        Circle cir1 = Circle.generate(point2);
        double actual = cir1.getY();
        double expected = point2.getY();
        assertEquals(expected, actual, 0.1);
        cir1.clear();
    }

    @Test
    public void test3_generate()
    {
        Point2 point = new Point2(1, 2);
        Circle cir = Circle.generate(point);
        boolean actual = cir.isGenerate();
        assertTrue(actual);

        point = new Point2(3.0, 9);
        cir = Circle.generate(point);
        actual = cir.isGenerate();
        assertFalse(actual);

        point = new Point2(3.4, 4);
        cir = Circle.generate(point);
        actual = cir.isGenerate();
        assertFalse(actual);


    }

}