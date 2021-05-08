import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitSetTest {

    @Test
    void add() {
        BitSet set1 = new BitSet(10);
        assertTrue(set1.add(0));
        assertTrue(set1.add(2));
        assertTrue(set1.add(5));
        assertTrue(set1.add(6));
        assertTrue(set1.add(9));
        assertFalse(set1.add(9));
        assertFalse(set1.add(2));
        assertThrows(IllegalArgumentException.class, () -> set1.add(22));
        assertThrows(IllegalArgumentException.class, () -> set1.add(-57));

        BitSet set2 = new BitSet(9);
        assertTrue(set2.add(new int[]{0, 2, 5, 6, 8}));
        assertFalse(set2.add(new int[]{0, 2, 8}));
        assertThrows(IllegalArgumentException.class, () -> set2.add(new int[]{-1, 0}));
        assertThrows(IllegalArgumentException.class, () -> set2.add(new int[]{12, -9}));

        BitSet expected1 = new BitSet(10);
        BitSet expected2 = new BitSet(9);
        expected1.add(new int[]{0, 2, 5, 6, 9});
        expected2.add(new int[]{0, 2, 5, 6, 8});

        BitSet notexpected1 = new BitSet(9);
        BitSet notexpected2 = new BitSet(19);
        notexpected1.add(new int[]{0, 2, 5, 6, 7});
        notexpected2.add(new int[]{0, 2, 5, 6, 6, 8});

        assertEquals(expected1, set1);
        assertEquals(expected2, set2);
        assertNotEquals(notexpected1, set1);
        assertNotEquals(notexpected2, set1);
        assertNotEquals(notexpected1, set2);
        assertNotEquals(notexpected2, set2);
    }

    @Test
    void remove() {
        BitSet set1 = new BitSet(22);
        set1.add(new int[]{0, 2, 5, 6, 9, 12, 19});
        assertTrue(set1.remove(0));
        assertTrue(set1.remove(5));
        assertTrue(set1.remove(6));
        assertTrue(set1.remove(9));
        assertFalse(set1.remove(9));
        assertFalse(set1.remove(0));
        assertThrows(IllegalArgumentException.class, () -> set1.remove(25));
        assertThrows(IllegalArgumentException.class, () -> set1.remove(-57));

        BitSet set2 = new BitSet(34);
        set2.add(new int[]{1, 3, 4, 5, 6, 17, 20});
        assertTrue(set2.remove(new int[]{1, 3, 4, 5, 6}));
        assertFalse(set2.remove(new int[]{1, 3, 4}));
        assertThrows(IllegalArgumentException.class, () -> set2.remove(new int[]{-1, 0}));
        assertThrows(IllegalArgumentException.class, () -> set2.remove(new int[]{90, -9}));

        BitSet expected1 = new BitSet(22);
        BitSet expected2 = new BitSet(34);
        expected1.add(new int[]{2, 12, 19});
        expected2.add(new int[]{17, 20});

        BitSet notexpected1 = new BitSet(13);
        BitSet notexpected2 = new BitSet(19);
        notexpected1.add(new int[]{0, 2, 5, 6, 9});
        notexpected2.add(new int[]{0, 2, 5, 6, 6, 8});

        assertEquals(expected1, set1);
        assertEquals(expected2, set2);
        assertNotEquals(notexpected1, set1);
        assertNotEquals(notexpected2, set1);
        assertNotEquals(notexpected1, set2);
        assertNotEquals(notexpected2, set2);
    }

    @Test
    void union() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        BitSet set3 = new BitSet(10);
        BitSet set4 = new BitSet(11);

        set1.add(new int[]{1, 4, 6, 8, 9});
        set2.add(new int[]{1, 5, 7, 8, 9});
        set3.add(new int[]{1, 4, 5, 6, 7, 8, 9});

        assertEquals(set3, set1.union(set2));
        assertThrows(IllegalArgumentException.class, () -> set2.union(set4));
    }

    @Test
    void intersect() {
        BitSet set1 = new BitSet(10);
        BitSet set2 = new BitSet(10);
        BitSet set3 = new BitSet(10);
        BitSet set4 = new BitSet(11);

        set1.add(new int[]{1, 4, 6, 8, 9});
        set2.add(new int[]{1, 5, 7, 8, 9});
        set3.add(new int[]{1, 8, 9});

        assertEquals(set3, set1.intersect(set2));
        assertThrows(IllegalArgumentException.class, () -> set2.intersect(set4));
    }

    @Test
    void complement() {
        int size = 24;
        BitSet set1 = new BitSet(size);
        BitSet set2 = new BitSet(size);
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) set1.add(i);
            else set2.add(i);
        }
        assertEquals(set1.toString(), set2.complement().toString());
        assertEquals(set2.toString(), set1.complement().toString());
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
        assertThrows(IllegalArgumentException.class, () -> set1.contains(-18));
        assertThrows(IllegalArgumentException.class, () -> set1.contains(22));
    }

}