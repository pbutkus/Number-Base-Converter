package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        levelOne();
    }

    public static String convertFrom(BigInteger decimal, int base) {
        ArrayList<BigInteger> remainders = new ArrayList<>();
        StringBuilder stringOfConverted = new StringBuilder();

        while(decimal.compareTo(BigInteger.ZERO) > 0) {
            remainders.add(decimal.remainder(BigInteger.valueOf(base)));
            decimal = decimal.divide(BigInteger.valueOf(base));
        }

        for (int i = remainders.size() - 1; i >= 0; i--) {
            if (remainders.get(i).intValue() > 9) {
                stringOfConverted.append((char)(55 + remainders.get(i).intValue()));
            } else {
                stringOfConverted.append(remainders.get(i));
            }
        }

        return stringOfConverted.toString();
    }

    public static BigInteger convertTo(String sourceNumber, int sourceBase) {
        BigInteger base = BigInteger.ONE;
        BigInteger decimalVal = BigInteger.ZERO;
        String upperCaseNum = sourceNumber.toUpperCase();

        for (int i = upperCaseNum.length() - 1; i >= 0; i--) {
            if (upperCaseNum.charAt(i) >= '0' && upperCaseNum.charAt(i) <= '9') {
                int intValOfChar = (upperCaseNum.charAt(i) - 48);
                BigInteger multiplied = BigInteger.valueOf(intValOfChar).multiply(base);
                decimalVal = decimalVal.add(multiplied);

                base = base.multiply(BigInteger.valueOf(sourceBase));
            } else if (upperCaseNum.charAt(i) >= 'A' && upperCaseNum.charAt(i) <= 'Z') {
                int intValOfChar = (upperCaseNum.charAt(i) - 55);
                BigInteger multiplied = BigInteger.valueOf(intValOfChar).multiply(base);
                decimalVal = decimalVal.add(multiplied);

                base = base.multiply(BigInteger.valueOf(sourceBase));
            }
        }

        return decimalVal;
    }

    public static void levelOne() {
        boolean running = true;

        while (running) {
            System.out.println("Enter two numbers in format: {source base} {target base} (To quit type /exit)");
            String input = scanner.nextLine();

            if ("/exit".equals(input)) {
                running = false;
            } else {
                String[] parts = input.split(" ");
                int sourceBase = Integer.parseInt(parts[0]);
                int targetBase = Integer.parseInt(parts[1]);
                levelTwo(sourceBase, targetBase);
            }
        }
    }

    public static void levelTwo(int sourceBase, int targetBase) {
        boolean running = true;

        while (running) {
            System.out.println("Enter number in base " + sourceBase + " to convert to base " + targetBase + " (To go back type /back)");
            String input = scanner.nextLine();

            if ("/back".equals(input)) {
                running = false;
            } else {
                String[] splitInput = input.split("\\.");
                if (splitInput.length == 2) {
                    BigDecimal fractionConvertedToDecimal = convertFractions(splitInput[1], sourceBase);
                    BigInteger convertedToDecimal = convertTo(splitInput[0], sourceBase);
                    double valueToMultiplyBy = Math.pow(targetBase * 1.0, 5.0);

                    fractionConvertedToDecimal = fractionConvertedToDecimal.multiply(BigDecimal.valueOf(valueToMultiplyBy));
                    convertedToDecimal = convertedToDecimal.multiply(BigInteger.valueOf((long)valueToMultiplyBy));

                    BigInteger toConvert = convertedToDecimal.add(fractionConvertedToDecimal.toBigInteger());

                    String converted = convertFrom(toConvert, targetBase);
                    String result = converted.substring(0, converted.length() - 5) + "." + converted.substring(converted.length() - 5);

                    if (result.charAt(0) == '.') {
                        result = "0" + result;
                    }

                    System.out.println("Conversion result: " + result);
                } else {
                    String converted = convertFrom(convertTo(splitInput[0], sourceBase), targetBase);
                    System.out.println("Conversion result: " + converted);
                }
            }

            System.out.println();
        }
    }

    public static BigDecimal convertFractions(String source, int sourceBase) {
        String upperCaseNum = source.toUpperCase();
        BigDecimal decimalVal = BigDecimal.ZERO;
        double initialBase = 1.0 / sourceBase;
        BigDecimal base = BigDecimal.valueOf(initialBase);

        for (int i = 0; i < upperCaseNum.length(); i++) {
            if (upperCaseNum.charAt(i) >= '0' && upperCaseNum.charAt(i) <= '9') {
                int intValOfChar = (upperCaseNum.charAt(i) - 48);
                BigDecimal multiplied = BigDecimal.valueOf(intValOfChar).multiply(base);
                decimalVal = decimalVal.add(multiplied);

                base = base.divide(BigDecimal.valueOf(sourceBase), 100, RoundingMode.HALF_UP);
            } else if (upperCaseNum.charAt(i) >= 'A' && upperCaseNum.charAt(i) <= 'Z') {
                int intValOfChar = (upperCaseNum.charAt(i) - 55);
                BigDecimal multiplied = BigDecimal.valueOf(intValOfChar).multiply(base);
                decimalVal = decimalVal.add(multiplied);

                base = base.divide(BigDecimal.valueOf(sourceBase), 100, RoundingMode.HALF_UP);
            }
        }

        return decimalVal;
    }


}
