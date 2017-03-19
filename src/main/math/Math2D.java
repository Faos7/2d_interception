package main.math;

import main.primitives2d.*;

import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by faos7 on 17.03.17.
 */
public class Math2D {

    private Shape shape1, shape2;
    private static final double EPS = 1e-9;

    private  double determinant(double a, double b, double c, double d) {
        return a * d - b * c;
    }

    public Math2D(Shape shape1, Shape shape2) {
        this.shape1 = shape1;
        this.shape2 = shape2;
    }

    public void entryPoint(){
        String string;
        if (shape1.isSquare()){
            if (shape2.isSquare()){

                string = CompareSquares((Square)shape1, (Square)shape2);
            }else if (shape2.isTriangle()){
                string = CompareTriangleAndSquare((Triangle)shape2, (Square) shape1);
            }else if (shape2.isCircle()){
                string = CompareCircleAndSquare((Square)shape1, (Circle)shape2);
                //square & circle
            }else {
                string = ("unexpected problem!");
            }
        }else if (shape1.isTriangle()){
            if (shape2.isTriangle()){
                string = CompareTriangles((Triangle)shape1, (Triangle)shape2);
            } else if (shape2.isSquare()){
                string = CompareTriangleAndSquare((Triangle)shape1, (Square)shape2);
            }else if (shape2.isCircle()){
                string = CompareTriangleAndCircle((Triangle)shape1, (Circle)shape2);
                //triangle and circle
            }else {
                string = ("unexpected problem!");
            }
        }else if (shape1.isCircle()){
            if (shape2.isTriangle()){
                string = CompareTriangleAndCircle((Triangle)shape2, (Circle)shape1);
                //circle and triangle
            } else if (shape2.isSquare()){
                string = CompareCircleAndSquare((Square) shape2, (Circle) shape1);
                //circle and square
            } else if (shape2.isCircle()){
                string = CompareCircles((Circle)shape1, (Circle)shape2);
                //circle and circle
            } else {
                string = ("unexpected problem!");
            }
        } else {
            string = ("unexpected problem!");
        }
        outPrint(string);
    }

    private boolean realEq(double a, double b){
        boolean res = Math.abs(a-b) <= EPS;
        return res;
    }
    private boolean realMoreEq(double a, double b){
        boolean res = a - b >= EPS;
        return res;
    }

    private boolean eqPoint(Point_2D p1, Point_2D p2){
        boolean res = realEq(p1.getX(), p2.getX()) && realEq(p1.getY(), p2.getY());
        return res;
    }
    private boolean atOtres(Point_2D p1, Point_2D p2, Point_2D p){
        boolean res = false;
        if (eqPoint(p1,p2)){
            res = eqPoint(p1, p);
        }else {
            boolean b1 = realEq((p.getX() - p1.getX())*(p2.getY() - p1.getY()) -
                    (p.getY() - p1.getY())*(p2.getX() - p1.getX()), 0);
            boolean b2 = (realMoreEq(p.getX(),p1.getX()) && realMoreEq(p2.getX(), p.getX())) ||
                    (realMoreEq(p.getX(), p2.getX()) && realMoreEq(p1.getX(),p.getX()));
            res = b1 && b2;
        }
        return res;
    }

    boolean onSegment(Point_2D p, Point_2D r, Point_2D q)
    {
        if (q.getX() <= max(p.getX(), r.getX()) && q.getX() >= min(p.getX(), r.getX()) &&
                q.getY() <= max(p.getY(), r.getY()) && q.getY() >= min(p.getY(), r.getY()))
            return true;

        return false;
    }

