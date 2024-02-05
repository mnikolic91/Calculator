package DataSaveLoad;

public interface SaveStrategy<T> {
    void saveData(T data, String path);
}
