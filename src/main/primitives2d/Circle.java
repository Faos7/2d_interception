package main.primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Circle extends Shape{

    Point_2D center;
    double radius;


    public Circle(Point_2D center, double radius) {
        if (radius <= 0.0){
            System.err.println("radius should be more than 0");
            super.setCreated(false);
            return;
        }
        this.center = center;
        this.radius = radius;
        super.setCircle(true);
    }

    public Point_2D getCenter() {
        return center;
    }

    public void setCenter(Point_2D center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0.0){
            System.err.println("radius should be more than 0");
            return;
        }
        this.radius = radius;
    }

    public boolean getSuperCreated(){
        return super.isCreated();
    }
    public boolean getSuperTriangle(){
        return super.isTriangle();
    }
    public boolean getSuperSquare(){
        return super.isSquare();
    }
    public boolean getSuperCircle(){
        return super.isCircle();
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
