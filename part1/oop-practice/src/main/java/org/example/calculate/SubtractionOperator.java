package org.example.calculate;

public class SubtractionOperator implements NewArithmeticOperator {
    @Override
    public int calculate(int operand1, int operand2) {
        return operand1 - operand2;
    }

    @Override
    public boolean supports(String operator) {
        return "-".equals(operator);
    }
}
