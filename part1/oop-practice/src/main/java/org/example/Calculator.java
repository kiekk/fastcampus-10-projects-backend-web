package org.example;

public class Calculator {
    public static int calculate(int operand1, String operator, int operand2) {
        if ("+".equals(operator)) {
            return operand1 + operand2;
        }
        return operand1 - operand2;
    }
}
