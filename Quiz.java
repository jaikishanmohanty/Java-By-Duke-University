
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Quiz {
    
	public CSVRecord coldestHourInFile(CSVParser parser) {
		//start with smallestSoFar as nothing
		CSVRecord smallestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			smallestSoFar = getsmallestOfTwo(currentRow, smallestSoFar);
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("TimeEDT"));
	}

	public CSVRecord coldestInManyDays() {
		CSVRecord smallestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get smallest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			// use method to compare two records
			smallestSoFar = getsmallestOfTwo(currentRow, smallestSoFar);
		}
		//The smallestSoFar is the answer
		return smallestSoFar;
	}

	public CSVRecord getsmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
		//If smallestSoFar is nothing
		if (smallestSoFar == null) {
			smallestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature > smallestSoFar’s
			if ((currentTemp < smallestTemp && currentTemp != -9999) || (smallestTemp == -9999)) {
				//If so update smallestSoFar to currentRow
				smallestSoFar = currentRow;
			}
		}
		return smallestSoFar;
	}

	public void testColdestInManyDays () {
		CSVRecord smallest = coldestInManyDays();
		System.out.println("Coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
	}
	
	public String fileWithColdestTemperature(){
	CSVRecord Lowest = null;
        String name = null;
        DirectoryResource dr = new DirectoryResource();

        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            Lowest = getsmallestOfTwo(record, Lowest);
            if(Lowest == record){
                name = f.getName();
            }
        }
        return name;
        }
        
        void testFileWithColdestTemperature(){
        String name = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " +name);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestTemp = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was "+lowestTemp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: " );
        parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.println(record.get("TemperatureF") +" "
                               +record.get("DateUTC")  );
        }
    }
    
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
       // Start with lowestSoFar as nothing
       CSVRecord lowestSoFar = null;
       // For each row (currentRow) in the CSV File
       for (CSVRecord currentRow : parser) {
           // Getting the humidity of the row
           String humidity = currentRow.get("Humidity");
           // If lowestSoFar is nothing
           if (lowestSoFar == null) {
               lowestSoFar = currentRow;
           }
           // Otherwise check if currentRow's humidity is not "N/A"
           if (!humidity.equals("N/A")) {
               double currentHumidity = Double.parseDouble(currentRow.get
               ("Humidity"));
               double lowestHumidity = Double.parseDouble(lowestSoFar.get
               ("Humidity"));
               // Check if currentRow's humidity < lowestSoFar's and
               // Check if lowestSoFar is not null
               if (lowestSoFar != null && 
               currentHumidity < lowestHumidity) {
                   // If so, update lowestSoFar to currentRow
                   lowestSoFar = currentRow;
               }
           }
           if (humidity.equals("N/A")) {
               lowestSoFar = lowestSoFar;
           }
       }
       // The lowestSoFar is the answer
       return lowestSoFar;
   }
   
    public void testLowestHumidityInFile() {
       FileResource fr = new FileResource();
       CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
       System.out.println("lowest humidity was " + smallest.get
       ("Humidity") + " at " + smallest.get("DateUTC"));
   }
   
   public CSVRecord lowestHumidityInManyFiles() {
       CSVRecord lowestSoFar = null;
       DirectoryResource dr = new DirectoryResource();
       // iterate over files
       for (File f : dr.selectedFiles()) {
           FileResource fr = new FileResource(f);
           // use method to get smallest in file
           CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
           if (lowestSoFar == null) {
               lowestSoFar = currentRow;
           }
           else {
               double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
               double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
               // Check if currentRow's humidity < lowestSoFar's
               if (currentHumidity < lowestHumidity) {
                   // If so,update lowestSoFar to currentRow
                   lowestSoFar = currentRow;
               }
           }
       }
       // The lowestSoFar is the answer
       return lowestSoFar;
   }
   public void testLowestHumidityInManyFiles() {
       CSVRecord lowest = lowestHumidityInManyFiles();
       System.out.println("Lowest humidity was " + lowest.get("Humidity") + " at " + lowest.get
       ("DateUTC"));
    }
    
    
        public double averageTemperatureinFile (CSVParser parser){
        CSVRecord alltemps = null;
        double avg = 0;
        double count = 0;
        double sum = 0;
        for(CSVRecord currentRow : parser){
            if(alltemps == null){
              alltemps = currentRow;
            } 
            
                String temp = currentRow.get("TemperatureF");
                double curr = Double.parseDouble(temp);
            
                if (!temp.equals("-9999")){
                    count++;
                    sum = curr + sum;
                } 
            }   
        
        avg = sum/count;
        return avg;
    }
    
    
    //test Average
    public void testAverageTemperatureinFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureinFile(fr.getCSVParser());
        System.out.println("Average temp in the file was " + avg);
                            
    }   
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
         double avg = 0;
        double count = 0;
        double sum = 0;
        
        for(CSVRecord currentRow: parser){
               
                String temp = currentRow.get("TemperatureF");
                String humidity = currentRow.get("Humidity");
                double curr = Double.parseDouble(currentRow.get("TemperatureF"));
                double hum = Double.parseDouble(currentRow.get("Humidity"));
                if (!temp.equals("-9999") && hum >= value){
                   count++;
                    sum = curr + sum;
                    
                }
                
            } 
            avg = sum/count;
            return avg;
        }
        
        public void testAverageTemperatureWithHighHumidityInFile() {
         FileResource fr = new FileResource();
         double averageTempWithHumity = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
         if(!(averageTempWithHumity == 0))
         {  
         System.out.println("Average Temp when high Humidity is "+averageTempWithHumity);
        }
        else{ System.out.println("No temperatures with that humidity");}
         
            
        }
        
        
}

