package com.apple.pbo.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/06/14
 * Time: 9:31 PM
 */

/**
 * This class is a data transfer object
 *
 */
public class BoundingBox {

    /** First X co-ordinate **/
    private double xCorFirst;

    /** Second X co-ordinate **/
    private double xCorSecond;

    /** First Y co-ordinate **/
    private double yCorFirst;

    /** Second Y co-ordinate **/
    private double yCorSecond;


    /**
     * Constructor
     * @param xCorFirst First x Co-ordinate
     * @param xCorSecond Second x Co-oridinate
     * @param yCorFirst First y Co-ordinate
     * @param yCorSecond Second x Co-oridinate
     */
    public BoundingBox(double xCorFirst, double xCorSecond, double yCorFirst, double yCorSecond)
    {
        this.xCorFirst  = xCorFirst;
        this.xCorSecond = xCorSecond;
        this.yCorFirst  = yCorFirst;
        this.yCorSecond = yCorSecond;
    }

    /**
     * Helper method to return First X Coordinate
     * @return  double - x co-ordinate
     */
    public double getxCorFirst() {
        return xCorFirst;
    }

    /**
     * Helper method to set First X Coordinate
     * @param xCorFirst - x co-ordinate
     */
    public void setxCorFirst(double xCorFirst) {
        this.xCorFirst = xCorFirst;
    }

    /**
     * Helper method to get second X Coordinate
     * @return double Second X Co-ordinate
     */
    public double getxCorSecond() {
        return xCorSecond;
    }

    /**
     * Helper method to set Second X Coordinate
     * @param xCorSecond - x co-ordinate
     */
    public void setxCorSecond(double xCorSecond) {
        this.xCorSecond = xCorSecond;
    }

    public double getyCorFirst() {
        return yCorFirst;
    }
    /**
     * Helper method to set First Y Coordinate
     * @param yCorFirst - y co-ordinate
     */
    public void setyCorFirst(double yCorFirst) {
        this.yCorFirst = yCorFirst;
    }

    public double getyCorSecond() {
        return yCorSecond;
    }

    /**
     * Helper method to set Second Y Coordinate
     * @param yCorSecond - y co-ordinate
     */
    public void setyCorSecond(double yCorSecond) {
        this.yCorSecond = yCorSecond;
    }





}
