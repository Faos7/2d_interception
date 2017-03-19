package primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Shape {
    private Point_2D point_2D;
    private boolean created = false;
    private boolean isTriangle = false;
    private boolean isSquare = false;
    private boolean isCircle = false;

    public Shape() {
        created = true;
    }

    public Shape(Point_2D point_2D) {
        this.point_2D = point_2D;
    }

    public Point_2D getPoint_2D() {
        return point_2D;
    }

    public void setPoint_2D(Point_2D point_2D) {
        this.point_2D = point_2D;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isTriangle() {
        return isTriangle;
    }

    public void setTriangle(boolean triangle) {
        isTriangle = triangle;
    }

    public boolean isSquare() {
        return isSquare;
    }

    public void setSquare(boolean square) {
        isSquare = square;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    @Override
    public String toString() {
        return "primitives2d.Shape{" +
                "" + point_2D +
                '}';
    }

}
