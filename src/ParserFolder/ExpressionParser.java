package ParserFolder;

import MainFormulaFolder.Expression;

public class ExpressionParser {
    private ExpressionParser() {}

    /**
     * Parses strings like: (2+3)^4, (x+y)^3, (x-zy)^2.
     * - A and B may be multi-character tokens (letters/digits/underscores).
     * - Only one top-level '+' or '-' between A and B is allowed.
     * - Power must be a non-negative integer.
     */
    public static Expression parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        String s = input.trim();
        int open = s.indexOf('(');
        int close = s.indexOf(')');
        int caret = s.indexOf('^');

        if (open < 0 || close < 0 || caret < 0 || open > close || close > caret) {
            throw new IllegalArgumentException("Expected format: (A±B)^n");
        }

        String inside = s.substring(open + 1, close).trim();
        if (inside.isEmpty()) {
            throw new IllegalArgumentException("Empty parentheses.");
        }

        // Find the first binary '+' or '-' that splits A and B (ignore unary sign at index 0)
        int opPos = -1;
        char op = 0;
        int i = 0;
        while (i < inside.length()) {
            char c = inside.charAt(i);
            if (c == '+' || c == '-') {
                if (i != 0) {
                    opPos = i;
                    op = c;
                    break;
                }
            }
            i = i + 1;
        }
        if (opPos == -1) {
            throw new IllegalArgumentException("Missing '+' or '-' between terms inside parentheses.");
        }

        String left = inside.substring(0, opPos).trim();
        String right = inside.substring(opPos + 1).trim();
        if (left.isEmpty() || right.isEmpty()) {
            throw new IllegalArgumentException("Both terms A and B must be present.");
        }

        String powerText = s.substring(caret + 1).trim();
        boolean matches = false;
        if (powerText.matches("-?\\d+")) {
            matches = true;
        }
        if (!matches) {
            throw new IllegalArgumentException("Power must be an integer.");
        }
        int power = Integer.parseInt(powerText);
        if (power < 0) {
            throw new IllegalArgumentException("Power must be non-negative.");
        }

        boolean minus = false;
        if (op == '-') {
            minus = true;
        } else {
            minus = false;
        }
        return new Expression(left, right, power, minus);
    }
}