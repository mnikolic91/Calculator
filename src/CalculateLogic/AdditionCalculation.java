package CalculateLogic;

public class AdditionCalculation implements CalculationStrategy {

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }


    @Override
    public String toString() {
        return "AdditionCalculation";
    }

}

