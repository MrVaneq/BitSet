import java.util.Arrays;

class BitSet {

    private byte[] array; //elements
    private int size;
    private final int byteSize = Byte.SIZE; //8

    public BitSet(int size) {
        if (size <= 0) throw new IllegalArgumentException("Недопустимый размер");
        this.size = size;

        if (size % byteSize == 0) {
            array = new byte[size / byteSize];
        } else {
            array = new byte[size / byteSize + 1];
        }
    }

    public boolean add(int element) {
        if (element < 0 || element >= size) throw new IllegalArgumentException("Неверный номер элемента");
        if (contains(element)) return false;
        int index = element / byteSize;
        byte num = array[index];
        num = (byte) (num | (1 << (byteSize * (index + 1) - element - 1)));
        array[index] = num;
        return true;
    }

    public boolean add(int[] elements) {
        boolean wasModified = false;
        for (int element : elements) {
            if (add(element)) wasModified = true;
        }
        return wasModified;
    }

    public boolean remove(int element) {
        if (element < 0 || element >= size) throw new IllegalArgumentException("Неверный номер элемента");
        if (!contains(element)) return false;
        int index = element / byteSize;
        byte num = array[index];
        num = (byte) (num ^ (1 << (byteSize * (index + 1) - element - 1)));
        array[index] = num;
        return true;
    }

    public boolean remove(int[] elements) {
        boolean wasModified = false;
        for (int element : elements) {
            if (remove(element)) wasModified = true;
        }
        return wasModified;
    }

    public BitSet union(BitSet set) {
        if (size != set.size) throw new IllegalArgumentException("Разная длина битсетов");
        BitSet unioned = new BitSet(size);
        for (int i = 0; i < array.length; i++) {
            unioned.array[i] = (byte) (array[i] | set.array[i]);
        }
        return unioned;
    }

    public BitSet intersect(BitSet set) {
        if (size != set.size) throw new IllegalArgumentException("Разная длина битсетов");
        BitSet intersected = new BitSet(size);
        for (int i = 0; i < array.length; i++) {
            intersected.array[i] = (byte) (array[i] & set.array[i]);
        }
        return intersected;
    }

    public BitSet complement() {
        BitSet complemented = new BitSet(size);
        for (int i = 0; i < array.length; i++) {
            complemented.array[i] = (byte) ~array[i];
        }

        if (size % 8 != 0) {
            int octetPos = size / 8;
            complemented.array[octetPos] = (byte) (
                    (complemented.array[octetPos] & 0xE0) | ((array[octetPos]) & 0x1F));
        }
        return complemented;
    }

    public boolean contains(int element) {
        if (element < 0 || element >= size) throw new IllegalArgumentException("Неверный номер элемента");
        int index = element / byteSize;
        byte temp = array[index];
        return 1 == ((temp >> (byteSize * (index + 1) - element - 1)) & 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet bitset = (BitSet) o;
        if (size != bitset.size) return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != bitset.array[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 * size + Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            if (contains(i)) {
                sb.append(i).append(", ");
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        return sb.toString();
    }
}