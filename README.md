Build and Install instructions 

1) Build instructions 
        1) If using IDE , Make project should create a jar in the project directory with name 
PointBlotter.jar ( for convenience a file has been included) 
2)	If using command line ,  use maven plugin to make this project 
“mvn clean install” 
this should generate the require jar file 
      Cd into /PointBlotter directory and run  Mvn  clean install

2) Instructions to run this program:
We will be using Jar file for executing  ( Jar file has been supplied for convenience) 
From command line , make sure java is installed ( this can be done using config management tool) run the command below 
Java –jar PointBlotter-1.0.jar  
And the program prompts you to input file name 

Or 
java -jar PointBlotter-1.0.jar filename_with_path
example 
java -jar PointBlotter-1.0.jar /Users/bazinga/points.txt


Design

Main modules involved are 
BoundingBox – Data Transfer Object for storing Box information
CustomPoint – Data Transfer Object for  storing Point Information

CustomUtility: Helper Utility to read files 

ProcessBoxPoints – Main Core of the application which takes care of  , validating input data , operations to find if a point falls under a bounding box is determined and the output is logged to console and file 
PointBlotterApp:  Initiation class for application 


Test Cases:
I used spock framework for test cases 

