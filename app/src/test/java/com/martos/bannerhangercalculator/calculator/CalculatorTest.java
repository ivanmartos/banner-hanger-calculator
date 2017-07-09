package com.martos.bannerhangercalculator.calculator;

import org.junit.Test;

import java.util.List;

/**
 * @author Ivan Martos <ivan.martos@cleverlance.com>
 */
public class CalculatorTest {

    private final Calculator calculator = new Calculator();


    @Test
    public void testName() {
        List<CalculatedPadding> calculatedPaddings = calculator.calculatedPadding(3700, 300);
        for (CalculatedPadding calculated : calculatedPaddings) {
            System.out.println(calculated.toString());
        }
    }
}