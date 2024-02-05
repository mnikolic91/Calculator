package CalculateLogic;

import java.io.Serializable;

public class CalcData implements Serializable {

    private double firstNumber;
    private double secondNumber;
    private CalculationStrategy calculationStrategy;
    private double result;


    public CalcData(double firstNumber, double secondNumber, CalculationStrategy calculationStrategy) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.calculationStrategy = calculationStrategy;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double performCalculation() {
        result=calculationStrategy.calculate(firstNumber, secondNumber);
        return result;
    }

    public static CalculationStrategy createCalculationStrategy(String selectedCalculation) {

        switch (selectedCalculation) {
            case "AdditionCalculation":
                return new AdditionCalculation();
            case "SubtractionCalculation":
                return new SubtractionCalculation();
            case "MultiplicationCalculation":
                return new MultiplicationCalculation();
            case "DivisionCalculation":
                return new DivisionCalculation();
            case "PowerToCalculation":
                return new PowerToCalculation();
            case "UserCalc":
                return new UserCalc();
            default:
                throw new IllegalArgumentException("Nepodržana operacija: " + selectedCalculation);
        }
    }


    @Override
    public String toString() {
        return "CalcData{" +
                "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ",\n calculationStrategy=" + calculationStrategy.toString() +
                ", result=" + result +
                "\n==========================================" +
                "}\n";
    }
}
