import main.primitives2d.Point_2D;
import main.primitives2d.Square;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by faos7 on 20.03.17.
 */
public class SquareTest {

    @Test
    public void TestSquareConstructor(){
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(1,1);
        Square square = new Square(p1, p2);
        assertEquals("Should be null", square.toString(),
                "Square{ld=null, lu=null, rd=null, ru=null}");
        assertEquals(square.getSuperCreated(), false);
        assertEquals(square.getSuperSquare(), false);
        assertEquals(square.getSuperCircle(), false);
        assertEquals(square.getSuperTriangle(), false);
        p2.setX(2.4);
        p2.setY(2.4);
        square = new Square(p1, p2);
        assertEquals("Should be ok", square.toString(),
                "Square{ld=(1.0 , 1.0), lu=(1.0 , 2.4), rd=(2.4 , 1.0), ru=(2.4 , 2.4)}");
        assertEquals(square.getSuperCreated(), true);
        assertEquals(square.getSuperSquare(), true);
        assertEquals(square.getSuperCircle(), false);
        assertEquals(square.getSuperTriangle(), false);
    }
}
