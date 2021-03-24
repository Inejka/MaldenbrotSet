package Support;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScientificNotation {
    private static int SCALE = 30;
    private long exponent = 0;
    BigDecimal mantissa;

    public ScientificNotation(String value) {
        mantissa = new BigDecimal(value);
        normalization();
    }

    private ScientificNotation() {
    }

    public ScientificNotation multiply(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        result.mantissa = mantissa.multiply(rhs.mantissa);
        result.exponent = exponent + rhs.exponent;
        result.normalization();
        return result;
    }

    public ScientificNotation divide(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        result.mantissa = mantissa.divide(rhs.mantissa);
        result.exponent = exponent - rhs.exponent;
        result.normalization();
        return result;
    }

    public ScientificNotation add(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        if (Math.abs(exponent - rhs.exponent) > 30) {
            result.mantissa = (exponent > rhs.exponent) ? mantissa : rhs.mantissa;
            result.exponent = Math.max(exponent, rhs.exponent);
        } else {
            int exp = (int) (exponent - rhs.exponent);
            exponent -= exp;
            result.mantissa = mantissa.scaleByPowerOfTen(exp);
            result.mantissa = result.mantissa.add(rhs.mantissa);
            result.exponent = exponent;
            result.normalization();
        }
        return result;
    }

    public ScientificNotation subtract(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        if (Math.abs(exponent - rhs.exponent) > 30) {
            result.mantissa = (exponent > rhs.exponent) ? mantissa : rhs.mantissa;
            result.exponent = Math.max(exponent, rhs.exponent);
        } else {
            int exp = (int) (exponent - rhs.exponent);
            exponent -= exp;
            result.mantissa = mantissa.scaleByPowerOfTen(exp);
            result.mantissa = result.mantissa.subtract(rhs.mantissa);
            result.exponent = exponent;
            result.normalization();
        }
        return result;
    }


    private void normalization() {
        long exp = mantissa.precision() - mantissa.scale();
        exponent += exp;
        mantissa = mantissa.scaleByPowerOfTen((int) (-1 * exp));
        mantissa = mantissa.setScale(SCALE, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return mantissa + "E" + exponent;
    }

    public static ScientificNotation valueOf(double val) {
        return new ScientificNotation(String.valueOf(val));
    }

    public static ScientificNotation valueOf(int val) {
        return new ScientificNotation(String.valueOf(val));
    }

    public static ScientificNotation valueOf(float val) {
        return new ScientificNotation(String.valueOf(val));
    }

    public static ScientificNotation valueOf(String val) {
        return new ScientificNotation(val);
    }

    public ScientificNotation(double val) {
        this(String.valueOf(val));
    }

    public final static ScientificNotation ZERO = new ScientificNotation("0");

    public int compareTo(ScientificNotation rhs) {
        if (rhs.exponent != exponent) return (exponent > rhs.exponent) ? 1 : -1;
        else return mantissa.compareTo(rhs.mantissa);
    }

}
