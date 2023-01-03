package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c){
        super();
        cmp=c;
    }
    public T max(){
        if(isEmpty())return null;
        T maxitem=this.get(0);
        Iterator<T> it=this.iterator();
        while(it.hasNext()){
            if(cmp.compare(it.next(),maxitem)>0){
                maxitem=it.next();
            }
        }
        return maxitem;
    }
    public T max(Comparator<T> c){
        if(isEmpty())return null;
        T maxitem=this.get(0);
        Iterator<T> it=this.iterator();
        while(it.hasNext()){
            if(c.compare(it.next(),maxitem)>0){
                maxitem=it.next();
            }
        }
        return maxitem;
    }
}
