import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

    @Test
    void add() {
        BitSet set = new BitSet(320);
        set.add(32 * 2);
        set.add(32 * 5);
        set.add(32 * 6);
        set.add(32 * 9);

        int[] expected = {0, 1, 0, 0, 1, 1, 0, 0, 1, 0};
        int[] actual = set.getArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void remove() {
        BitSet set = new BitSet(352);
        set.add(32);
        set.add(32 * 2);
        set.add(32 * 3);
        set.add(32 * 6);
        set.add(32 * 11);
        set.remove(32 * 6);

        int[] expected = {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] actual = set.getArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void union() {
        BitSet set1 = new BitSet(320);
        BitSet set2 = new BitSet(320);
        set1.add(32);
        set1.add(32 * 4);
        set1.add(32 * 6);
        set1.add(32 * 8);
        set1.add(32 * 9);
        set2.add(32);
        set2.add(32 * 5);
        set2.add(32 * 6);
        set2.add(32 * 8);
        set2.add(32 * 10);

        BitSet set3 = set1.union(set2);
        int[] expected = {1, 0, 0, 1, 1, 1, 0, 1, 1, 1};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void intersect() {
        BitSet set1 = new BitSet(320);
        BitSet set2 = new BitSet(320);
        set1.add(32);
        set1.add(32 * 4);
        set1.add(32 * 6);
        set1.add(32 * 8);
        set1.add(32 * 9);
        set2.add(32);
        set2.add(32 * 5);
        set2.add(32 * 6);
        set2.add(32 * 8);
        set2.add(32 * 10);

        BitSet set3 = set1.intersect(set2);
        int[] expected = {1, 0, 0, 0, 0, 1, 0, 1, 0, 0};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void complement() {
        BitSet set1 = new BitSet(320);
        BitSet set2 = new BitSet(320);
        set1.add(32);
        set1.add(32 * 3);
        set1.add(32 * 4);
        set1.add(32 * 6);
        set2.add(32);
        set2.add(32 * 2);
        set2.add(32 * 3);
        set2.add(32 * 6);
        set2.add(32 * 9);

        BitSet set3 = set1.complement();
        BitSet set4 = set2.complement();

        int[] expected1 = {-2, -1, -2, -2, -1, -2, -1, -1, -1, -1};
        int[] actual1 = set3.getArray();
        int[] expected2 = {-2, -2, -2, -1, -1, -2, -1, -1, -2, -1};
        int[] actual2 = set4.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void contains() {
        BitSet set1 = new BitSet(320);
        set1.add(32);
        set1.add(32 * 3);
        set1.add(32 * 4);
        set1.add(32 * 5);
        set1.add(32 * 6);

        assertTrue(set1.contains(32 * 3));
        assertTrue(set1.contains(32 * 4));
        assertFalse(set1.contains(32 * 2));
        assertFalse(set1.contains(32 * 9));
    }

}