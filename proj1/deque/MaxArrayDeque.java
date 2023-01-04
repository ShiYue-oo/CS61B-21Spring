package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {
        if (isEmpty()) return null;
        T maxitem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (cmp.compare(this.get(i), maxitem) > 0) {
                maxitem = this.get(i);
            }
        }
        return maxitem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) return null;
        T maxitem = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(this.get(i), maxitem) > 0) {
                maxitem = this.get(i);
            }
        }
        return maxitem;
    }

}
