/**
 * Created by Kate on 21.01.2017.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distanceMethod(Point second) {
        double k, m;
        k = Math.pow((this.x - second.x),2);
        m = Math.pow((this.y - second.y),2);
        return (Math.sqrt(k+m));
    }
}
