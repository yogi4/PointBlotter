package com.apple.pbo.process;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 2/7/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * File Utility class with helper objects
 * Mainly focuses on checking if file is valid and
 * Loading the data from file and populating the buffer
 */

public class CustomUtility {
    static Logger LOGGER = LoggerFactory.getLogger(CustomUtility.class);

    /**
     * Load data from a file if the file is valid
     *
     * @param filename input filename
     * @return bufferlist with strings if file is valid
     *         an EmptyList if the file is empty or corrupt
     */
    public List<String> loadData(String filename) {
        List<String> bufferlist = new ArrayList<String>();
        try {
            Scanner in = new Scanner(new FileReader(filename));
            if (!in.hasNext()) {
                LOGGER.error("File is Empty");
                System.out.println("File is Empty");
                return Collections.EMPTY_LIST;
            }
            while (in.hasNext()) {
                String line = in.nextLine();
                bufferlist.add(line);
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("File Name: " + filename + " is not a valid file");
            return Collections.EMPTY_LIST;
        }
        return bufferlist;
    }

    public String getCurrentWorkingDirectory() {
        String path = "";
        try {
            path = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * Validate File
     *
     * @return boolean value
     */
    public boolean isValidFile(String fileName) {
        if (!fileName.isEmpty()) {
            File f = new File(fileName);
            if (!f.isDirectory())
                f = f.getParentFile();
            if (f == null)
                return false;
            if (f.exists()) {
                return true;
            } else {

                System.out.println("File Path " + fileName + "is a Directory");
            }

            return f.exists();
        } else {
            System.out.println("Please enter a valid file Name");
            return false;
        }
    }
}
