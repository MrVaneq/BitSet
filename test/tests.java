import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class tests {

    @Test
    public void remove() {
        BitSet<String> bitset1 = new BitSet<>(7);
        bitset1.add("fff");
        bitset1.add("aaa");
        bitset1.add("ppp");
        bitset1.add("zzz");
        bitset1.add("nnn");
        bitset1.remove("ppp");
        bitset1.remove(3);

        assertFalse(bitset1.contains("ppp"));
        assertFalse(bitset1.contains("zzz"));
        assertArrayEquals(new Object[]{"fff", "aaa", null, null, "nnn", null, null}, bitset1.getElements());
    }

    @Test
    public void add() {
        BitSet<String> bitset1 = new BitSet<>(5);
        bitset1.add("first");
        bitset1.add("hi");
        bitset1.add("hello");
        bitset1.add("test");
        bitset1.add("last");

        assertEquals(5, bitset1.getSize());
        assertEquals("last", bitset1.get(4));
        assertEquals("hello", bitset1.get(2));
        assertTrue(bitset1.contains("test"));
        assertArrayEquals(new Object[]{"first", "hi", "hello", "test", "last"}, bitset1.getElements());

        bitset1 = new BitSet<>(10);
        bitset1.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        assertArrayEquals(new Object[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}, bitset1.getElements());
        bitset1.remove(new String[]{"3", "4", "5", "7", "9"});
        assertArrayEquals(new Object[]{"0", "1", "2", null, null, null, "6", null, "8", null}, bitset1.getElements());
        assertEquals(5, bitset1.elementsSize());
    }

    @Test
    public void union() {
        BitSet<String> bitset1 = new BitSet<>(6);
        BitSet<String> bitset2 = new BitSet<>(6);
        bitset1.add("aaa");
        bitset1.add("oooo");
        bitset1.add("ppp");

        bitset2.add("123");
        bitset2.add("ppp");
        bitset2.add("hhh");

        Object[] expected = new Object[]{"aaa", "oooo", "ppp", "123", "hhh", null, null, null, null, null, null, null};
        Object[] actual = bitset1.union(bitset2).getElements();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void intersect() {
        BitSet<String> bitset1 = new BitSet<>(4);
        BitSet<String> bitset2 = new BitSet<>(4);
        bitset1.add("hi");
        bitset1.add("hello");
        bitset1.add("i");

        bitset2.add("666");
        bitset2.add("123");
        bitset2.add("hi");

        Object[] expected = new Object[]{"hi", null , null, null, null, null, null, null};
        Object[] actual = bitset1.intersect(bitset2).getElements();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void addition() {
        BitSet<String> bitset1 = new BitSet<>(5);
        BitSet<String> bitset2 = new BitSet<>(5);
        bitset1.add("q");
        bitset1.add("w");
        bitset1.add("e");
        bitset1.add("r");
        bitset1.add("t");

        bitset2.add("e");
        bitset2.add("r");
        bitset2.add("t");

        Object[] expected = new Object[]{"q", "w", null , null, null};
        Object[] actual = bitset1.addition(bitset2).getElements();
        assertArrayEquals(expected, actual);
    }

}