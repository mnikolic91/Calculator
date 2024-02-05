package CalculateLogic;

public class UserCalc implements CalculationStrategy {

    @Override
    public double calculate(double a, double b) {

        if (b < 0) {
            throw new IllegalArgumentException("Brojevi ne mogu biti negativni!");
        }else {
            return Math.pow(a, 3) * Math.sqrt(b);
        }

    }

    @Override
    public String toString() {
        return "UserCalc";
    }
}
