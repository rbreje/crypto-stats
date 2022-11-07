package ro.breje.cryptostats.model;

public interface BeMap<V> {

    int size();

    V get(String key);

    void put(String key, V value);
}
