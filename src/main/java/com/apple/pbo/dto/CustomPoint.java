package com.apple.pbo.dto;
/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/06/14
 * Time: 9:46 PM
 * Data transfer object for CustomPoint
 */

public class CustomPoint {

    /* X co-ordinate */
    private double xCoordinate ;

    /* Y co-ordinate */
    private double yCoordinate ;

    /*  CustomPoint ID */
    private String pointID = "";

    /**
     * Constructor to instantiate Custom Point
     * @param xCoordinate - X Co-ordinate
     * @param yCoordinate - Y co-ordinate
     * @param pointID - CustomPoint ID
     */
    public CustomPoint(double xCoordinate, double yCoordinate, String pointID) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.pointID = pointID;
    }

    /**
     * Method to Get x- co-ordinate
     * @return xCoordinate
     */
    public double getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Method to Set x co-ordinate
     * @param xCoordinate x co-ordinate
     */
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Method to Get y- co-ordinate
     * @return y co-ordinate
     */
    public double getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Method to set y co-ordinate
     * @param yCoordinate y co-ordinate
     */
    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Method to get pointID
     * @return point id
     */
    public String getPointID() {
        return pointID;
    }

    /**
     * Method to set point ID
     * @param pointID point ID
     */
    public void setPointID(String pointID) {
        this.pointID = pointID;
    }


}
