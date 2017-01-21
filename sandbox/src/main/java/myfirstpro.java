//import java.math.MathContext;
//import java.math.BigDecimal;
public class myfirstpro {
    public static void main(String[] args) {
       Point first = new Point(15,27);
        Point second = new Point(56,29);
        System.out.println("Расстояние между двумя точками с координатами (" + first.x + "),(" + first.y + ") и (" + second.x + "," + second.y + ") равно " + distance(first,second));
    }
    public static double distance(Point first, Point second) {
    double k, m;
    k = Math.pow((first.x - second.x),2);
    m = Math.pow((first.y - second.x),2);
        return (Math.sqrt(k+m));
    }
}