    private String linesOverlap (Point_2D A, Point_2D B, Point_2D C, Point_2D D){
        if (onSegment(A,B,C) || onSegment(B,A,C)){
            if (onSegment(A,B,D) || onSegment(B,A,D)){
                return ("Overlapping from " + D.toString() +" to " + C.toString());
            }else {
                if (onSegment(C,D,B) || onSegment(D,C,B)){
                    if (B.toString().equals(C.toString())){
                        return "*";
                    }else {
                        return ("Overlapping from " + C.toString() +" to " + B.toString());
                    }
                }else {
                    if (A.toString().equals(C.toString())){
                        return "*";
                    }else {
                        return ("Overlapping from " + A.toString() +" to " + C.toString());
                    }
                }
            }
        }else if (onSegment(A, B, D) || onSegment(B,A,D)){
            if (onSegment(C,D,B) || onSegment(D,C,B)){
                if (D.toString().equals(B.toString())){
                    return "*";
                }else {
                    return ("Overlapping from " + D.toString() +" to " + B.toString());
                }
            }else {
                if (D.toString().equals(A.toString())){
                    return "*";
                }else {
                    return ("Overlapping from " + D.toString() +" to " + A.toString());
                }
            }
        }else if ((onSegment(C,D, A) || onSegment(D,C,A)) && (onSegment(C,D,B) || onSegment(D,C,B))){
            return ("Overlapping from " + A.toString() +" to " + B.toString());
        }else {
            return "*";
        }
    }

