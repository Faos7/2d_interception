package primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Point_2D {
    double x, y;

    public Point_2D(){
        this.x = 0;
        this.y = 0;
    }

    public Point_2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean equals( Point_2D point_2D) {
        return Double.compare(point_2D.getX(), this.getX()) == 0 &&
                Double.compare(point_2D.getY(), this.getY()) == 0;
    }

    public double distanceTo(Point_2D point_2D){
        return Math.sqrt(((this.getX()-point_2D.getX())*(this.getX() - point_2D.getX())) +
                        ((this.getY()-point_2D.getY()) * (this.getY() - point_2D.getY())));
    }


    @Override
    public String toString() {
        return  "(" + x +
                " , " + y +
                ')';
    }
}
