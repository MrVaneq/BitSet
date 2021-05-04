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

        for (int i = 0; i < size / intSize; i++) {
            array[i] = 0;
        }
    }

    public void add(int element) {
        if (element <= size) {
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
    }

    public void remove(int element) {
        if (element <= size) {
            int num = element % intSize;
            int intNum = Integer.MAX_VALUE - 1;
            if (element % intSize != 0) {
                element = element / intSize;
                for (int i = 0; i < intSize - num; i++) {
                    intNum <<= (1);
                    intNum |= 1;
                }
            } else {
                element = (element / intSize) - 1;
            }
            array[element] &= intNum;
        }
    }

    public BitSet union(BitSet set) {
        BitSet unioned = new BitSet(Math.max(size, set.size));
        int setLength = Math.min(array.length, set.array.length);

        for (int i = 0; i < unioned.array.length; i++) {
            if (i < setLength) {
                unioned.array[i] = array[i] | set.array[i];
            } else {
                if (setLength == array.length) {
                    unioned.array[i] = set.array[i];
                } else {
                    unioned.array[i] = array[i];
                }
            }
        }
        return unioned;
    }

    public BitSet intersect(BitSet set) {
        BitSet intersected = new BitSet(Math.min(size, set.size));
        for (int i = 0; i < intersected.array.length; i++) {
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