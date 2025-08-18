package ResultFolder;

import java.math.BigInteger;

public class NumericResult {
    private final String denotation;
    private final BigInteger value;
    private final String steps;

    public NumericResult(String denotation, BigInteger value, String steps) {
        this.denotation = denotation;
        this.value = value;
        this.steps = steps;
    }

   public String getDenotation() {
        return denotation;
    }
    public BigInteger getValue() {
        return value;
    }
    public String getSteps() {
        return steps;
    }
}

