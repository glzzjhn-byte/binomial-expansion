import java.util.List;
import java.util.ArrayList;

class PascalTriangle {
    public static List<Integer> generateRow(int power) {
        List<Integer> row = new ArrayList<>();
        int binomialNum = 1;
        for (int k = 0; k <= power; k++) {
            row.add(binomialNum);
            binomialNum = binomialNum * (power - k) / (k + 1);
        }
        return row;
    }
}