package BinomialCalculatorFolder;
import BinomialExpander.NumericBinomialExpander;

import javax.swing.*;
import java.awt.*;

public class BinomialCalculatorFrame extends JFrame {
    private static final int WIDTH = 640;
    private static final int HEIGHT = 520;

    private final JTextField inputField;
    private final JTextArea outputArea;
    private final JButton computeButton;

  public BinomialCalculatorFrame() {
        super("Binomial Expansion Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        JPanel top = new JPanel(new BorderLayout(8, 8));
        JLabel label = new JLabel("Enter expression:");
        inputField = new JTextField();
        computeButton = new JButton("Compute");

        top.add(label, BorderLayout.WEST);
        top.add(inputField, BorderLayout.CENTER);
        top.add(computeButton, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        getRootPane().setDefaultButton(computeButton);

        computeButton.addActionListener(e -> onCompute());
        outputArea.setText("Enter expression like (2+3)^4, (x+y)^3, or (x-zy)^2: ");
    }

    private void onCompute() {
        String input = inputField.getText();
        if (input == null) {
            outputArea.setText("Please enter an expression.");
            return;
        }
        input = input.trim();
        if (input.isEmpty()) {
            outputArea.setText("Please enter an expression.");
            return;
        }

        try {
            ParserFolder.ExpressionParser parser = null;
            MainFormulaFolder.Expression expr = ParserFolder.ExpressionParser.parse(input);
            StringBuilder sb = new StringBuilder();
            boolean purelyNumeric = expr.isPurelyNumeric();
            if (purelyNumeric) {
                ResultFolder.NumericResult result = NumericBinomialExpander.expand(expr);
                sb.append("Denotation: ");
                sb.append(result.getDenotation());
                sb.append('\n');
                sb.append('\n');
                sb.append("Numeric Value: ");
                sb.append(result.getValue());
                sb.append('\n');
                sb.append('\n');
                sb.append("Steps:\n");
                sb.append(result.getSteps());
            } else {
                String denotation = AlgebraFolder.AlgebraBinomial.expand(expr);
                String steps = AlgebraFolder.AlgebraBinomial.buildSteps(expr);
                sb.append("Denotation: ");
                sb.append(denotation);
                sb.append('\n');
                sb.append('\n');
                sb.append("Steps:\n");
                sb.append(steps);
            }
            outputArea.setText(sb.toString());
        } catch (IllegalArgumentException ex) {
            outputArea.setText("Invalid input: " + ex.getMessage() + "\n\nExamples:\n  (2+3)^4\n  (x+y)^3\n  (x-zy)^2");
        }
    }
}