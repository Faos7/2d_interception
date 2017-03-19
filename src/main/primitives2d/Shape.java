package main.primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Shape {
    private boolean created = false;
    private boolean isTriangle = false;
    private boolean isSquare = false;
    private boolean isCircle = false;

    public Shape() {
        created = true;
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
        return "Shape{" +
                "created=" + created +
                '}';
    }
}
