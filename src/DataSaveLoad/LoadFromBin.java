package DataSaveLoad;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadFromBin<T> implements LoadStrategy<T> {
    @Override
    public T loadData(String path) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path))) {
            T loadedData = (T) objectInputStream.readObject();
            System.out.println("Data loaded from binary file: " + path);
            return loadedData;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
