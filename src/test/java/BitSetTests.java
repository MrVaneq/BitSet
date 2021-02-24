import org.junit.Test;

import static org.junit.Assert.*;

public class BitSetTests {
    private Integer[] a1 = {1, 2, 3, 4, 5}, a2 = {3, 4, 5, 6};

    @Test
    public void addAndRemoveTest() {
        BitSet a = new BitSet(a1, 5);
        Integer[] b1 = {2, 1, 3, 4, 5};
        Integer[] b2 = {2, 3, 4, 5};
        Integer[] b3 = {2, 3, 4, 5, 7};
        Integer[] b4 = {2, 4, 5};
        Integer[] b5 = {2, 4, 5, 7, 9};
        Integer[] removable = {3, 7};
        Integer[] addable = {7, 9};
        BitSet B1 = new BitSet(b1, 5), B2 = new BitSet(b2, 4), B3 = new BitSet(b3, 5),
                B4 = new BitSet(b4, 3), B5 = new BitSet(b5, 5);
        a.add(3);
        assertEquals(B1, a);
        a.remove(1);
        assertEquals(B2, a);
        a.add(7);
        assertEquals(B3, a);
        a.remove(removable);
        assertEquals(B4, a);
        a.add(addable);
        assertEquals(B5, a);
    }

    @Test
    public void intersectionTest() {
        BitSet a = new BitSet(a1, 5), b = new BitSet(a2, 4);
        Integer[] b1 = {3, 5, 4};
        BitSet B1 = new BitSet(b1, 3);
        a.intersect(b);
        assertEquals(B1, a);
    }

    @Test
    public void combineTest() {
        BitSet a = new BitSet(a1, 5), b = new BitSet(a2, 4);
        Integer[] b1 = {1, 2, 3, 5, 4, 6};
        BitSet B1 = new BitSet(b1, 3);
        a.combine(b);
        assertEquals(B1, a);
    }

    @Test
    public void containsTest() {
        BitSet a = new BitSet(a1, 5);
        assertEquals(true, a.contains(3));
        assertEquals(false, a.contains(13));
    }
}
