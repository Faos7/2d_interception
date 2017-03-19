package primitives2d;

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


    public boolean equals(Circle circle) {
        boolean x = (Double.compare(this.getCenter().getX(), circle.getCenter().getX()) == 0);
        boolean y = (Double.compare(this.getCenter().getY(), circle.getCenter().getY()) == 0);
        boolean r = Double.compare(circle.getRadius(), getRadius()) == 0;
        return  x&&y&&r;
    }

    @Override
    public String toString() {
        return "primitives2d.Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
