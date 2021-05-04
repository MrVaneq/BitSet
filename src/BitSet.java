import java.util.Arrays;

public class BitSet<T> {
    private int size;
    private Object[] elements;

    public BitSet(int size) {
        this.size = size;
        elements = new Object[size];
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    public void add(T o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    elements[i] = o;
                    break;
                }
                if (i == size - 1) throw new IllegalArgumentException("Bitset is full");
            }
        }
    }

    public void add(Object[] objects) {
        for (Object object : objects) {
            for (int j = 0; j < size; j++) {
                if (elements[j] == null) {
                    elements[j] = object;
                    break;
                }
                if (j == size - 1) throw new IllegalArgumentException("Bitset is full");
            }
        }
    }

    public void remove(T o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                elements[i] = null;
            }
        }
    }

    public void remove(Object[] objects) {
        for (Object object : objects) {
            for (int j = 0; j < size; j++) {
                if (elements[j] != null && object != null) {
                    if (elements[j].equals(object)) {
                        elements[j] = null;
                        break;
                    }
                }
            }
        }
    }

    public void remove(int i) {
        elements[i] = null;
    }

    public BitSet<T> addition(BitSet second) {
        BitSet<T> additioned = new BitSet(size);
        additioned.add(elements);
        for (Object o1 : getElements()) {
            for (Object o2 : second.getElements()) {
                if (o1.equals(o2)) {
                    additioned.remove((T) o1);
                }
            }
        }
        return additioned;
    }

    public BitSet<T> union(BitSet second) {
        BitSet unioned = new BitSet(second.size + size);
        for (Object o : getElements()) {
            if (!unioned.contains(o)) unioned.add(o);
        }
        for (Object o : second.getElements()) {
            if (!unioned.contains(o)) unioned.add(o);
        }
        return unioned;
    }

    public BitSet<T> intersect(BitSet second) {
        BitSet<T> intersected = new BitSet<>(size + second.size);
        for (Object o1 : getElements()) {
            for (Object o2 : second.getElements()) {
                if (o1 != null && o2 != null) {
                    if (!o1.getClass().equals(o2.getClass())) throw new IllegalArgumentException("Different raw types");
                    if (o1.equals(o2)) {
                        intersected.add((T) o1);
                    }
                }
            }
        }
        return intersected;
    }

    public boolean contains(T o) {
        if (o == null) return false;
        for (Object obj : getElements()) {
            if (obj != null) {
                if (obj.equals(o)) return true;

            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet bitset = (BitSet) o;
        return size == bitset.size && Arrays.equals(elements, bitset.elements);
    }

    @Override
    public int hashCode() {
        return 31 * size + Arrays.hashCode(elements);
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();
        for (Object o : getElements()) {
            if (o != null) {
                String appended;
                appended = o.toString();
                answer.append(appended).append("\n");
            }
        }
        return answer.toString();
    }

    public Object[] getElements() {
        return elements;
    }

    public int getSize() {
        return size;
    }

    public T get(int i) {
        return (T) elements[i];
    }

    public int elementsSize() {
        int answer = 0;
        for (Object o : elements) {
            if (o != null) {
                answer++;
            }
        }
        return answer;
    }
}