package DataSaveLoad;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveToBin<T> implements SaveStrategy<T> {
    @Override
    public void saveData(T data, String path) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            objectOutputStream.writeObject(data);
            System.out.println("Data saved to binary file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
