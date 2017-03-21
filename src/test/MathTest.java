import main.math.Math2D;
import main.primitives2d.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by faos7 on 20.03.17.
 */
public class MathTest {

    Shape shape1 = new Shape();
    Shape shape2 = new Shape();
    Math2D math2D = new Math2D(shape1, shape2);

    @Test
    public void TestSquaresInterception(){
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(2,2);
        Point_2D p3 = new Point_2D(3,0);
        Point_2D p4 = new Point_2D(5,2);
        shape1 = new Square(p1,p2);
        shape2 = new Square(p3,p4);
        String s = math2D.CompareSquares((Square)shape1,(Square)shape2);
        String actual = "Not intercepting\n";
        assertEquals("Should not intercept", s ,actual);
        p3 = new Point_2D(2,0);
        p4 = new Point_2D(4,2);
        shape2 = new Square(p3,p4);
        s = "(2.0 , 2.0)\n" +
                "Overlapping from (2.0 , 2.0) to (2.0 , 0.0)\n" +
                "(2.0 , 0.0)\n";
        actual = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should overlap in (2,0)&&(2,2)", s ,actual);

        p3.setX(1);
        p4.setX(3);
        shape2 = new Square(p3,p4);
        actual = "(1.0 , 2.0)\n" +
                "(1.0 , 0.0)\n" +
                "Overlapping from (1.0 , 2.0) to (2.0 , 2.0)\n" +
                "(2.0 , 2.0)\n" +
                "(2.0 , 0.0)\n" +
                "Overlapping from (1.0 , 0.0) to (2.0 , 0.0)\n";
        s = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should overlap in (1,2)&&(2,2) and (1,0)&&(2,0)", s ,actual);

        p1.setX(-1);
        p2.setX(1);
        p2.setY(0);
        p3.setX(0);
        p3.setY(1);
        p4.setY(1);
        p4.setX(2);
        shape1 = new Square(p1,p2);
        shape2 = new Square(p3,p4);
        actual = "(0.0 , 1.0)\n" +
                "(1.0 , 0.0)\n" +
                "Overlapping from (1.0 , 0.0) to (0.0 , 1.0)\n";
        s = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should overlap in (1,0)&&(0,1)", s ,actual);

        p3.setX(-1);
        p3.setY(0);
        p4.setY(2);
        p4.setX(1);
        shape2 = new Square(p3,p4);

        actual = "(-1.0 , 0.0)\n" +
                "(1.0 , 0.0)\n";
        s = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should intercept in (-1,0) & (1,0)", s ,actual);

        p3 = p1;
        p4 = p2;
        shape2 = new Square(p3,p4);
        actual = "Overlapping from (0.0 , 1.0) to (-1.0 , 0.0)\n" +
                "(0.0 , 1.0)\n" +
                "(-1.0 , 0.0)\n" +
                "Overlapping from (0.0 , 1.0) to (1.0 , 0.0)\n" +
                "(1.0 , 0.0)\n" +
                "Overlapping from (1.0 , 0.0) to (0.0 , -1.0)\n" +
                "(0.0 , -1.0)\n" +
                "Overlapping from (0.0 , -1.0) to (-1.0 , 0.0)\n";
        s = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should fully overlap", s ,actual);

        p1 = new Point_2D(0,0);
        p2 = new Point_2D(2,2);
        p3 = new Point_2D(0,0);
        p4 = new Point_2D(1,1);

        shape1 = new Square(p1,p2);
        shape2 = new Square(p3,p4);

        s = "Overlapping from (0.0 , 1.0) to (0.0 , 0.0)\n" +
                "(0.0 , 0.0)\n" +
                "(0.0 , 1.0)\n" +
                "(1.0 , 0.0)\n" +
                "Overlapping from (1.0 , 0.0) to (0.0 , 0.0)\n";
        actual = math2D.CompareSquares((Square)shape1,(Square)shape2);
        assertEquals("Should overlap in 2 lines & intercept in 3 points", s ,actual);

    }

    @Test
    public void TestCircleInterception(){
        Point_2D p1 = new Point_2D(0,0);
        Point_2D p2 = new Point_2D(0,0);
        shape1 = new Circle(p1,1);
        shape2 = new Circle(p1, 1);
        String expected = "Overlapping";
        String actual = math2D.CompareCircles((Circle) shape1,(Circle) shape2);
        assertEquals("Should overlap", expected,actual);

        p2.setX(1);
        shape2 = new Circle(p2, 2);
        expected = "(-1.0 , 0.0)\n";
        actual = math2D.CompareCircles((Circle) shape1,(Circle) shape2);
        assertEquals("Should intercept in (-1,0)", expected,actual);

        shape2 = new Circle(p2, 1);
        expected = "(0.5 , -0.8660254037844386)\n" +
                "(0.5 , 0.8660254037844386)\n";
        actual = math2D.CompareCircles((Circle) shape1,(Circle) shape2);
        assertEquals("Should intercept in (0.5,-0.86) and (0.5, 0.86)", expected,actual);

        p2.setX(2);
        shape2 = new Circle(p2,1);
        expected = "(1.0 , 0.0)\n";
        actual = math2D.CompareCircles((Circle) shape1,(Circle) shape2);
        assertEquals("Should intercept in (1,0)", expected,actual);
    }

