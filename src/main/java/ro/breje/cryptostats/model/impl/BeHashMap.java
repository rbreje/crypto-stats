package ro.breje.cryptostats.model.impl;

import ro.breje.cryptostats.model.BeMap;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BeHashMap<V> implements BeMap<V> {

    private static final int INITIAL_SIZE = 16;

    private Set<String> keys = new TreeSet<>();

    private Entry[] buckets = new Entry[INITIAL_SIZE];

    private int currentSize = 0;

    private int maxSize = INITIAL_SIZE;

    public List<String> getKeys() {
        return keys.stream().collect(Collectors.toList());
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V get(String key) {
        int hash = Math.abs(key.hashCode() % maxSize);
        Entry<V> existingEntry = buckets[hash];

        while (Objects.nonNull(existingEntry)) {
            if (existingEntry.key.equals(key)) {
                return existingEntry.value;
            }
            existingEntry = existingEntry.next;
        }
        return null;
    }

    @Override
    public void put(String key, V value) {
        int hash = Math.abs(key.hashCode() % maxSize);
        Entry existingEntry = buckets[hash];

        if (Objects.isNull(existingEntry)) {
            Entry newEntry = new Entry(key, value);
            buckets[hash] = newEntry;
            currentSize++;

            keys.add(key);
        } else {
            if (existingEntry.key.equals(key)) {
                existingEntry.value = value;
            } else {
                while (Objects.nonNull(existingEntry.next)) {
                    existingEntry = existingEntry.next;
                }
                Entry entryInOldBucket = new Entry(key, value);
                existingEntry.next = entryInOldBucket;
                currentSize++;

                keys.add(key);
            }
        }

        if (currentSize * 0.7 == maxSize) {
            maxSize *= 2;
        }
    }

    class Entry<V> {
        final String key;
        V value;
        Entry next;

        Entry(String key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }
    }
}
