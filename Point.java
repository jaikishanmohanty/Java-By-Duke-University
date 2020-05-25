
/**
 * Write a description of Point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Point {
    private int x;
    private int y;
    public Point(int startx,int starty)
    {
        x = startx;
        y = starty;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    
    public double distance(Point otherpt){
    int dx = x - otherpt.getx();
    int dy = y - otherpt.gety();
    return Math.sqrt(dx*dx + dy*dy);
}
public static void main(String args[])
{
    Point p1 = new Point(3,9);
    Point p2 = new Point(7,7);
    System.out.println("Distance between two point");
    System.out.println(p1.distance(p2));
    
}
}
