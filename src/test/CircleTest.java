import main.primitives2d.Circle;
import main.primitives2d.Point_2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Created by faos7 on 20.03.17.
 */
public class CircleTest {

    @Test
    public void TestCircleConstructor(){
        Point_2D p1 = new Point_2D(1,1);
        Circle circle = new Circle(p1, 0);
        assertEquals("Should be null", circle.toString(),
                "Circle{center=null, radius=0.0}");
        assertEquals(circle.getSuperCreated(), false);
        assertEquals(circle.getSuperSquare(), false);
        assertEquals(circle.getSuperCircle(), false);
        assertEquals(circle.getSuperTriangle(), false);

        circle = new Circle(p1, -1);
        assertEquals("Should be null", circle.toString(),
                "Circle{center=null, radius=0.0}");
        assertEquals(circle.getSuperCreated(), false);
        assertEquals(circle.getSuperSquare(), false);
        assertEquals(circle.getSuperCircle(), false);
        assertEquals(circle.getSuperTriangle(), false);

        circle = new Circle(p1, 1);
        assertEquals("Should be ok", circle.toString(),
                "Circle{center=(1.0 , 1.0), radius=1.0}");
        assertEquals(circle.getSuperCreated(), true);
        assertEquals(circle.getSuperSquare(), false);
        assertEquals(circle.getSuperCircle(), true);
        assertEquals(circle.getSuperTriangle(), false);
    }
}
