import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        BigDecimal first = scanner.nextBigDecimal();
        BigDecimal second = scanner.nextBigDecimal();
        BigDecimal third = scanner.nextBigDecimal();

        BigDecimal average = first.add(second).add(third).divide(BigDecimal.valueOf(3), 0, RoundingMode.DOWN);
        System.out.println(average);
    }
}