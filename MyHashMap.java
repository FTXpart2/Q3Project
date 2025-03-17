import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private List<Entry<K, V>>[] buckets;

    public MyHashMap() {
        buckets = new List[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        bucket.add(new Entry<>(key, value));
    }

    public V get(K key) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public V remove(K key) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                V value = entry.value;
                bucket.remove(entry);
                return value;
            }
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                keySet.add(entry.key);
            }
        }
        return keySet;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % INITIAL_CAPACITY);
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
