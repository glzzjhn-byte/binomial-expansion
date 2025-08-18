package MainFormulaFolder;

public class Expression {
    private final String left;
    private final String right;
    private final int power;
    private final boolean isMinus;

    public Expression(String left, String right, int power, boolean isMinus) {
        this.left = left;
        this.right = right;
        this.power = power;
        this.isMinus = isMinus;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public int getPower() {
        return power;
    }

    public boolean isMinus() {
        return isMinus;
    }

    public boolean isPurelyNumeric() {
        boolean leftIsNumeric = false;
        boolean rightIsNumeric = false;

        if (left.matches("-?\\d+")) {
            leftIsNumeric = true;
        } else {
            leftIsNumeric = false;
        }

        if (right.matches("-?\\d+")) {
            rightIsNumeric = true;
        } else {
            rightIsNumeric = false;
        }

        if (leftIsNumeric) {
            if (rightIsNumeric) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}