import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Collection;

public class CustomHashSet<E> implements Set<E> {
    private static final int INITIAL_CAPACITY = 16;
    private List<E>[] buckets;

    public CustomHashSet() {
        buckets = new List[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);
        List<E> bucket = buckets[index];
        if (!bucket.contains(element)) {
            bucket.add(element);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object element) {
        int index = getIndex(element);
        List<E> bucket = buckets[index];
        return bucket.contains(element);
    }

    @Override
    public boolean remove(Object element) {
        int index = getIndex(element);
        List<E> bucket = buckets[index];
        return bucket.remove(element);
    }

    @Override
    public int size() {
        int size = 0;
        for (List<E> bucket : buckets) {
            size += bucket.size();
        }
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> allElements = new ArrayList<>();
        for (List<E> bucket : buckets) {
            allElements.addAll(bucket);
        }
        return allElements.iterator();
    }

    private int getIndex(Object element) {
        return Math.abs(element.hashCode() % INITIAL_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        for (List<E> bucket : buckets) {
            bucket.clear();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (List<E> bucket : buckets) {
            Iterator<E> it = bucket.iterator();
            while (it.hasNext()) {
                if (!c.contains(it.next())) {
                    it.remove();
                    modified = true;
                }
            }
        }
        return modified;
    }

    @Override
    public Object[] toArray() {
        List<E> allElements = new ArrayList<>();
        for (List<E> bucket : buckets) {
            allElements.addAll(bucket);
        }
        return allElements.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        List<E> allElements = new ArrayList<>();
        for (List<E> bucket : buckets) {
            allElements.addAll(bucket);
        }
        return allElements.toArray(a);
    }
}
