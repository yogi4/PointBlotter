package com.apple.pbo.process;

import com.apple.pbo.dto.BoundingBox;
import com.apple.pbo.dto.CustomPoint;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/06/14
 * Time: 10:38 PM
 * Core of this application which is responsible for Validating Data
 * Process points and Print output
 */

public class ProcessBoxPoints {
    static int POINT_ITEM_SIZE = 4;
    static int BOX_ITEM_SIZE   = 5;
    static String EMPTY_SPACE = " ";
    static Logger LOGGER = LoggerFactory.getLogger(ProcessBoxPoints.class);

    /**
     * This is the method to used to process points
     * we scan points and store points in buffer as we move along
     * when we hit a box, we do a sweep of inBounds check with the points in
     * the buffer .
     * we could use other algorithm to start sweeping from the bottom of the list
     * but the disadvantage to that method is the over head of having to use additional data
     * structures and retrieval methods to tie the boxes with points and get the right print order
     * I noticed the number of computations to be same with both these approaches.
     */
    public List<String> processPointsBox(List<String> itemList)
    {
        List<String> result = new ArrayList<String>();
        List<CustomPoint> ipoints = new ArrayList<CustomPoint>();
        for( String s: itemList  )
        {
            if(s.contains("point"))
            {
                ipoints.add(convertToiPoint(s));
            }
            else if(s.contains("box"))
            {
                BoundingBox currentBox = converToiBox(s);
                result.add(s);
                for(CustomPoint point : ipoints)
                {
                    if(isInBounds(point,currentBox))
                    {
                        result.add(point.getPointID());
                    }
                }
            }

        }
        return result;
    }


    /**
     * Method to Validate if string is a point
     * @param point String representation of Point
     * @return true if valid point
     * Assumptions: Strict point check
     */
    public boolean isValidPoint(String point)
    {
         if (point.isEmpty())
         {
             System.out.println("Empty String in File");
             return false;
         }
         String[] tok = point.split(EMPTY_SPACE);
        if(tok.length==POINT_ITEM_SIZE)
        {
            if(isDouble(tok[1])&&isDouble(tok[2]))
            {
                return true;
            }else
            {
                System.out.println(point + " Point values are not double values");
                return false;
            }

        }else
        {
            System.out.println(point + " Point doesn't have enough attributes ");
            return false;
        }
    }

    /**
     * Helper Method to Validate Box line
     * @param box String input line of box
     * @return boolean true if it's valid box
     */
    public boolean isValidBox(String box)
    {
        if (box.isEmpty())
        {
            System.out.println("Empty String in File");
            return false;
        }
        String[] tok = box.split(EMPTY_SPACE);
        if(tok.length==BOX_ITEM_SIZE)
        {
            if(!tok[0].equals("box"))
            {
                System.out.println(tok[0] + " Invalid Entry ");
               return false;
            }
            if(isDouble(tok[1])&&isDouble(tok[2])&&isDouble(tok[3])&&isDouble(tok[4]))
            {
                return true;
            }else
            {
                System.out.println(box + " Point values are not double values");
                return false;
            }

        }else
        {
            System.out.println(box + " Point doesn't have enough attributes ");
            return false;
        }
    }


    /**
     * Helper method to validate if a value is Double
     * @param str  String
     * @return true if the value is double
     */
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Helper method to validate the data lines in the file
     * @param bufferList Arraylist of loaded file items
     * @return true if the file is valid and it has valid
     *         lines representing points and boxes
     */
    public boolean validateData(List<String> bufferList)
    {
         for( String line : bufferList)
         {
            boolean isValid;
            if(line.startsWith("point "))
            {
                isValid = isValidPoint(line);
            }
            else if(line.startsWith("box "))
            {
                isValid = isValidBox(line);
            }
            else
            {
                // Strict check, The lines have to start with point or box
                // if there is an empty line the program spits out error to correct it out
                // we could use a moderate check to allow empty lines at the end of the file

                System.out.println("Invalid data line neither starts with point nor box");
                return false;
            }

            if(!isValid)
            {
                return isValid;
            }
        } //end while loop
        return true;
    }


    /**
     * Method to check if point is in bounds
     * @param point CustomPoint
     * @param box BoundingBox
     * @return true if point is in bounds
     */

    public boolean isInBounds(CustomPoint point, BoundingBox box)
    {
        if(box!=null && point!=null)
        {
            return (((box.getxCorFirst() <= point.getxCoordinate()) && (point.getxCoordinate() <= box.getxCorSecond()))
                    || ((box.getxCorSecond() <= point.getxCoordinate()) && (point.getxCoordinate() <= box.getxCorFirst())))
                    && (((box.getyCorFirst() <= point.getyCoordinate()) && (point.getyCoordinate() <= box.getyCorSecond()))
                    || ((box.getyCorSecond() <= point.getyCoordinate()) && (point.getyCoordinate() <= box.getyCorFirst())));
        }
        else{
            return false;
        }
     }


    /**
     * Convert String to CustomPoint Object
     * @param pointLine point line string
     * @return CustomPoint Object
     */
    public CustomPoint convertToiPoint(String pointLine)
    {
        CustomPoint point = null;
        if(pointLine.startsWith("point"))
        {
            String[] a = pointLine.split(EMPTY_SPACE);
            if(a.length == POINT_ITEM_SIZE){
                point = new CustomPoint(Double.parseDouble(a[1]), Double.parseDouble(a[2]),a[3]);
            }
        }
       return point;
    }

    /**
     * Convert String to BoundingBox Object
     * @param boxLine box Line string
     * @return BoundingBox Object
     */
    public BoundingBox converToiBox(String boxLine)
    {
        BoundingBox box = null;
        if(boxLine.startsWith("box"))
        {
            String[] boxToken = boxLine.split(EMPTY_SPACE);
            if(boxToken.length == BOX_ITEM_SIZE){
                box = new BoundingBox(Double.parseDouble(boxToken[1]),Double.parseDouble(boxToken[3]),Double.parseDouble(boxToken[2]),Double.parseDouble(boxToken[4]));
            }
        }
        return box;
    }

    /**
     * Process method to validate file and call the processing
     * @param fileName
     * @return boolean if false
     */
    public boolean process(String fileName)
    {
        List<String> fileBufferList = new ArrayList();
        CustomUtility cu = new CustomUtility();
        if(cu.isValidFile(fileName))
        {
            fileBufferList = cu.loadData(fileName);
        }

        if(!fileBufferList.isEmpty())
        {
            if(validateData(fileBufferList))
            {
               // processPoints(fileBufferList);
                List<String> result = processPointsBox(fileBufferList);
                if(!result.isEmpty())
                {
                    System.out.println("");
                    System.out.println("**********************************");
                    for( String s: processPointsBox(fileBufferList))
                    {
                        LOGGER.info(s);
                    }
                    System.out.println("");
                    System.out.println("**********************************");
                    System.out.println("Please Refer to " + cu.getCurrentWorkingDirectory() + "/output.log  file for more information" );
                }
                return true;
            }
            else
            {
                LOGGER.info("This File has some invalid data associated with it");
                LOGGER.info("Please look at the error messages above");
                return false;
            }
        }
        else
        {
           LOGGER.info("Nice Try !!! Please input a Valid File");
           LOGGER.info("Please look at the error messages above");
           return false;
        }

    }

}
