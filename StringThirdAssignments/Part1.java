package StringThirdAssignments;
import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    
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
      public String findGene(String dna, int where){
    int startIndex = dna.indexOf("ATG", where);
    if(startIndex == -1)
    { return ""; }
    
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    int tgaIndex = findStopCodon(dna,startIndex, "TGA");
    
    // int temp = Math.min(taaIndex,tagIndex);
    // int minIndex = Math.min(temp, tgaIndex);
    int minIndex = 0;
    if(taaIndex == -1 ||
        (tgaIndex != -1 && tgaIndex < taaIndex)){
        minIndex = tgaIndex;
    }
    else {
        minIndex = taaIndex;
    }
    if(minIndex == -1 || (tagIndex != -1 && tagIndex <minIndex)) {
        minIndex = tagIndex;
    }
    if(minIndex == -1) {
        return "";
    }
    return dna.substring(startIndex, minIndex+3);
    
    }
    
    
       public StorageResource getAllGenes(String dna) {
            StorageResource geneList = new StorageResource();
     int startIndex = 0;
     
     while(true)
     {
         String currentGene = findGene(dna, startIndex);
         if(currentGene.isEmpty())
         { break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + 
                        currentGene.length();
         
        }
        return geneList;
    }
   

   public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        // printAllGenes(dna);
        // countGenes(dna);
        StorageResource genes = getAllGenes(dna);
        for(String g : genes.data())
        {
            System.out.println("Storage Resoce Genes : "+g);
        }
        
    }
    
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
    }
    


}
