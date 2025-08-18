package UtilitiesFolder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PascalTriangleUtil {
    private PascalTriangleUtil() {}

    public static List<BigInteger> generateRow(int n) {
        List<BigInteger> row = new ArrayList<BigInteger>(n + 1);
        BigInteger coeff = BigInteger.ONE;
        int k = 0;
        while (k <= n) {
            row.add(coeff);

            int numerator = n - k;
            int denominator = k + 1;
            if (k < n) {
                coeff = coeff.multiply(BigInteger.valueOf(numerator));
                coeff = coeff.divide(BigInteger.valueOf(denominator));
            }
            k = k + 1;
        }
        return row;
    }
}