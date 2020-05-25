package StringThirdAssignments;


/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    
    public double findcgRatio(String dna)
    {
        int countC = 0;
        int countG = 0;
        int startC = 0;
        int startG = 0;
       
        while (true) {
        int pos = dna.indexOf("C", startC);
            if (pos == -1) {
                break;
            }
            countC += 1;
            startC = pos + 1;
        }
        
        while (true) {
        int pos = dna.indexOf("G", startG);
            if (pos == -1) {
                break;
            }
            countG += 1;
            startG = pos + 1;
        }
        
        // System.out.println("Total C : "+countC);
        // System.out.println("Total G : "+countG);
        // System.out.println("Length of Dna : "+dna.length());
        double ratiocg = (float)(countC+countG)/dna.length();
        return ratiocg;
    }
    
    public void testcgratio(){
    
        String dna = "CGTCJGHCTASJF";
        double cgratio = findcgRatio(dna);
        System.out.println("Ratio of CG is : "+cgratio);
    
    }

}
