package Support;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Coordinates {
    private BigDecimal X, Y;

    public BigDecimal getY() {
        return Y;
    }

    public void setY(double y) {
        Y = BigDecimal.valueOf(y);
        Y.setScale(20, RoundingMode.CEILING);
    }

    public BigDecimal getX() {
        return X;
    }

    public void setX(double x) {
        X = BigDecimal.valueOf(x);
        X.setScale(20, RoundingMode.CEILING);
    }

    public Coordinates(double x, double y) {
        X = new BigDecimal(x);
        Y = new BigDecimal(y);
        X.setScale(20, RoundingMode.CEILING);
        Y.setScale(20, RoundingMode.CEILING);
    }

    public void setX(BigDecimal x) {
        X = x;
    }

    public void setY(BigDecimal y) {
        Y = y;
    }

    public Coordinates(BigDecimal x, BigDecimal y) {
        X = x;
        Y = y;
        X.setScale(20, RoundingMode.CEILING);
        Y.setScale(20, RoundingMode.CEILING);
    }
}
