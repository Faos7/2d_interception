package math;

import primitives2d.*;

import java.util.ArrayList;

/**
 * Created by faos7 on 17.03.17.
 */
public class math2D {

    private static final double EPS = 1e-9;

    private static double determinant(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    private static String[] circleInterception(Circle circle1, Circle circle2){

        String[] res = {"*", "*"};

        double dx = circle1.getCenter().getX() - circle2.getCenter().getX();
        double dy = circle1.getCenter().getY()- circle2.getCenter().getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        // See how many solutions there are.
        if (dist > circle1.getRadius() + circle2.getRadius())
        {
            // No solutions, the circles are too far apart.
            return res;
        }
        else if (dist < Math.abs(circle1.getRadius() - circle2.getRadius()))
        {
            // No solutions, one circle contains the other.
            return res;
        }
        else if ((dist == 0) && (Double.compare(circle1.getRadius(), circle2.getRadius()) == 0))
        {
            // No solutions, the circles coincide.
            return res;
        }
        else
        {
            // Find a and h.
            double a = (circle1.getRadius() * circle1.getRadius() -
                    circle2.getRadius() * circle2.getRadius() + dist * dist) / (2 * dist);
            double h = Math.sqrt(circle1.getRadius() * circle1.getRadius() - a * a);

            // Find P2.
            double cx2 = circle1.getCenter().getX() + a *
                    (circle2.getCenter().getX() - circle1.getCenter().getX()) / dist;
            double cy2 = circle1.getCenter().getY() + a *
                    (circle2.getCenter().getY() - circle1.getCenter().getY()) / dist;

            // Get the points P3.
            Point_2D p1 = new Point_2D(
                    (cx2 + h * (circle2.getCenter().getY() - circle1.getCenter().getY()) / dist),
                    (cy2 - h * (circle2.getCenter().getX() - circle1.getCenter().getX()) / dist));
            Point_2D p2= new Point_2D(
                    (cx2 - h * (circle2.getCenter().getY() - circle1.getCenter().getY()) / dist),
                    (cy2 + h * (circle2.getCenter().getX() - circle1.getCenter().getX()) / dist));

            // See if we have 1 or 2 solutions.
            if (dist == circle1.getRadius() + circle2.getRadius()) {
                res[0] = p1.toString();
                return res;}

            res[0] = p1.toString();
            res[1] = p2.toString();
            return res;
        }

    }

    private static String[] CircleLineInterception(Point_2D a, Point_2D b, Circle circle){
        String[] res = {"*", "*"};
        double dx, dy, A, B, C, det, t;

        dx = b.getX() - a.getX();
        dy = b.getY()- a.getY();

        A = dx * dx + dy * dy;
        B = 2 * (dx * (a.getX()- circle.getCenter().getX()) + dy * (a.getY()- circle.getCenter().getY()));
        C = (a.getX() - circle.getCenter().getX()) * (a.getX() - circle.getCenter().getX()) +
                (a.getY() - circle.getCenter().getY()) * (a.getY() - circle.getCenter().getY()) -
                circle.getRadius() * circle.getRadius();
        det = B * B - 4 * A * C;

        if ((A <= 0.0000001) || (det < 0))
        {
            // No real solutions.
            return res;
        }else if (det == 0)
        {
            // One solution.
            t = -B / (2 * A);
            Point_2D p1 = new Point_2D(a.getX() + t * dx, a.getY() + t *dy);
            if ((((Double.compare(p1.getX(), a.getX()) <= 0 )&&(Double.compare(p1.getX(), b.getX()) >= 0)) ||
                    ((Double.compare(p1.getX(), a.getX()) >=0) && (Double.compare(p1.getX(), b.getX()) <=0))) &&
                    (((Double.compare(p1.getY(), a.getY() )<= 0)&&(Double.compare(p1.getY(), b.getY()) >=0)) ||
                    (Double.compare(p1.getY(), a.getY()) >=0) && (Double.compare(p1.getY(), b.getY())  <=0)))
            res[0] = p1.toString();

            return res;
        }else
        {
            // Two solutions.
            t = (float)((-B + Math.sqrt(det)) / (2 * A));
            Point_2D p1 = new Point_2D(a.getX() + t * dx, a.getY() + t * dy);
            t = (float)((-B - Math.sqrt(det)) / (2 * A));
            Point_2D p2 = new Point_2D(a.getX() + t * dx, a.getY() + t * dy);
            if ((((Double.compare(p1.getX(), a.getX()) <= 0 )&&(Double.compare(p1.getX(), b.getX()) >= 0)) ||
                    ((Double.compare(p1.getX(), a.getX()) >=0) && (Double.compare(p1.getX(), b.getX()) <=0))) &&
                    (((Double.compare(p1.getY(), a.getY() )<= 0)&&(Double.compare(p1.getY(), b.getY()) >=0)) ||
                            (Double.compare(p1.getY(), a.getY()) >=0) && (Double.compare(p1.getY(), b.getY())  <=0)))
            res[0] = p1.toString();
            if ((((Double.compare(p2.getX(), a.getX()) <= 0 )&&(Double.compare(p2.getX(), b.getX()) >= 0)) ||
                    ((Double.compare(p2.getX(), a.getX()) >=0) && (Double.compare(p2.getX(), b.getX()) <=0))) &&
                    (((Double.compare(p2.getY(), a.getY() )<= 0)&&(Double.compare(p2.getY(), b.getY()) >=0)) ||
                            (Double.compare(p2.getY(), a.getY()) >=0) && (Double.compare(p2.getY(), b.getY())  <=0)))
            res[1] = p2.toString();

            return res;
        }
    }


    private static String interceptLines(Point_2D A, Point_2D B, Point_2D C, Point_2D D){
        Line l1 = new Line(A, B);
        Line l2 = new Line(C, D);


        if ((Double.compare(l1.getA(), l2.getA()) == 0)&&(Double.compare(l1.getB(), l2.getB()) == 0)
                && (Double.compare(l1.getC(),l2.getC())) == 0)
            System.err.println("Overlapping!");


            double zn = determinant(l1.getA(), l1.getB(), l2.getA(), l2.getB());
            if (Math.abs(zn) < EPS)
                return "*";
            double x = - determinant(l1.getC(), l1.getB(), l2.getC(), l2.getB()) / zn;
            double y = - determinant(l1.getA(), l1.getC(), l2.getA(), l2.getC()) / zn;

            if ((((Double.compare(A.getX(), x) <= 0) && (Double.compare(B.getX(), x) >= 0) &&
                    (Double.compare(C.getX(), x) <=0) && (Double.compare(D.getX(), x) >=0)) ||
                    ((Double.compare(A.getX(), x) >= 0) && (Double.compare(B.getX(), x) <= 0) &&
                    (Double.compare(C.getX(), x) >=0) && (Double.compare(D.getX(), x) <=0))) &&
                    (((Double.compare(A.getY(), y) <= 0) && (Double.compare(B.getY(), y) >= 0) &&
                    (Double.compare(C.getY(), y) <=0) && (Double.compare(D.getY(), y) >=0)) ||
                    ((Double.compare(A.getY(), y) >= 0) && (Double.compare(B.getY(), y) <= 0) &&
                    (Double.compare(C.getY(), y) >=0) && (Double.compare(D.getY(), y) <=0)))){

                Point_2D point_2D = new Point_2D(x, y);
                return point_2D.toString();
            }

            return "*";

    }

    public static void CompareSquares(Square square1, Square square2){
        ArrayList<String> list = new ArrayList<>();
        list.add(interceptLines(square1.getLd(), square1.getLu(), square2.getLd(), square2.getLu()));
        list.add(interceptLines(square1.getRu(), square1.getLu(), square2.getLd(), square2.getLu()));
        list.add(interceptLines(square1.getRu(), square1.getRd(), square2.getLd(), square2.getLu()));
        list.add(interceptLines(square1.getLd(), square1.getRd(), square2.getLd(), square2.getLu()));

        list.add(interceptLines(square1.getLd(), square1.getLu(), square2.getRu(), square2.getLu()));
        list.add(interceptLines(square1.getRu(), square1.getLu(), square2.getRu(), square2.getLu()));
        list.add(interceptLines(square1.getRu(), square1.getRd(), square2.getRu(), square2.getLu()));
        list.add(interceptLines(square1.getLd(), square1.getRd(), square2.getRu(), square2.getLu()));

        list.add(interceptLines(square1.getLd(), square1.getLu(), square2.getRd(), square2.getRu()));
        list.add(interceptLines(square1.getRu(), square1.getLu(), square2.getRd(), square2.getRu()));
        list.add(interceptLines(square1.getRu(), square1.getRd(), square2.getRd(), square2.getRu()));
        list.add(interceptLines(square1.getLd(), square1.getRd(), square2.getRd(), square2.getRu()));

        list.add(interceptLines(square1.getLd(), square1.getLu(), square2.getLd(), square2.getRd()));
        list.add(interceptLines(square1.getRu(), square1.getLu(), square2.getLd(), square2.getRd()));
        list.add(interceptLines(square1.getRu(), square1.getRd(), square2.getLd(), square2.getRd()));
        list.add(interceptLines(square1.getLd(), square1.getRd(), square2.getLd(), square2.getRd()));

        ArrayList<String> list1 = new ArrayList<>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            System.out.println("Not intercepting");
        }else {
            for (String s1 : list1){
                System.out.println(s1);
            }
        }

    }

