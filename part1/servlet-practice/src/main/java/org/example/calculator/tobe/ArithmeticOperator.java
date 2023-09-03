package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public interface ArithmeticOperator {

    int calculate(PositiveNumber operand1, PositiveNumber operand2);

    boolean supports(String operator);
}
