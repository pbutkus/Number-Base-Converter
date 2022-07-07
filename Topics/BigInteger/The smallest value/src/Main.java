import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        BigInteger input = scanner.nextBigInteger();
        BigInteger factorial = BigInteger.ONE;
        int n = 1;

        while (factorial.compareTo(input) < 0) {
            factorial = factorial.multiply(BigInteger.valueOf(n));
            n++;
        }

        System.out.println(n - 1);
    }
}