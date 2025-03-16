import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {

    // List to store calculation history
    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Console-Based Calculator!");

        while (running) {
            System.out.println("\nPlease enter an operation (e.g., 2 + 3, sqrt(16), pow(2,3)) or type 'history' to view past calculations:");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("history")) {
                displayHistory();
                continue;
            }

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator. Goodbye!");
                running = false;
                continue;
            }

            try {
                String result = evaluateExpression(input);
                System.out.println("Result: " + result);
                history.add(input + " = " + result); // Add to history
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            // Ask the user if they want to continue or exit
            System.out.println("\nDo you want to perform another calculation? (yes/no)");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator. Goodbye!");
                running = false;
            }
        }

        scanner.close();
    }

    // Evaluate the input expression
    private static String evaluateExpression(String input) throws Exception {
        if (input.startsWith("sqrt(")) {
            double number = parseNumber(input.substring(5, input.length() - 1));
            return String.valueOf(sqrt(number));
        } else if (input.startsWith("pow(")) {
            String[] parts = input.substring(4, input.length() - 1).split(",");
            double base = parseNumber(parts[0]);
            double exponent = parseNumber(parts[1]);
            return String.valueOf(power(base, exponent));
        } else if (input.startsWith("abs(")) {
            double number = parseNumber(input.substring(4, input.length() - 1));
            return String.valueOf(abs(number));
        } else if (input.startsWith("round(")) {
            double number = parseNumber(input.substring(6, input.length() - 1));
            return String.valueOf(round(number));
        } else {
            // Handle basic arithmetic operations
            String[] tokens = input.split(" ");
            if (tokens.length != 3) {
                throw new Exception("Invalid input format. Expected: <number> <operator> <number>");
            }

            double num1 = parseNumber(tokens[0]);
            String operator = tokens[1];
            double num2 = parseNumber(tokens[2]);

            switch (operator) {
                case "+":
                    return String.valueOf(add(num1, num2));
                case "-":
                    return String.valueOf(subtract(num1, num2));
                case "*":
                    return String.valueOf(multiply(num1, num2));
                case "/":
                    return String.valueOf(divide(num1, num2));
                case "%":
                    return String.valueOf(modulus(num1, num2));
                default:
                    throw new Exception("Invalid operator. Supported operators: +, -, *, /, %");
            }
        }
    }

    // Parse a number from a string
    private static double parseNumber(String str) throws Exception {
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number format: " + str);
        }
    }

    // Basic arithmetic operations
    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) throws Exception {
        if (b == 0) {
            throw new Exception("Division by zero is not allowed.");
        }
        return a / b;
    }

    private static double modulus(double a, double b) throws Exception {
        if (b == 0) {
            throw new Exception("Modulus by zero is not allowed.");
        }
        return a % b;
    }

    // Additional functions
    private static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    private static double sqrt(double number) throws Exception {
        if (number < 0) {
            throw new Exception("Square root of a negative number is not allowed.");
        }
        return Math.sqrt(number);
    }

    private static double abs(double number) {
        return Math.abs(number);
    }

    private static long round(double number) {
        return Math.round(number);
    }

    // Display calculation history
    private static void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("No calculations in history.");
        } else {
            System.out.println("Calculation History:");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }
}