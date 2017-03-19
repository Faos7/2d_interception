import main.math.Math2D;
import main.primitives2d.Point_2D;
import main.primitives2d.Shape;
import org.junit.Test;

/**
 * Created by faos7 on 20.03.17.
 */
public class MathTest {

    Shape shape1 = new Shape();
    Shape shape2 = new Shape();
    Math2D math2D = new Math2D(shape1, shape2);

    @Test
    public void TestLinesInterception(){
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,2);
        Point_2D p3 = new Point_2D(2,0);
        Point_2D p4 = new Point_2D(2,2);
    }
}
