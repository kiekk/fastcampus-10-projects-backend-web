package org.example.calculate;

public interface NewArithmeticOperator {

    int calculate(PositiveNumber operand1, PositiveNumber operand2);

    boolean supports(String operator);
}
