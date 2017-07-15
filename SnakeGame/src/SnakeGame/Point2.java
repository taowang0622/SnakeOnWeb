package SnakeGame;

/**
 * Created by Jay C on 7/8/2017.
 */
import java.util.Objects;

/**
 * A simple class for representing points in 2D Cartesian coordinates. Every
 * <code>Point2D</code> instance has an x and y coordinate.
 *
 * @author EECS2030 Winter 2016-17
 *
 */
public class Point2
{

    private double x;
    private double y;

    /**
     * Initialize the point to have coordinates <code>(0, 0)</code>.
     */
    public Point2() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Initialize the point to have coordinates <code>(x, y)</code>.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a point to have the same coordinates as <code>other</code>.
     *
     * @param other another point
     */
    public Point2(Point2 other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {

        return this.x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {

        return this.y;
    }

    /**
     * Sets the x-coordinate of this point to <code>newX</code>.
     *
     * @param newX the new x-coordinate of this point
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Sets the y-coordinate of this point to <code>newY</code>.
     *
     * @param newY the new y-coordinate of this point
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * Sets the x-coordinate and y-coordinate of this point to <code>newX</code>
     * and <code>newY</code>, respectively.
     *
     * @param newX the new x-coordinate of this point
     * @param newY the new y-coordinate of this point
     */
    public void set(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    /**
     * Move the point in the x direction by an amount dx. The new x coordinate
     * of the point is equal to <code>(this.getX() + dx)</code>.
     *
     * @param dx the change in the x coordinate of this point
     */
    public void moveX(double dx) {
        this.x += dx;
    }

    /**
     * Move the point in the y direction by an amount dy. The new y coordinate
     * of the point is equal to <code>(this.getY() + dy)</code>.
     *
     * @param dy the change in the y coordinate of this point
     */
    public void moveY(double dy) {
        this.y += dy;
    }

    /**
     * Returns the distance between this point and another point.
     *
     * @param other another point
     * @return the distance between this point and another point
     */
    public double distanceTo(Point2 other) {
        double deltaX = Math.pow(this.x - other.x, 2);
        double deltaY = Math.pow(this.y - other.y, 2);
        double distance = Math.sqrt(deltaX + deltaY);
        return distance;
    }

    /**
     * Determines if two points are almost equal (similar). Two points are
     * similar if the distance between them is smaller than the specified
     * tolerance.
     *
     * @param other the other point to compare
     * @param tol   the threshold distance between this point and other
     * @return true if the distance between this point and other is strictly
     * less than tol
     */
    public boolean similarTo(Point2 other, double tol) {

        boolean output = false;
        double thisMag = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
        ;
        double otherMag = Math.sqrt(Math.pow(other.x, 2) + Math.pow(other.y, 2));

        if (thisMag == Double.NEGATIVE_INFINITY || otherMag == Double.NEGATIVE_INFINITY)
            output = true;
        if (thisMag == Double.POSITIVE_INFINITY || otherMag == Double.POSITIVE_INFINITY)
            output = false;
        else
            output = Math.abs(thisMag - otherMag) <= tol;

        return output;

    }

    /**
     * Returns a string representation of this point. The string representation
     * of this point is the x and y-coordinates of this point, separated by a
     * comma and space, inside a pair of parentheses.
     *
     * @return a string representation of this point
     */
    @Override
    public String toString() {

        return "(" + this.x + ", " + this.y + ")";
    }

    public double mag() {
        return Math.hypot(this.getX(), this.getY());
    }

    /**
     * Returns a hash code for this point. The hash code is computed by using
     * <code>Objects.hash</code> to hash the values of the x and y coordinates
     * of this point.
     *
     * @return a hash code for this point
     */
    @Override
    public int hashCode() {

        return Objects.hash(this.x, this.y);
    }

    /**
     * Compares this point with the given object. The result is
     * <code>true</code> if and only if the argument is not <code>null</code>
     * and is a <code>Point2</code> object having the same coordinates as this
     * object.
     *
     * @param obj the object to compare this vector against
     * @return true if the given object represents a Point2 equivalent to this
     * point, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        //first step of the requirement: check if this object is equals to itself
        if (this == obj)
            return true;

        //second step of the requirement: check if the object is equals to null
        if (obj == null)
            return false;

        //third step of the requirement: check if the object is type is the same
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        //fourth step of the requirement: if the object bits are the same
        Point2 point = (Point2) obj;
        double thisX = Double.doubleToLongBits(this.x);
        double objX = Double.doubleToLongBits(point.x);

        System.out.println(point.y);
        double thisY = Double.doubleToLongBits(this.y);
        double objY = Double.doubleToLongBits(point.getY());

        if (Double.compare(this.x, point.x) == 0 && Double.compare(this.y, point.y) == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        double x = 99.9;
        double y = -33.3;
        Point2 p = new Point2(x, y);
        System.out.println(Math.ulp(y));
        System.out.println(Double.doubleToLongBits(y));
        System.out.println(Double.doubleToLongBits(y + Math.ulp(y)));
        Point2 q = new Point2(x, y + Math.ulp(y));

        System.out.println(p.equals(q));
    }


}
