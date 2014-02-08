package com.apple.pbo.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/6/14
 * Time: 7:16 PM
 * Initiation Point for code
 */


public class PointBlotterApp {

    public static void main(String args[])
    {
        ProcessBoxPoints processBoxPoints = new ProcessBoxPoints();
       if(args.length==0 || args[0].equalsIgnoreCase("c"))
       {
           System.out.println("I see that you haven't passed file as parameter ");
           System.out.println("Please enter the filename with Path:( eg. /Users/bazinga/points.txt) : ");

           try{
               BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
               String s = bufferRead.readLine();
               System.out.println("FILE PATH IS: " + s);
               processBoxPoints.process(s);
           }
           catch(IOException e)
           {
               e.printStackTrace();
           }
       }
       else if(args.length==1)
       {
                String s = args[0];
                processBoxPoints.process(s);
       }

    }

}
