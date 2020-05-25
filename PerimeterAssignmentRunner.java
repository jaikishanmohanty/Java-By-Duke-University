package perimeter_quiz;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Start with count = 0
        int count = 0;
        for(Object points : s.getPoints())
        {
            count = count + 1;
        }
        // Put code here
        return count;
    }

    public double getAverageLength(Shape s) {
        //Get the perimeter of the traingle
        double length = getPerimeter(s);
        //Get the number of point
        int numberOfPoint = getNumPoints(s);
        
        //divide Perimeter by a numbe rof point
        double averageLength = length / numberOfPoint ;
        // return averageLength
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        //Set Largest distace value as 0 double largestSide = 0
        double largestSide = 0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currSide = prevPt.distance(currPt);
            // Check currSide > largestSide
            if(currSide > largestSide)
            {
                largestSide = currSide;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // Return largestSide
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
         // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = currPt.getX();
            // Check currSide > largestSide
            if(currX > largestX)
            {
                largestX = currX;
            }
        }
        // Put code here
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerimeter = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePerimeter = getPerimeter(s);
          //  System.out.println("Shape Perimeter of each file : "+shapePerimeter);
            if(shapePerimeter > largestPerimeter)
            {
                largestPerimeter = shapePerimeter;
            }
        }
       // System.out.println("Largest Perimeter : "+largestPerimeter);
        // Put code here
        return largestPerimeter;
    }

    public File getFileWithLargestPerimeter() {
        // Put code here
        double largestPerimeter = 0;
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double shapePerimeter = getPerimeter(s);
          //  System.out.println("Shape Perimeter of each file : "+shapePerimeter);
            if(shapePerimeter > largestPerimeter)
            {
                largestPerimeter = shapePerimeter;
                temp = f;
                System.out.println(f);
            }
        }
        // System.out.println("Name of File : "+temp.getName());
        return temp;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numberOfPoint = getNumPoints(s);
        System.out.println("Total Points is : "+numberOfPoint);
        double averageLength = getAverageLength(s);
        System.out.println("Average Length of Shape = "+averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side = "+largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = "+largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeterMultipleFiles = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Permeter from Multiple files = "+largestPerimeterMultipleFiles);
        // Put code here
    }

    public void testFileWithLargestPerimeter() {
        File fileName = getFileWithLargestPerimeter();
        System.out.println("File Name with largest perimeter = "+fileName.getName());
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter of triangle = "+peri);
        double averageLength = getAverageLength(triangle);
        System.out.println("Average Lenght of triangle = "+averageLength);
        double largestSide = getLargestSide(triangle);
        System.out.println("Largest Side = "+largestSide);
        double largestX = getLargestX(triangle);
        System.out.println("Largest X = "+largestX);
        
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
       // pr.triangle();
    }
}
