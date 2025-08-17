import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GUIform extends JFrame {
    private final JTextField inputField;
    private final JTextArea outputArea;
    private final JButton calculateButton;

    public GUIform() {
        setTitle("Binomial Expansion Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel("Enter expression:"));
        inputField = new JTextField(15);
        topPanel.add(inputField);
        calculateButton = new JButton("Calculate");
        topPanel.add(calculateButton);
        add(topPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Make ENTER trigger calculateButton
        getRootPane().setDefaultButton(calculateButton);

        // Button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateExpression();
            }
        });

        setVisible(true);
    }

    private void calculateExpression() {
        String input = inputField.getText().trim();
        try {
            String inside = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
            boolean isMinus = inside.contains("-");
            String[] parts = inside.split(isMinus ? "-" : "\\+");
            long baseNumA = Long.parseLong(parts[0].trim());
            long baseNumB = Long.parseLong(parts[1].trim());
            int power = Integer.parseInt(input.substring(input.indexOf('^') + 1));

            String denotedResult, simplifiedResult, steps;
            if (isMinus) {
                denotedResult = BinomialExpansionMinus.expandAndDenote(baseNumA, baseNumB, power);
                simplifiedResult = BinomialExpansionMinus.calculateSimplifiedValue(baseNumA, baseNumB, power);
                steps = buildSteps(baseNumA, baseNumB, power, true);
            } else {
                denotedResult = BinomialExpansion.expandAndDenote(baseNumA, baseNumB, power);
                simplifiedResult = BinomialExpansion.calculateSimplifiedValue(baseNumA, baseNumB, power);
                steps = buildSteps(baseNumA, baseNumB, power, false);
            }

            outputArea.setText("Denotation: " + denotedResult + "\n\nSteps:\n" + steps + "\nSimplified Value: " + simplifiedResult);

        } catch (Exception ex) {
            outputArea.setText("Invalid input format. Try again (e.g., (2+3)^4)");
        }
    }

    private String buildSteps(long baseNumA, long baseNumB, int power, boolean isMinus) {
        StringBuilder sb = new StringBuilder();
        List<Integer> coefficients = PascalTriangle.generateRow(power);

        for (int k = 0; k <= power; k++) {
            long binomialNum = coefficients.get(k);
            long powA = power - k;
            long powB = k;
            long sign = (isMinus && (k % 2 != 0)) ? -1 : 1;

            long termA = (long) Math.pow(baseNumA, powA);
            long termB = (long) Math.pow(baseNumB, powB);
            long termValue = sign * binomialNum * termA * termB;

            sb.append("Step ").append(k + 1).append(": ")
                    .append(sign * binomialNum).append(" * ")
                    .append(baseNumA).append("^").append(powA)
                    .append(" * ").append(baseNumB).append("^").append(powB)
                    .append(" = ").append(termValue).append("\n");
        }
        return sb.toString();
    }
}
