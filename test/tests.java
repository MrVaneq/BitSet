import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

    @Test
    void add() {
        BitSet set1 = new BitSet(10 * 32);
        BitSet set2 = new BitSet(10 * 32);
        set1.add(0 * 32);
        set1.add(2 * 32);
        set1.add(5 * 32);
        set1.add(6 * 32);
        set1.add(9 * 32);

        set2.add(new int[]{0 * 32, 2 * 32, 5 * 32, 8 * 32});

        int[] expected1 = {1, 0, 1, 0, 0, 1, 1, 0, 0, 1};
        int[] actual1 = set1.getArray();
        int[] expected2 = {1, 0, 1, 0, 0, 1, 0, 0, 1, 0};
        int[] actual2 = set2.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
        assertThrows(IllegalArgumentException.class, () -> set1.add(22 * 32));
        assertThrows(IllegalArgumentException.class, () -> set2.add(new int[]{2 * 32, 55 * 32}));
    }

    @Test
    void remove() {
        BitSet set1 = new BitSet(10 * 32);
        BitSet set2 = new BitSet(10 * 32);
        set1.add(1 * 32);
        set1.add(2 * 32);
        set1.add(3 * 32);
        set1.add(6 * 32);
        set1.add(9 * 32);
        set1.remove(6 * 32);

        set2.add(new int[]{0 * 32, 2 * 32, 5 * 32, 8 * 32});
        set2.remove(new int[]{2 * 32, 5 * 32, 8 * 32});

        int[] expected1 = {0, 1, 1, 1, 0, 0, 0, 0, 0, 1};
        int[] actual1 = set1.getArray();
        int[] expected2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] actual2 = set2.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
        assertThrows(IllegalArgumentException.class, () -> set1.remove(22 * 32));
        assertThrows(IllegalArgumentException.class, () -> set2.remove(new int[]{2 * 32, 55 * 32}));
    }

    @Test
    void union() {
        BitSet set1 = new BitSet(10 * 32);
        BitSet set2 = new BitSet(10 * 32);
        BitSet set4 = new BitSet(11 * 32);
        set1.add(1 * 32);
        set1.add(4 * 32);
        set1.add(6 * 32);
        set1.add(8 * 32);
        set1.add(9 * 32);
        set2.add(1 * 32);
        set2.add(5 * 32);
        set2.add(6 * 32);
        set2.add(8 * 32);

        BitSet set3 = set1.union(set2);
        int[] expected = {0, 1, 0, 0, 1, 1, 1, 0, 1, 1};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, () -> set2.union(set4));
    }

    @Test
    void intersect() {
        BitSet set1 = new BitSet(10 * 32);
        BitSet set2 = new BitSet(10 * 32);
        BitSet set4 = new BitSet(11 * 32);
        set1.add(1 * 32);
        set1.add(4 * 32);
        set1.add(6 * 32);
        set1.add(8 * 32);
        set1.add(9 * 32);
        set2.add(1 * 32);
        set2.add(5 * 32);
        set2.add(6 * 32);
        set2.add(8 * 32);
        set2.add(9 * 32);

        BitSet set3 = set1.intersect(set2);
        int[] expected = {0, 1, 0, 0, 0, 0, 1, 0, 1, 1};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, () -> set2.union(set4));
    }

    @Test
    void complement() {
        BitSet set1 = new BitSet(10 * 32);
        BitSet set2 = new BitSet(10 * 32);
        set1.add(1 * 32);
        set1.add(3 * 32);
        set1.add(4 * 32);
        set1.add(6 * 32);
        set2.add(1 * 32);
        set2.add(2 * 32);
        set2.add(3 * 32);
        set2.add(6 * 32);
        set2.add(9 * 32);

        BitSet set3 = set1.complement();
        BitSet set4 = set2.complement();

        int[] expected1 = {-1, -2, -1, -2, -2, -1, -2, -1, -1, -1};
        int[] actual1 = set3.getArray();
        int[] expected2 = {-1, -2, -2, -2, -1, -1, -2, -1, -1, -2};
        int[] actual2 = set4.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void contains() {
        BitSet set1 = new BitSet(10 * 32);
        set1.add(1 * 32);
        set1.add(3 * 32);
        set1.add(4 * 32);
        set1.add(5 * 32);
        set1.add(6 * 32);

        assertTrue(set1.contains(3 * 32));
        assertTrue(set1.contains(4 * 32));
        assertFalse(set1.contains(2 * 32));
        assertFalse(set1.contains(9 * 32));
        assertThrows(IllegalArgumentException.class, () -> set1.contains(18 * 32));
        assertThrows(IllegalArgumentException.class, () -> set1.contains(22 * 32));
    }

}