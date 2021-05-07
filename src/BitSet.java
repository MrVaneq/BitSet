import java.util.Arrays;

class BitSet {

    private int[] array; //elements
    private int size;
    private final int intSize = Integer.SIZE; //32

    public BitSet(int size) {
        this.size = size;
        if (size % intSize == 0) {
            array = new int[size / intSize];
        } else {
            array = new int[(size / intSize) + 1];
        }
    }

    public void add(int element) {
        element += intSize;
        if (element > size) throw new IllegalArgumentException("Неверный номер элемента");
        int num = element % intSize;
        int intNum = 1;
        if (element % intSize != 0) {
            element = element / (intSize + 1);
            intNum = intNum << (intSize - num);
        } else {
            element = (element / intSize) - 1;
        }
        array[element] |= intNum;
    }

    public void add(int[] elements) {
        for (int element : elements) {
            add(element);
        }
    }

    public void remove(int element) {
        element += intSize;
        if (element > size) throw new IllegalArgumentException("Неверный номер элемента");
        int num = element % intSize;
        int intNum = Integer.MAX_VALUE - 1;
        if (element % intSize != 0) {
            element = element / intSize;
            intNum = intNum << (intSize - num);
            intNum = ~intNum;

        } else {
            element = (element / intSize) - 1;
        }
        array[element] &= intNum;
    }

    public void remove(int[] elements) {
        for (int element : elements) {
            remove(element);
        }
    }

    public BitSet union(BitSet set) {
        if (size != set.size) throw new IllegalArgumentException("Разная длина битсетов");
        BitSet unioned = new BitSet(size);
        for (int i = 0; i < array.length; i++) {
            unioned.array[i] = array[i] | set.array[i];
        }
        return unioned;
    }

    public BitSet intersect(BitSet set) {
        if (size != set.size) throw new IllegalArgumentException("Разная длина битсетов");
        BitSet intersected = new BitSet(size);
        for (int i = 0; i < array.length; i++) {
            intersected.array[i] = array[i] & set.array[i];
        }
        return intersected;
    }

    public BitSet complement() {
        BitSet complemented = new BitSet(size);
        for (int i = 0; i < size / intSize; i++) {
            complemented.array[i] = ~array[i];
        }
        return complemented;
    }

    public boolean contains(int element) {
        element += intSize;
        if (element > size) throw new IllegalArgumentException("Неверный номер элемента");
        int temp = array[element / (intSize + 1)];
        temp >>= (intSize - (element % intSize));
        return 1 == temp % 2;
    }

    public int[] getArray() {
        return array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet bitset = (BitSet) o;
        return size == bitset.size && Arrays.equals(array, bitset.array);
    }

    @Override
    public int hashCode() {
        return 31 * size + Arrays.hashCode(array);
    }
}