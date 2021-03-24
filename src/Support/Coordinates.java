package Support;


import java.math.RoundingMode;

public class Coordinates {
    private ScientificNotation X, Y;

    public ScientificNotation getY() {
        return Y;
    }

    public void setY(String y) {
        Y = new ScientificNotation(y);
    }

    public ScientificNotation getX() {
        return X;
    }

    public void setX(String x) {
        X = new ScientificNotation(x);
    }

    public Coordinates(String x, String y) {
        X = new ScientificNotation(x);
        Y = new ScientificNotation(y);
    }

    public void setX(ScientificNotation x) {
        X = x;
    }

    public void setY(ScientificNotation y) {
        Y = y;
    }

    public Coordinates(ScientificNotation x, ScientificNotation y) {
        X = x;
        Y = y;
    }
}
