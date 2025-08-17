import java.util.List;

class BinomialExpansion {
    public static String expandAndDenote(long baseNumA, long baseNumB, int power) {
        List<Integer> coefficients = PascalTriangle.generateRow(power);
        StringBuilder expansion = new StringBuilder();

        for (int k = 0; k <= power; k++) {
            long binomialNum = coefficients.get(k);
            long powA = power - k;
            long powB = k;

            if (k > 0) {
                expansion.append(" + ");
            }

            if (binomialNum != 1 || (powA == 0 && powB == 0)) {
                expansion.append(binomialNum);
            }

            if (powA > 0) {
                if (binomialNum != 1) {
                    expansion.append("(");
                }
                expansion.append(baseNumA);
                if (powA > 1) {
                    expansion.append("^").append(powA);
                }
                if (binomialNum != 1) {
                    expansion.append(")");
                }
            }

            if (powB > 0) {
                if (powA > 0) {
                    expansion.append("(");
                }
                expansion.append(baseNumB);
                if (powB > 1) {
                    expansion.append("^").append(powB);
                }
                if (powA > 0) {
                    expansion.append(")");
                }
            }
        }
        return expansion.toString();
    }

    public static String calculateSimplifiedValue(long baseNumA, long baseNumB, int power) {
        List<Integer> coefficients = PascalTriangle.generateRow(power);
        long sum = 0;
        for (int k = 0; k <= power; k++) {
            long binomialNum = coefficients.get(k);
            long termA = (long) Math.pow(baseNumA, power - k);
            long termB = (long) Math.pow(baseNumB, k);
            long termValue = binomialNum * termA * termB;
            sum += termValue;
        }
        return String.valueOf(sum);
    }
}