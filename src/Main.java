import javax.swing.SwingUtilities;
import java.util.Scanner;

// You may need to update the imports below to match your package structure.
import BinomialCalculatorFolder.BinomialCalculatorFrame;
import MainFormulaFolder.Expression;
import BinomialExpander.NumericBinomialExpander;
import ResultFolder.NumericResult;

public class Main {
    public static void main(String[] args) {
        // Launch GUI on the EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BinomialCalculatorFrame frame = new BinomialCalculatorFrame();
                frame.setVisible(true);
            }
        });

        // Optional console interaction (loop until valid input)
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            boolean parsed = false;
            while (!parsed) {
                System.out.print("Enter expression like (2+3)^4, (x+y)^3, or (x-zy)^2: ");
                String input = scanner.nextLine();
                try {
                    Expression expr = ParserFolder.ExpressionParser.parse(input);
                    boolean purelyNumeric = expr.isPurelyNumeric();
                    if (purelyNumeric) {
                        NumericResult result = NumericBinomialExpander.expand(expr);
                        System.out.println();
                        System.out.print("Denotation: ");
                        System.out.println(result.getDenotation());
                        System.out.print("Numeric Value: ");
                        System.out.println(result.getValue());
                        System.out.println();
                        System.out.print("Steps:\n");
                        System.out.println(result.getSteps());
                    } else {
                        String denotation = AlgebraFolder.AlgebraBinomial.expand(expr);
                        String steps = AlgebraFolder.AlgebraBinomial.buildSteps(expr);
                        System.out.println();
                        System.out.print("Denotation: ");
                        System.out.println(denotation);
                        System.out.println();
                        System.out.print("Steps:\n");
                        System.out.println(steps);
                    }
                    parsed = true;
                } catch (IllegalArgumentException ex) {
                    System.out.print("Invalid input: ");
                    System.out.println(ex.getMessage());
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}