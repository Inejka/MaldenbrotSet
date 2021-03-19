package Support;

public class Coordinates {
    private double X, Y;

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public Coordinates(double x, double y) {
        X = x;
        Y = y;
    }
}
