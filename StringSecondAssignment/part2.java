package StringSecondAssignment;


/**
 * Write a description of part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class part2 {
    
    public int howMany(String stringa, String stringb) {
        int count =0;
        int currIndex =  stringb.indexOf(stringa);
        if(currIndex == -1)
        {
            return 0;
        }
        while(currIndex >= 0)
        {
            count = count +1;
            currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        }
        
        
        return count;
    }

    public void testHowMany() {
    
        int number = howMany ("AA", "ATAATATATAAAA");
        System.out.println("Total stringa in stringb is : "+number);
        
        int number1 = howMany ("T", "ATAATATATAAAA");
        System.out.println("Total stringa in stringb is : "+number1);
    
    }
}
