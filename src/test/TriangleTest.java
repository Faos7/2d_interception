import main.primitives2d.Point_2D;
import main.primitives2d.Triangle;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by faos7 on 20.03.17.
 */
public class TriangleTest {

    @Test
    public void TestTriangleConstructor(){
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(1,-1);
        Point_2D p3 = new Point_2D(-1,1);
        Triangle triangle = new Triangle(p1, p1, p1);
        assertEquals("should be null", triangle.toString(),
                "Triangle{a=null, b=null, c=null}");
        assertEquals(triangle.getSuperCreated(), false);
        assertEquals(triangle.getSuperSquare(), false);
        assertEquals(triangle.getSuperCircle(), false);
        assertEquals(triangle.getSuperTriangle(), false);
        triangle = new Triangle(p1, p1, p2);
        assertEquals("should be null", triangle.toString(),
                "Triangle{a=null, b=null, c=null}");
        assertEquals(triangle.getSuperCreated(), false);
        assertEquals(triangle.getSuperSquare(), false);
        assertEquals(triangle.getSuperCircle(), false);
        assertEquals(triangle.getSuperTriangle(), false);
        triangle = new Triangle(p1, p2, p2);
        assertEquals("should be null", triangle.toString(),
                "Triangle{a=null, b=null, c=null}");
        assertEquals(triangle.getSuperCreated(), false);
        assertEquals(triangle.getSuperSquare(), false);
        assertEquals(triangle.getSuperCircle(), false);
        assertEquals(triangle.getSuperTriangle(), false);
        triangle = new Triangle(p1, p2, p1);
        assertEquals("should be null", triangle.toString(),
                "Triangle{a=null, b=null, c=null}");
        assertEquals(triangle.getSuperCreated(), false);
        assertEquals(triangle.getSuperSquare(), false);
        assertEquals(triangle.getSuperCircle(), false);
        assertEquals(triangle.getSuperTriangle(), false);
        triangle = new Triangle(p1,p2,p3);
        assertEquals("should be ok", triangle.toString(),
                "Triangle{a=(1.0 , 1.0), b=(1.0 , -1.0), c=(-1.0 , 1.0)}");
        assertEquals(triangle.getSuperCreated(), true);
        assertEquals(triangle.getSuperSquare(), false);
        assertEquals(triangle.getSuperCircle(), false);
        assertEquals(triangle.getSuperTriangle(), true);

    }
}
