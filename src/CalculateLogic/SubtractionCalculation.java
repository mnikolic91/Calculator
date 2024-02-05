package CalculateLogic;

public class SubtractionCalculation implements CalculationStrategy{

        @Override
        public double calculate(double a, double b) {
            return a - b;
        }

    @Override
    public String toString() {
        return "SubtractionCalculation";
    }
}
