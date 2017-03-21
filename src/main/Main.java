package main;

import main.math.Math2D;
import main.misc.PointGetter;
import main.primitives2d.*;

public class Main {
    /*
    Goal: Estimation of main.math level, examination of programming level and coding style

Languages: C++, C#, Java, Python, Matlab or any other language at your choice. Usage of OOP is encouraged.

Task description

In this task you need to implement a console application that would find intersection points for
geometric primitives. The program should support the following three primitives at least:
circle, square, triangle. The workflow should look as follows:

    + User defines a pair of 2D primitives that will be analyzed, e.g. a circle and a triangle.

    + He/she enters parameters of each primitive. A circle is defined by x and y coordinates of the
    center and radius. A square is defined by two endpoints of a diagonal. A triangle is defined by
    coordinates of three vertices.

    + The program determines whether given two primitives are intersecting and, if so, finds coordinates
    of all intersection points.

    + Coordinates of the intersection points are printed to console.

+Please make sure that the program does not crash/hang/go mad in the case of incorrect input data
(e.g. if user enters negative radius or specifies equal points for two vertices). Extreme cases
should be taken into scope as well (e.g. if there is overlapping between line segments).

In addition to the application, you need to implement automated tests. In these tests the program
should be called several times with some specific input data prepared in advance. The actual output
data produced by the program (the array of intersection points coordinates) should be compared
against the expected output data. */

    public static void main(String[] args) {

        Shape shape1 = PointGetter.createShape("first");
        Shape shape2 = PointGetter.createShape("second");

        Math2D math2D = new Math2D(shape1, shape2);
        math2D.entryPoint();


/*
        Point_2D ps1 = new Point_2D(1,1);
        Point_2D ps2 = new Point_2D(3,3);
        Point_2D pt1 = new Point_2D(0,0);
        Point_2D pt2 = new Point_2D(4,0);
        Point_2D pt3 = new Point_2D(2,4);

        Shape shape1 = new Triangle(pt1, pt2, pt3);
        Shape shape2 = new Square(ps1,ps2);

        Math2D math2D = new Math2D(shape1, shape2);
        String s = math2D.CompareTriangleAndSquare((Triangle) shape1,(Square) shape2);
        System.out.println(s);
        */

    }
}
