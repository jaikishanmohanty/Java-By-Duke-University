import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of Week3Practice1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Week3Practice1 {
    
    public String countryInfo(CSVParser parser, String country) {
        //for each row in the CSV File
        String foundCountry = "";
        for (CSVRecord record : parser) {
            //Look at the "Counntry" column
            String countryRecord = record.get("Country");
            //Check if it contains Country
            if (countryRecord.contains(country)) {
                //If so, write down the "Format" from that row
                foundCountry = country + ": "+record.get("Exports")+": "+record.get("Value (dollars)");
               // System.out.println(foundCountry);
            }
            
        }
        if(foundCountry.isEmpty()){
        foundCountry = "Not Found";
        }
        return foundCountry;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
         for (CSVRecord record : parser) {
            //Look at the "Counntry" column
            String export = record.get("Exports");
            //Check if it contains Country
          //  System.out.println(export);
            if (export.contains(exportItem1) && export.contains(exportItem2))
            {
                //If so, write down the "Format" from that row
               String countryName = record.get("Country"); 
                System.out.println(countryName);
            }
        }
        
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser)
        {
            String export = record.get("Exports");
            if (export.contains(exportItem))
            {
              // String countryName = record.get("Country"); 
               
                count++;
              //  System.out.println(count+" : "+countryName);
            }
            
        }
        
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount)
    {
        int amountLength = amount.length();
        
         for(CSVRecord record : parser)
        {
            String export = record.get("Value (dollars)");
            if (export.length() > amountLength)
            {
              String countryName = record.get("Country"); 
              System.out.println(countryName+ " "+ export);
            }
            
        }
        
        
    }
        

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String countryName = countryInfo(parser, "Nauru");
        System.out.println("CountryInfo return : "+countryName);
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton", "flowers");
        parser = fr.getCSVParser();
        int noOfExporters = numberOfExporters(parser, "cocoa");
        System.out.println("Number of Exporters : "+noOfExporters);
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

}
