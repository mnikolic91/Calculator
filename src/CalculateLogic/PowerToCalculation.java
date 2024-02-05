package CalculateLogic;

public class PowerToCalculation implements CalculationStrategy {

    @Override
    public double calculate(double a, double b) {

        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Polja ne mogu biti prazna.");
        }


        if (b % 1 != 0) {
            throw new IllegalArgumentException("Drugi broj mora biti cijeli broj.");
        }

        int intB = (int) b;

        return Math.pow(a, intB);
    }

    @Override
    public String toString() {
        return "PowerToCalculation";
    }
}
