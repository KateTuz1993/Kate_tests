import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Kate on 24.01.2017.
 */
public class KateTests {

    @Test
    public void testDistance1() {
        Point point1 = new Point(-16, 84);
        Point point2 = new Point(47, -29);
        Assert.assertEquals(point1.distanceMethod(point2), 129.37542270462347);
       // assert(point1.distanceMethod(point2))==25;
    }
    @Test
    public void testDistance2() {
        Point point1 = new Point(-55, 55);
        Point point2 = new Point(-28, 195);
        Assert.assertEquals(point1.distanceMethod(point2), 142.57980221616245);
        // assert(point1.distanceMethod(point2))==25;
    }

    @Test
    public void testDistance3() {
        Point point1 = new Point(5, 5);
        Point point2 = new Point(5, 5);
        Assert.assertEquals(point1.distanceMethod(point2), 0.0);
        // assert(point1.distanceMethod(point2))==25;
    }
}
