import java.math.BigInteger;

class DoubleFactorial {
    public static BigInteger calcDoubleFactorial(int n) {
        // type your java code here
        BigInteger doubleFactorial = BigInteger.ONE;

        for (int i = n; i >= 1; i -= 2) {
            doubleFactorial = doubleFactorial.multiply(BigInteger.valueOf(i));
        }

        return doubleFactorial;
    }
}