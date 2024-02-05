package CalculateLogic;

public class DivisionCalculation implements CalculationStrategy {


    @Override
    public double calculate(double a, double b) {
        if (Double.isNaN(a) || Double.isNaN(b)) {
            throw new IllegalArgumentException("Polja ne mogu biti prazna (NaN).");
        }


        if (b == 0) {
            throw new IllegalArgumentException("Drugi broj (b) ne smije biti 0.");
        }

        return a / b;
    }

    @Override
    public String toString() {
        return "DivisionCalculation";
    }
}