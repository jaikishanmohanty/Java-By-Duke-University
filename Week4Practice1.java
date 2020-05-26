import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Week4Practice1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Week4Practice1 {
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                System.out.println("Name : " + rec.get(0) );
            }
            else {
                totalGirls += numBorn;
                System.out.println("Name : " + rec.get(0));
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    
    public int getRank(int year, String name, String gender)
    {
        int rank = 0;
        String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                    rank++;
                   // System.out.println("Rank : "+rank);
                    if(rec.get(0).equals(name)){       
                     return rank;
                      }
            }
              
        }

       return -1;
       }
       
       public void testRank(){
           int rank = getRank(1971,"Frank", "M");
           System.out.println("Rank is : "+rank);
}


 public String getName(int year, int rank, String gender)
    {
        int rankNo = 0;
        String name = null;
         String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
      //  String fname = "Data/testing/yob" + year + "short.csv";
        FileResource fr = new FileResource(fname);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                    rankNo += 1;
                    if(rankNo == rank){       
                     name = rec.get(0);
                     return name;
                      }
            }
              
        }

       return name = "No Name";
       }
       
       public void testgetName(){
           String name = getName(1980,350, "F");
           System.out.println("Name at Rank : "+name);
}

    public void whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rankOfThatYear = getRank(year,name,gender);
        String nameInNewYear = getName(newYear, rankOfThatYear, gender);
        System.out.println(name+" born in "+year+" would be "+nameInNewYear+" if he/she was born in "+newYear);
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
        
    }
    
    
    public int yearOfHighestRank (String name, String gender){
            int year = 0;
            DirectoryResource dr = new DirectoryResource();
            int startRank = 0;
            for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String fName = f.getName();
            String find = "yob";
            int startPos = fName.indexOf(find);
            int currYear = Integer.parseInt(fName.substring(startPos + 3, startPos + 7));
            int currRank = getRank(currYear, name, gender);
            if (year == 0){ 
                year = currYear;
                startRank = currRank;
            }
            System.out.println(" Current Rank is : "+currRank+" in Year : "+currYear);
            
                if (currRank < startRank) {
                    year = currYear;
                    startRank = currRank;
                }
            if (currRank == -1) {
            year = currRank;
            }
            
        }
        return year;
        }
        
        public void testyearOfHighestRank()
        {
            int highestRankYear = yearOfHighestRank("Mich", "M");
            
            System.out.println("highest year in rank  : "+highestRankYear);
            
    }
    
    public double getAverageRank(String name, String gender){
     double avgRank = 0;
     int noOfFiles = 0;
            DirectoryResource dr = new DirectoryResource();
            
            for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            noOfFiles++;
            String fName = f.getName();
            String find = "yob";
            int startPos = fName.indexOf(find);
            int currYear = Integer.parseInt(fName.substring(startPos + 3, startPos + 7));
            int currRank = getRank(currYear, name, gender);
            
            if (currRank != -1){ 
                avgRank += currRank;
            }
            System.out.println(" Current Rank is : "+currRank+" & AvgRank : "+avgRank);
            
              
            if (currRank == -1) {
            return currRank;
            }
            
        }
        return avgRank/noOfFiles;  
    }
    
    public void testgetAverageRank(){
         double averageRank = getAverageRank("Robert", "M");
            
            System.out.println("Average rank  : "+averageRank);
            
    }
    
        public int getTotalBirthsRankedHigher(int year,String name,String gender){
             int sumBirth=0;
             String fname = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
            //   String fname = "Data/testing/yob" + year + "short.csv";
            FileResource f= new FileResource(fname);
            CSVParser parser = f.getCSVParser(false);    
            int indexRank = getRank(year,name,gender);
            System.out.println("Index Rank : "+indexRank);
           
            if (indexRank==-1){
                return -1;
            }
            for(CSVRecord record:parser){
                if(record.get(1).equals(gender)){
                    int currRank=getRank(year,record.get(0),gender);
                     System.out.println("Current Rank : "+currRank);
                    if(currRank<indexRank){
                        sumBirth+=Integer.parseInt(record.get(2));
                    }
                    else{
                    break;
                }
                }
            }
           
            return sumBirth;
            
    }
    
    public void testgetTotalBirthsRankedHigher(){
     int totalBirth =  getTotalBirthsRankedHigher(1990, "Drew", "M");
     System.out.println("Total Birth higher than rank : "+totalBirth);
        
    }
}