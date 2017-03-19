package primitives2d;

/**
 * Created by faos7 on 18.03.17.
 */
public class Line {
    private  double A, B, C;

    public Line(double a, double b, double c) {
        A = a;
        B = b;
        C = c;
    }

    public Line(Point_2D a, Point_2D b){
        A = a.getY()- b.getY();
        B = b.getX() - a.getX();
        C = a.getX() * b.getY() - b.getX() * a.getY();
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }
}
