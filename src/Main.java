
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String denotedResult = "";
        String simplifiedResult = "";

        while (true) {
            System.out.println("Enter expression like (a+b)^3 or (a-b)^3: ");
            String input = sc.nextLine().trim();

            try {
                String inside = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
                boolean isMinus = inside.contains("-");
                String[] parts = inside.split(isMinus ? "-" : "\\+");
                long baseNumA = Long.parseLong(parts[0].trim());
                long baseNumB = Long.parseLong(parts[1].trim());
                int power = Integer.parseInt(input.substring(input.indexOf('^') + 1));

                if (isMinus) {
                    denotedResult = BinomialExpansionMinus.expandAndDenote(baseNumA, baseNumB, power);
                    simplifiedResult = BinomialExpansionMinus.calculateSimplifiedValue(baseNumA, baseNumB, power);
                } else {
                    denotedResult = BinomialExpansion.expandAndDenote(baseNumA, baseNumB, power);
                    simplifiedResult = BinomialExpansion.calculateSimplifiedValue(baseNumA, baseNumB, power);
                }
                break;

            } catch (Exception e) {
                System.out.println("Invalid input format. Please try again.");
            }
        }

        System.out.println("Denotation: " + denotedResult);
        System.out.println("Simplified Value: " + simplifiedResult);

        sc.close();
    }
}