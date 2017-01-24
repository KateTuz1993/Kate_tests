public class myfirstpro {
    public static void main(String[] args) {
       Point first = new Point(46,28);
        Point second = new Point(46,28);
        System.out.println("Расстояние между двумя точками с координатами (" + first.x + "),(" + first.y + ") и (" + second.x + "," + second.y + ") равно " + distance(first,second));
        System.out.println("Расстояние между двумя точками с координатами (" + first.x + "),(" + first.y + ") и (" + second.x + "," + second.y + ") равно " + first.distanceMethod(second));
    }
    public static double distance(Point first, Point second) {
    double k, m;
    k = Math.pow((first.x - second.x),2);
    m = Math.pow((first.y - second.y),2);
        return (Math.sqrt(k+m));
    }
}
