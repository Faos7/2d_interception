import math.math2D;
import misc.PointGetter;
import primitives2d.*;

public class Main {
    /*
    Goal: Estimation of math level, examination of programming level and coding style

Languages: C++, C#, Java, Python, Matlab or any other language at your choice. Usage of OOP is encouraged.

Task description

In this task you need to implement a console application that would find intersection points for
geometric primitives. The program should support the following three primitives at least:
circle, square, triangle. The workflow should look as follows:

    User defines a pair of 2D primitives that will be analyzed, e.g. a circle and a triangle.

    He/she enters parameters of each primitive. A circle is defined by x and y coordinates of the
    center and radius. A square is defined by two endpoints of a diagonal. A triangle is defined by
    coordinates of three vertices.

    The program determines whether given two primitives are intersecting and, if so, finds coordinates
    of all intersection points.

    Coordinates of the intersection points are printed to console.

Please make sure that the program does not crash/hang/go mad in the case of incorrect input data
(e.g. if user enters negative radius or specifies equal points for two vertices). Extreme cases
should be taken into scope as well (e.g. if there is overlapping between line segments).

In addition to the application, you need to implement automated tests. In these tests the program
should be called several times with some specific input data prepared in advance. The actual output
data produced by the program (the array of intersection points coordinates) should be compared
against the expected output data. */

    public static void main(String[] args) {
        Shape shape1 = PointGetter.createShape("first");
        Shape shape2 = PointGetter.createShape("second");

        if (shape1.isSquare()){
            if (shape2.isSquare()){
                math2D.CompareSquares((Square)shape1, (Square)shape2);
            }else if (shape2.isTriangle()){
                math2D.CompareTriangleAndSquare((Triangle)shape2, (Square) shape1);
            }else if (shape2.isCircle()){
                math2D.CompareCircleAndSquare((Square)shape1, (Circle)shape2);
                //square & circle
            }else {
                System.err.println("unexpected pronlem!");
            }
        }else if (shape1.isTriangle()){
            if (shape2.isTriangle()){
                math2D.CompareTriangles((Triangle)shape1, (Triangle)shape2);
            } else if (shape2.isSquare()){
                math2D.CompareTriangleAndSquare((Triangle)shape1, (Square)shape2);
            }else if (shape2.isCircle()){
                math2D.CompareTriangleAndCircle((Triangle)shape1, (Circle)shape2);
                //triangle and circle
            }else {
                System.err.println("unexpected pronlem!");
            }
        }else if (shape1.isCircle()){
            if (shape2.isTriangle()){
                math2D.CompareTriangleAndCircle((Triangle)shape2, (Circle)shape1);
                //circle and triangle
            } else if (shape2.isSquare()){
                math2D.CompareCircleAndSquare((Square) shape2, (Circle) shape1);
                //circle and square
            } else if (shape2.isCircle()){
                math2D.CompareCircles((Circle)shape1, (Circle)shape2);
                //circle and circle
            } else {
                System.err.println("unexpected pronlem!");
            }
        } else {
            System.err.println("unexpected pronlem!");
        }

            /*
        if (shape1.isSquare() && shape2.isSquare()){
            math2D.CompareSquares((Square) shape1,(Square) shape2);
        } else if (shape1.isTriangle() && shape2.isTriangle()){
            math2D.CompareTriangles((Triangle)shape1, (Triangle)shape2);
        }*/
/*
        primitives2d.Square square = new primitives2d.Square(new primitives2d.Point_2D(2.0,2.0 ),
                new primitives2d.Point_2D(2.0, 2.0));
        System.out.println(square.toString());*/
        System.out.println("keep working");
	// write your code here
    }




}
