import java.util.ArrayList;
import java.util.Objects;

public class BitSet {
    ArrayList<Object> list;

    @Override
    public String toString() {
        return "BitSet{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BitSet bitSet = (BitSet) o;
        boolean ans = true;
        if(list.size() == bitSet.list.size())
        for (int i = 0; i < list.size(); i++) {
            if (!bitSet.list.contains(list.get(i))){
                ans = false;
                break;
            }
        }
        return ans;
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    public BitSet(Object[] setOfObjects, int N) {
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (!list.contains(setOfObjects[i]))
                list.add(setOfObjects[i]);
        }
    }

    public void add(Object addable) {
        if (!this.list.contains(addable)) {
            this.list.add(addable);
        }
    }

    public void add(Object[] addable) {
        for (Object o : addable) {
            if (!this.list.contains(o)) {
                this.list.add(o);
            }
        }
    }

    public void remove(Object removable) {
        if (this.list.contains(removable))
            this.list.remove(removable);
        else throw new IllegalArgumentException("this object is not in the set");
    }

    public void remove(Object[] removable) {
        for (int i = 0; i < removable.length; i++) {
            if (this.list.contains(removable[i])) {
                this.list.remove(removable[i]);
            } else throw new IllegalArgumentException("this object (index = " + i + ") is not in the set");
        }
    }

    public void intersect(BitSet other) {
        int i = 0;
        while (i < this.list.size()) {
            if (!other.list.contains(this.list.get(i))) {
                this.list.remove(i);
            } else i++;
        }
    }

    public void combine(BitSet other) {
        for (int i = 0; i < other.list.size(); i++) {
            if (!this.list.contains(other.list.get(i))) {
                this.list.add(other.list.get(i));
            }
        }
    }

    public boolean contains(Object checkable) {
        return this.list.contains(checkable);
    }

}
