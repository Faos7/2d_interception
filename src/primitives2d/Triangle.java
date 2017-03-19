package primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Triangle extends Shape {
    Point_2D a, b, c;

    public Triangle(Point_2D a, Point_2D b, Point_2D c) {
        if (a.equals(b) || a.equals(c) || c.equals(b)){
            System.err.println("at least 2 points are identic!");
            super.setCreated(false);
            return;
        }
        this.a = a;
        this.b = b;
        this.c = c;
        super.setTriangle(true);
    }

    public Point_2D getA() {
        return a;
    }

    public void setA(Point_2D a) {
        this.a = a;
    }

    public Point_2D getB() {
        return b;
    }

    public void setB(Point_2D b) {
        this.b = b;
    }

    public Point_2D getC() {
        return c;
    }

    public void setC(Point_2D c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "primitives2d.Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