    public static void CompareTriangles(Triangle t1, Triangle t2){
        ArrayList<String> list = new ArrayList<>();

        list.add(interceptLines(t1.getA(), t1.getB(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getB(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getB(), t2.getA(), t2.getC()));

        list.add(interceptLines(t1.getC(), t1.getB(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getC(), t1.getB(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getC(), t1.getB(), t2.getA(), t2.getC()));

        list.add(interceptLines(t1.getA(), t1.getC(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getC(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getC(), t2.getA(), t2.getC()));

        ArrayList<String> list1 = new ArrayList<>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            System.out.println("Not intercepting");
        }else {
            for (String s1 : list1){
                System.out.println(s1);
            }
        }
    }
    public static void CompareTriangleAndSquare(Triangle t, Square sq){
        ArrayList<String> list = new ArrayList<>();
        list.add(interceptLines(t.getA(), t.getB(), sq.getLd(), sq.getRd()));
        list.add(interceptLines(t.getA(), t.getB(), sq.getLd(), sq.getLu()));
        list.add(interceptLines(t.getA(), t.getB(), sq.getRu(), sq.getRd()));
        list.add(interceptLines(t.getA(), t.getB(), sq.getLu(), sq.getRu()));

        list.add(interceptLines(t.getC(), t.getB(), sq.getLd(), sq.getRd()));
        list.add(interceptLines(t.getC(), t.getB(), sq.getLd(), sq.getLu()));
        list.add(interceptLines(t.getC(), t.getB(), sq.getRu(), sq.getRd()));
        list.add(interceptLines(t.getC(), t.getB(), sq.getLu(), sq.getRu()));

        list.add(interceptLines(t.getA(), t.getC(), sq.getLd(), sq.getRd()));
        list.add(interceptLines(t.getA(), t.getC(), sq.getLd(), sq.getLu()));
        list.add(interceptLines(t.getA(), t.getC(), sq.getRu(), sq.getRd()));
        list.add(interceptLines(t.getA(), t.getC(), sq.getLu(), sq.getRu()));


        ArrayList<String> list1 = new ArrayList<>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            System.out.println("Not intercepting");
        }else {
            for (String s1 : list1){
                System.out.println(s1);
            }
        }
    }

    public static void CompareCircleAndSquare(Square sq, Circle circle){
        ArrayList<String> list = new ArrayList<>();
        String[] s = CircleLineInterception(sq.getLd(), sq.getLu(), circle);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        s = CircleLineInterception(sq.getLd(), sq.getRd(), circle);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        s = CircleLineInterception(sq.getRd(), sq.getRu(), circle);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        s = CircleLineInterception(sq.getRu(), sq.getLu(), circle);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }
        if (list.size() == 0){
            System.out.println("Not intercepting");
        }else {
            for (String s1 : list){
                System.out.println(s1);
            }
        }
    }

    public static void CompareTriangleAndCircle(Triangle tr, Circle c){
        ArrayList<String> list = new ArrayList<>();
        String[] s = CircleLineInterception(tr.getA(), tr.getB(), c);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        s = CircleLineInterception(tr.getA(), tr.getC(), c);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        s = CircleLineInterception(tr.getB(), tr.getC(), c);
        for (int i = 0; i < s.length; i++){
            if (!list.contains(s[i]) && !s[i].equals("*"))
                list.add(s[i]);
        }

        if (list.size() == 0){
            System.out.println("Not intercepting");
        }else {
            for (String s1 : list){
                System.out.println(s1);
            }
        }
    }

    public static void CompareCircles(Circle circle1, Circle circle2){

        boolean x = (Double.compare(circle1.getCenter().getX(), circle2.getCenter().getX()) == 0);
        boolean y = (Double.compare(circle1.getCenter().getY(), circle2.getCenter().getY()) == 0);
        boolean r = Double.compare(circle1.getRadius(), circle2.getRadius()) == 0;


        if (x&&y&&r){
            System.out.println("Overlapping");
            return;
        } else {
            ArrayList<String> list = new ArrayList<>();
            String[] s = circleInterception(circle1, circle2);
            for (int i = 0; i < s.length; i++){
                if (!list.contains(s[i]) && !s[i].equals("*"))
                    list.add(s[i]);
            }
            if (list.size() == 0){
                System.out.println("Not intercepting");
            }else {
                for (String s1 : list){
                    System.out.println(s1);
                }
            }
        }
    }
}
