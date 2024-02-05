package DataSaveLoad;

import java.io.FileWriter;
import java.io.IOException;

public class SaveToText<T> implements SaveStrategy<T> {
    @Override
    public void saveData(T data, String path) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            String dataAsString = data.toString();
            fileWriter.write(dataAsString);
            System.out.println("Data saved to text file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
