package primitives2d;

/**
 * Created by faos7 on 17.03.17.
 */
public class Square extends Shape{
    Point_2D ld, lu, rd, ru;

    public Square() {
    }

    public Square(Point_2D ld, Point_2D ru) {
        if (ld.equals(ru)){
            System.err.println("two points are identic!");
            super.setCreated(false);
            return;
        }
        this.ld = ld;
        this.ru = ru;
        double a = ru.getX() - ld.getX();
        double b = ru.getY() - ld.getY();
        this.rd = new Point_2D(ld.getX() + a/2 + b/2, ld.getY() + b/2 - a/2);
        this.lu = new Point_2D(ld.getX() + a/2 - b/2, ld.getY() + b/2 + a/2);
        super.setSquare(true);
    }

    public Point_2D getLd() {
        return ld;
    }

    public void setLd(Point_2D ld) {
        this.ld = ld;
    }

    public Point_2D getLu() {
        return lu;
    }

    public void setLu(Point_2D lu) {
        this.lu = lu;
    }

    public Point_2D getRd() {
        return rd;
    }

    public void setRd(Point_2D rd) {
        this.rd = rd;
    }

    public Point_2D getRu() {
        return ru;
    }

    public void setRu(Point_2D ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return "primitives2d.Square{" +
                "ld=" + ld +
                ", lu=" + lu +
                ", rd=" + rd +
                ", ru=" + ru +
                '}';
    }
}
