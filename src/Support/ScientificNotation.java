package Support;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScientificNotation {
    private static int SCALE = 15;
    private long exponent = 0;
    BigDecimal mantissa;

    public ScientificNotation(String value) {
        mantissa = new BigDecimal(value);
        normalization();
    }

    private ScientificNotation() {
    }

    ScientificNotation multiply(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        result.mantissa = mantissa.multiply(rhs.mantissa);
        result.exponent = exponent + rhs.exponent;
        result.normalization();
        return result;
    }

    ScientificNotation divide(ScientificNotation rhs) {
        ScientificNotation result = new ScientificNotation();
        result.mantissa = mantissa.divide(rhs.mantissa);
        result.exponent = exponent - rhs.exponent;
        result.normalization();
        return result;
    }

    ScientificNotation add(ScientificNotation rhs) {
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

    ScientificNotation subtract(ScientificNotation rhs) {
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
}
