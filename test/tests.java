import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

    @Test
    void add() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        set1.add(0);
        set1.add(2);
        set1.add(5);
        set1.add(6);
        set1.add(9);

        set2.add(new int[]{0, 2, 5, 8});

        int[] expected1 = {1, 0, 1, 0, 0, 1, 1, 0, 0, 1};
        int[] actual1 = set1.getArray();
        int[] expected2 = {1, 0, 1, 0, 0, 1, 0, 0, 1, 0};
        int[] actual2 = set2.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
        assertThrows(IllegalArgumentException.class, () -> set1.add(22));
        assertThrows(IllegalArgumentException.class, () -> set2.add(new int[]{2, 55}));
    }

    @Test
    void remove() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(6);
        set1.add(9);
        set1.remove(6);

        set2.add(new int[]{0, 2, 5, 8});
        set2.remove(new int[]{2, 5, 8});

        int[] expected1 = {0, 1, 1, 1, 0, 0, 0, 0, 0, 1};
        int[] actual1 = set1.getArray();
        int[] expected2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] actual2 = set2.getArray();
        assertArrayEquals(expected1, actual1);
        assertArrayEquals(expected2, actual2);
        assertThrows(IllegalArgumentException.class, () -> set1.remove(22));
        assertThrows(IllegalArgumentException.class, () -> set2.remove(new int[]{2, 55}));
    }

    @Test
    void union() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        BitSet set4 = new BitSet(11);
        set1.add(1);
        set1.add(4);
        set1.add(6);
        set1.add(8);
        set1.add(9);
        set2.add(1);
        set2.add(5);
        set2.add(6);
        set2.add(8);

        BitSet set3 = set1.union(set2);
        int[] expected = {0, 1, 0, 0, 1, 1, 1, 0, 1, 1};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, () -> set2.union(set4));
    }

    @Test
    void intersect() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        BitSet set4 = new BitSet(11);
        set1.add(1);
        set1.add(4);
        set1.add(6);
        set1.add(8);
        set1.add(9);
        set2.add(1);
        set2.add(5);
        set2.add(6);
        set2.add(8);
        set2.add(9);

        BitSet set3 = set1.intersect(set2);
        int[] expected = {0, 1, 0, 0, 0, 0, 1, 0, 1, 1};
        int[] actual = set3.getArray();
        assertArrayEquals(expected, actual);
        assertThrows(IllegalArgumentException.class, () -> set2.union(set4));
    }

    @Test
    void complement() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        set1.add(1);
        set1.add(3);
        set1.add(4);
        set1.add(6);
        set2.add(1);
        set2.add(2);
        set2.add(3);
        set2.add(6);
        set2.add(9);

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
        BitSet set1 = new BitSet(10);
        set1.add(1);
        set1.add(3);
        set1.add(4);
        set1.add(5);
        set1.add(6);

        assertTrue(set1.contains(3));
        assertTrue(set1.contains(4));
        assertFalse(set1.contains(2));
        assertFalse(set1.contains(9));
        assertThrows(IllegalArgumentException.class, () -> set1.contains(18));
        assertThrows(IllegalArgumentException.class, () -> set1.contains(22));
    }

}