    private  String[] circleInterception(Circle circle1, Circle circle2){

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

    private  String[] CircleLineInterception(Point_2D a, Point_2D b, Circle circle){
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

    private  String interceptLines(Point_2D A, Point_2D B, Point_2D C, Point_2D D){
        Line l1 = new Line(A, B);
        Line l2 = new Line(C, D);

        if ((l1.getA() == - l2.getA()) &&(l1.getB() == -l2.getB()) && (l1.getC() == - l2.getC())){
            l2 = l1;
        }

        if ((Double.compare(l1.getA(), l2.getA()) == 0)&&(Double.compare(l1.getB(), l2.getB()) == 0)
                && (Double.compare(l1.getC(),l2.getC())) == 0){
            return linesOverlap(A,B,C,D);
        }



            double zn = determinant(l1.getA(), l1.getB(), l2.getA(), l2.getB());
            if (Math.abs(zn) < EPS)
                return "*";
            double x = - determinant(l1.getC(), l1.getB(), l2.getC(), l2.getB()) / zn;
            double y = - determinant(l1.getA(), l1.getC(), l2.getA(), l2.getC()) / zn;

            if (y == -0.0){
                y = 0;
            }

            if (x == -0.0)
                x = 0;

            boolean AB_CDx = (Double.compare(A.getX(), x) <= 0) && (Double.compare(B.getX(), x) >= 0) &&
                    (Double.compare(C.getX(), x) <=0) && (Double.compare(D.getX(), x) >=0);
            boolean BA_DCx = (Double.compare(A.getX(), x) >= 0) && (Double.compare(B.getX(), x) <= 0) &&
                    (Double.compare(C.getX(), x) >=0) && (Double.compare(D.getX(), x) <=0);
            boolean AB_DCx = (Double.compare(A.getX(), x) <= 0) && (Double.compare(B.getX(), x) >= 0) &&
                    (Double.compare(C.getX(), x) >=0) && (Double.compare(D.getX(), x) <=0);
            boolean BA_CDx = (Double.compare(A.getX(), x) >= 0) && (Double.compare(B.getX(), x) <= 0) &&
                    (Double.compare(C.getX(), x) <=0) && (Double.compare(D.getX(), x) >=0);

        boolean AB_CDy = (Double.compare(A.getY(), y) <= 0) && (Double.compare(B.getY(), y) >= 0) &&
                (Double.compare(C.getY(), y) <=0) && (Double.compare(D.getY(), y) >=0);
        boolean BA_DCy = (Double.compare(A.getY(), y) >= 0) && (Double.compare(B.getY(), y) <= 0) &&
                (Double.compare(C.getX(), y) >=0) && (Double.compare(D.getX(), y) <=0);
        boolean AB_DCy = (Double.compare(A.getY(), y) <= 0) && (Double.compare(B.getY(), y) >= 0) &&
                (Double.compare(C.getY(), y) >=0) && (Double.compare(D.getY(), y) <=0);
        boolean BA_CDy = (Double.compare(A.getY(), y) >= 0) && (Double.compare(B.getY(), y) <= 0) &&
                (Double.compare(C.getY(), y) <=0) && (Double.compare(D.getY(), y) >=0);

            if (( (AB_CDx || BA_DCx) || (AB_DCx || BA_CDx))
                    &&
                    ((AB_CDy || BA_DCy) || (AB_DCy || BA_CDy))){
                Point_2D point_2D = new Point_2D(x, y);
                return point_2D.toString();
            }

            return "*";

    }

    public  String CompareSquares(Square square1, Square square2){
        ArrayList<String> list = new ArrayList<String>();
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

        ArrayList<String> list1 = new ArrayList<String>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            list1.add("Not intercepting");
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list1){
            sb.append(s + "\n");
        }
        return sb.toString();


    }

    public  String CompareTriangles(Triangle t1, Triangle t2){
        ArrayList<String> list = new ArrayList<String>();

        list.add(interceptLines(t1.getA(), t1.getB(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getB(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getB(), t2.getA(), t2.getC()));

        list.add(interceptLines(t1.getC(), t1.getB(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getC(), t1.getB(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getC(), t1.getB(), t2.getA(), t2.getC()));

        list.add(interceptLines(t1.getA(), t1.getC(), t2.getA(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getC(), t2.getC(), t2.getB()));
        list.add(interceptLines(t1.getA(), t1.getC(), t2.getA(), t2.getC()));

        ArrayList<String> list1 = new ArrayList<String>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            list1.add("Not intercepting");
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list1){
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    public  String CompareTriangleAndSquare(Triangle t, Square sq){
        ArrayList<String> list = new ArrayList<String>();
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


        ArrayList<String> list1 = new ArrayList<String>();
        for (String s: list) {
            if (!list1.contains(s) && !s.equals("*"))
                list1.add(s);
        }
        if (list1.size() == 0){
            list1.add("Not intercepting");
        }
        StringBuilder sb = new StringBuilder();
        for (String s : list1){
            sb.append(s + "\n");
        }
        return sb.toString();
    }

    public  String CompareCircleAndSquare(Square sq, Circle circle){
        ArrayList<String> list = new ArrayList<String>();
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
            list.add("Not intercepting");
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : list){
            sb.append(s1 + "\n");
        }
        return sb.toString();
    }

    public String CompareTriangleAndCircle(Triangle tr, Circle c){
        ArrayList<String> list = new ArrayList<String>();
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
            list.add("Not intercepting");
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : list){
            sb.append(s1 + "\n");
        }
        return sb.toString();
    }

    public String CompareCircles(Circle circle1, Circle circle2){

        boolean x = (Double.compare(circle1.getCenter().getX(), circle2.getCenter().getX()) == 0);
        boolean y = (Double.compare(circle1.getCenter().getY(), circle2.getCenter().getY()) == 0);
        boolean r = Double.compare(circle1.getRadius(), circle2.getRadius()) == 0;


        if (x&&y&&r){

            return("Overlapping");
        } else {
            ArrayList<String> list = new ArrayList<String>();
            String[] s = circleInterception(circle1, circle2);
            for (int i = 0; i < s.length; i++){
                if (!list.contains(s[i]) && !s[i].equals("*"))
                    list.add(s[i]);
            }
            if (list.size() == 0){
                list.add("Not intercepting");
            }
            StringBuilder sb = new StringBuilder();
            for (String s1 : list){
                sb.append(s1 + "\n");
            }
            return sb.toString();
        }
    }

    public static void outPrint(String what){
        if (what.equals(("unexpected problem!")))
            System.err.println(what);
        else
            System.out.println(what);
    }
}
