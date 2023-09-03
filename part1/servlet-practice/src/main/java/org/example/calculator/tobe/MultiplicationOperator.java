package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public class MultiplicationOperator implements ArithmeticOperator {
    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() * operand2.toInt();
    }

    @Override
    public boolean supports(String operator) {
        return "*".equals(operator);
    }
}
