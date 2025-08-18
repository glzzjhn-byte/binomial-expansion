package AlgebraFolder;

import MainFormulaFolder.Expression;
import java.math.BigInteger;
import java.util.List;
import UtilitiesFolder.PascalTriangleUtil;

public class AlgebraBinomial {
    private AlgebraBinomial() {}

   public static String expand(Expression expr) {
        List<BigInteger> row = PascalTriangleUtil.generateRow(expr.getPower());
        StringBuilder sb = new StringBuilder();

        int k = 0;
        while (k <= expr.getPower()) {
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
                    sb.append(" - ");
                } else {
                    sb.append(" + ");
                }
            } else {
                if (negativeTerm) {
                    sb.append("-");
                }
            }

            BigInteger coeff = row.get(k);
            int powA = expr.getPower() - k;
            int powB = k;

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
                sb.append(coeff.toString());
            }

            if (powA > 0) {
                if (printCoeff) {
                    sb.append(" * ");
                }
                sb.append(formatSymbol(expr.getLeft(), powA));
            }
            if (powB > 0) {
                if (printCoeff) {
                    sb.append(" * ");
                } else {
                    if (powA > 0) {
                        sb.append(" * ");
                    }
                }
                sb.append(formatSymbol(expr.getRight(), powB));
            }
            k = k + 1;
        }
        return sb.toString();
    }

   public static String buildSteps(Expression expr) {
        List<BigInteger> row = PascalTriangleUtil.generateRow(expr.getPower());
        StringBuilder sb = new StringBuilder();

        int k = 0;
        while (k <= expr.getPower()) {
            BigInteger coeff = row.get(k);
            int powA = expr.getPower() - k;
            int powB = k;
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

            sb.append("Step ");
            sb.append(k + 1);
            sb.append(": ");
            if (negativeTerm) {
                sb.append("-");
            }
            sb.append(coeff.toString());

            if (powA > 0) {
                sb.append(" * ");
                sb.append(formatSymbol(expr.getLeft(), powA));
            }
            if (powB > 0) {
                sb.append(" * ");
                sb.append(formatSymbol(expr.getRight(), powB));
            }
            sb.append('\n');
            k = k + 1;
        }
        return sb.toString();
    }

    private static String formatSymbol(String token, int power) {
        StringBuilder out = new StringBuilder();
        boolean needsParens = false;
        if (token.length() > 1) {
            if (!token.matches("[A-Za-z]")) {
                needsParens = true;
            } else {
                needsParens = false;
            }
        } else {
            needsParens = false;
        }
        if (needsParens) {
            out.append('(');
            out.append(token);
            out.append(')');
        } else {
            out.append(token);
        }
        if (power > 1) {
            out.append('^');
            out.append(power);
        }
        return out.toString();
    }
}