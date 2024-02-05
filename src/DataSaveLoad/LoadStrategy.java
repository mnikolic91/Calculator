package DataSaveLoad;

public interface LoadStrategy<T> {
    T loadData(String path);
}
