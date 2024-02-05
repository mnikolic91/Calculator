package DataSaveLoad;


import CalculateLogic.CalcData;
import CalculateLogic.CalculationStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFromText<T> implements LoadStrategy<List<T>> {
    @Override
    public List<T> loadData(String path) {
        List<T> loadedDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CalcData{")) {
                    StringBuilder currentCalcData = new StringBuilder(line);
                    while (!line.endsWith("}")) {
                        line = reader.readLine();
                        currentCalcData.append(line);
                    }

                    String[] parts = currentCalcData.toString().split(",");
                    double firstNumber = extractNumber(parts[0]);
                    double secondNumber = extractNumber(parts[1]);
                    String calculationType = parts[2].substring(parts[2].indexOf('=') + 1).trim();
                    double result = extractNumber(parts[3]);

                    CalculationStrategy calculationStrategy = CalcData.createCalculationStrategy(calculationType);

                    CalcData calcData = new CalcData(firstNumber, secondNumber, calculationStrategy);
                    calcData.setResult(result);
                    loadedDataList.add((T) calcData);
                }
            }

            System.out.println("Data loaded from text file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedDataList;
    }

    private double extractNumber(String part) {
        String[] keyValue = part.split("=");
        return Double.parseDouble(keyValue[1].trim());
    }
}