    @Test
    public void TestSquareCircleInterception(){
        Point_2D p1 = new Point_2D(-1,-1);
        Point_2D p2 = new Point_2D(1,1);
        Point_2D p3 = new Point_2D(0,0);
        shape1 = new Square(p1,p2);
        shape2 = new Circle(p3,1);

        String expected = "(-1.0 , 0.0)\n" +
                "(0.0 , -1.0)\n" +
                "(1.0 , 0.0)\n" +
                "(0.0 , 1.0)\n";
        String actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (-1,0) && (0,-1) && (1,0) && (0,1)", expected, actual);

        p3.setX(1);
        expected = "(1.0 , -1.0)\n" +
                "(1.0 , 1.0)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (-1,-1) && (1,1)", expected, actual);

        p3.setX(2);
        expected = "(1.0 , 0.0)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (1,0)", expected, actual);

        p3.setX(1);
        p3.setY(1);
        expected = "(1.0 , 0.0)\n" +
                "(0.0 , 1.0)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (1,0)", expected, actual);

        p3.setX(0);
        p3.setY(0);
        ((Circle) shape2).setRadius(1.1);
        expected = "(-1.0 , 0.4582575559616089)\n" +
                "(-1.0 , -0.4582575559616089)\n" +
                "(0.4582575559616089 , -1.0)\n" +
                "(-0.4582575559616089 , -1.0)\n" +
                "(1.0 , 0.4582575559616089)\n" +
                "(1.0 , -0.4582575559616089)\n" +
                "(-0.4582575559616089 , 1.0)\n" +
                "(0.4582575559616089 , 1.0)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in 8 points", expected, actual);

        ((Circle) shape2).setRadius(1);
        p3.setX(1.5);
        ((Circle) shape2).setRadius(1);
        expected = "(1.0 , 0.866025447845459)\n" +
                "(1.0 , -0.8660254031419754)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (1,0.8)&&(1,-0.8)", expected, actual);

        p3.setX(0.5);
        expected = "(0.5 , -1.0)\n" +
                "(1.0 , 0.866025447845459)\n" +
                "(1.0 , -0.8660254031419754)\n" +
                "(0.5 , 1.0)\n";
        actual = math2D.CompareCircleAndSquare((Square) shape1,(Circle) shape2);
        assertEquals("Should intercept in (1,0.8)&&(1,-0.8) && (0.5,-1) && (0.5,1)", expected, actual);
    }

    @Test
    public void TestSquareTriangleInterception(){
        Point_2D ps1 = new Point_2D(0,0);
        Point_2D ps2 = new Point_2D(2,2);
        Point_2D pt1 = new Point_2D(0,0);
        Point_2D pt2 = new Point_2D(2,0);
        Point_2D pt3 = new Point_2D(0,2);
        shape1 = new Triangle(pt1, pt2, pt3);
        shape2 = new Square(ps1,ps2);

        String expected = "Overlapping from (2.0 , 0.0) to (0.0 , 0.0)\n" +
                "(0.0 , 0.0)\n" +
                "(2.0 , 0.0)\n" +
                "(0.0 , 2.0)\n" +
                "Overlapping from (0.0 , 2.0) to (0.0 , 0.0)\n";
        String actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 3 points and overlap in 2 lines", expected, actual);

        pt2.setX(1);
        pt3.setY(1);

        expected = "Overlapping from (0.0 , 0.0) to (1.0 , 0.0)\n" +
                "(0.0 , 0.0)\n" +
                "(1.0 , 0.0)\n" +
                "(0.0 , 1.0)\n" +
                "Overlapping from (0.0 , 0.0) to (0.0 , 1.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 3 points and overlap in 2 lines", expected, actual);

        pt1.setX(1);
        pt2.setX(3);
        pt3.setX(1);
        pt3.setY(2);

        expected = "Overlapping from (2.0 , 0.0) to (1.0 , 0.0)\n" +
                "(2.0 , 0.0)\n" +
                "(1.0 , 2.0)\n" +
                "(1.0 , 0.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 3 points and overlap in 1 line", expected, actual);

        pt1.setX(0);
        pt2.setX(2);

        expected = "Overlapping from (2.0 , 0.0) to (0.0 , 0.0)\n" +
                "(0.0 , 0.0)\n" +
                "(2.0 , 0.0)\n" +
                "(1.0 , 2.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 3 points and overlap in 1 line", expected, actual);

        pt1.setX(1);
        pt2.setX(3);
        pt3.setX(2);
        expected = "Overlapping from (2.0 , 0.0) to (1.0 , 0.0)\n" +
                "(2.0 , 0.0)\n" +
                "(2.0 , 2.0)\n" +
                "(1.0 , 0.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 3 points and overlap in 1 line", expected, actual);

        ps1.setY(1);
        ps2.setY(3);

        shape2 = new Square(ps1, ps2);
        expected = "(2.0 , 2.0)\n" +
                "(1.5 , 1.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 2 points", expected, actual);

        ps1 = new Point_2D(1,1);
        ps2 = new Point_2D(3,3);
        pt1 = new Point_2D(0,0);
        pt2 = new Point_2D(4,0);
        pt3 = new Point_2D(2,2);

        shape2 = new Square(ps1,ps2);
        shape1 = new Triangle(pt1,pt2, pt3);
        expected = "(3.0 , 1.0)\n" +
                "(1.0 , 1.0)\n";
        actual = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        assertEquals("should intercept in 2 points", expected, actual);



    }
}
