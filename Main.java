import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static String calc(String orLine) throws Exception { // for calculations
        String[] line = orLine.split(" "); // string to array [a, op, b]
        String res;

        if (line.length != 3) {
            throw new Exception("only 2 numbers and operator!");
        }

        try {
            res = Integer.toString(calcArabic(line)); // try arabic calculations
        } catch (NumberFormatException e) { // if not arabic numbers
            res = calcRoman(line);
        }

        return "Result is " + res;
    }

    public static String calcRoman(String[] line) throws Exception {
        int a = romanToArab(line[0]); // roman -> arabic
        int b = romanToArab(line[2]);
        String op = line[1];

        if (a == -1 || b == -1) { // if invalid input (or too big numbers)
            throw new Exception("different writing systems or invalid input!");
        }

        int res = switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("unknown operator!");
        };

        if (res <= 0) { // negative or zero result
            throw new Exception("roman numerals do not support negative numbers and zero!");
        }

        return arabToRoman(res);
    }

    public static int calcArabic(String[] line) throws Exception {
        int a = Integer.parseInt(line[0]);
        int b = Integer.parseInt(line[2]);
        String op = line[1];

        if (a > 10 || b > 10) { // if too big numbers exception
            throw new Exception("too big numbers!");
        }

        int res = switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("unknown operator!");
        };

        return res;
    }

    public static int romanToArab(String n) {
        int newN = -1;
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; // dictionary

        for (int i = 0; i < 10; i++) {
            if (Objects.equals(n, romanNumbers[i])) {
                newN = i + 1;
            }
        }

        return newN;
    }

    public static String arabToRoman(int n) {
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"}; //dictionary
        ArrayList<String> newN = new ArrayList<>(); // our result (array)
        StringBuilder res = new StringBuilder(); // for String result

        while (n > 0) { // check C
            if (n / 100 != 0) {
                n -= 100;
                newN.add("C"); // 100 is max value (when X * X)
            } else if (n / 50 != 0) { // check L
                if (n >= 90) {
                    newN.add("XC");
                    n -= 90;
                } else {
                    newN.add("L");
                    n -= 50;
                }

            } else if (n / 10 != 0) { // check X
                if (n >= 40) {
                    newN.add("XL");
                    n -= 40;
                } else {
                    newN.add("X");
                    n -= 10;
                }
            } else { // simple numbers
                newN.add(romanNumbers[n - 1]);
                n -= 10;
            }
        }

        for (String s : newN) // output string
        {
            res.append(s);
        }

        return res.toString();
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine(); //input

        System.out.println(calc(line)); // output
    }
}
