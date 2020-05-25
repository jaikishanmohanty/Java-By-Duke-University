package tagFinder2;


/**
 * Write a description of AllCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AllCodon {
    
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
    
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1)
        {
            int diff = currIndex-startIndex;
            if(diff%3 == 0)
            {
                return currIndex;
            }
            else{
                currIndex = dnaStr.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaStr.length();
    
    }
    
    public void testStopCodon(){
    String dna = "fasdasfbjTAAasdTAA";
    int stop = findStopCodon(dna,0,"TAA");
    if(stop != 9){System.out.println("Error in function");}
    else
    {
        System.out.println("no error");
    }
    
      String dna1 = "AAATGSDJAHDLTAAMHTAA";
        System.out.println("DNA Strand is : " +dna1);
        String gene1 = findGene(dna1);
        System.out.println("Gene is : "+gene1);
        
     String dna2 = "AAATGSDTTAGLTAAMHTAG";
        System.out.println("DNA Strand is : " +dna2);
        String gene2 = findGene(dna2);
        System.out.println("Gene is : "+gene2);
        
     String dna3 = "aATGssTGAOTGAAGTAA";
        System.out.println("DNA Strand is : " +dna3);
        String gene3 = findGene(dna3);
        System.out.println("Gene is : "+gene3);
    
    }
    
    public String findGene(String dna){
    int startIndex = dna.indexOf("ATG");
    if(startIndex == -1)
    { return ""; }
    
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    int tgaIndex = findStopCodon(dna,startIndex, "TGA");
    
    int temp = Math.min(taaIndex,tagIndex);
    int minIndex = Math.min(temp, tgaIndex);
    
    if(minIndex == dna.length()){
        return "";
    }
    
    return dna.substring(startIndex, minIndex+3);
    
    }
    
    
 
    
    

}
