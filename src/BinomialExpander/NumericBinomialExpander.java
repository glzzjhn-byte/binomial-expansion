package BinomialExpander;

import MainFormulaFolder.Expression;
import ResultFolder.NumericResult;
import java.math.BigInteger;
import java.util.List;

public class NumericBinomialExpander {
    private NumericBinomialExpander() {
    }

    public static NumericResult expand(Expression expr) {
        int n = expr.getPower();
        List<BigInteger> row = UtilitiesFolder.PascalTriangleUtil.generateRow(n);

        BigInteger a = new BigInteger(expr.getLeft());
        BigInteger b = new BigInteger(expr.getRight());

        StringBuilder denotation = new StringBuilder();
        StringBuilder steps = new StringBuilder();
        BigInteger sum = BigInteger.ZERO;

        int k = 0;
        while (k <= n) {
            boolean negativeTerm = false;
            if (expr.isMinus()) {
                if ((k % 2) == 1) {
                    negativeTerm = true;
                } else {
                    negativeTerm = false;
                }
            } else {
                negativeTerm = false;
            }

            if (k > 0) {
                if (negativeTerm) {
                    denotation.append(" - ");
                } else {
                    denotation.append(" + ");
                }
            } else {
                if (negativeTerm) {
                    denotation.append("-");
                }
            }

            BigInteger coeff = row.get(k);
            int powA = n - k;
            int powB = k;

            BigInteger termA = powBig(a, powA);
            BigInteger termB = powBig(b, powB);
            BigInteger term = coeff.multiply(termA).multiply(termB);

            if (negativeTerm) {
                term = term.negate();
            }

            boolean printCoeff = false;
            if (!coeff.equals(BigInteger.ONE)) {
                printCoeff = true;
            } else {
                if (powA == 0 && powB == 0) {
                    printCoeff = true;
                } else {
                    printCoeff = false;
                }
            }

            if (printCoeff) {
                denotation.append(coeff);
            }
            if (powA > 0) {
                if (printCoeff) {
                    denotation.append(" * ");
                }
                denotation.append(formatNumericFactor(a, powA));
            }
            if (powB > 0) {
                if (printCoeff) {
                    denotation.append(" * ");
                } else {
                    if (powA > 0) {
                        denotation.append(" * ");
                    }
                }
                denotation.append(formatNumericFactor(b, powB));
            }

            steps.append("Step ");
            steps.append(k + 1);
            steps.append(": ");
            if (negativeTerm) {
                steps.append("-");
            }
            steps.append(coeff);
            steps.append(" * ");
            steps.append(formatNumericFactor(a, powA));
            steps.append(" * ");
            steps.append(formatNumericFactor(b, powB));
            steps.append(" = ");
            steps.append(term);
            steps.append("\n");

            sum = sum.add(term);

            k = k + 1;
        }

        return new NumericResult(denotation.toString(), sum, steps.toString());
    }

    private static String formatNumericFactor(BigInteger base, int power) {
        if (power == 0) {
            return "1";
        }
        if (power == 1) {
            return base.toString();
        }
        return base.toString() + "^" + power;
    }

    private static BigInteger powBig(BigInteger base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative.");
        }
        BigInteger result = BigInteger.ONE;
        int i = 0;
        while (i < exp) {
            result = result.multiply(base);
            i = i + 1;
        }
        return result;
    }
}
