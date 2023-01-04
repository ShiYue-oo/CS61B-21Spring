package deque;

import java.util.Iterator;

public interface Deque <T>{
    public void addFirst(T item);
     public void addLast(T item);
    default boolean isEmpty(){
        if (this.size()==0)return true;
        else return false;
    }
     public int size();
    public void printDeque();
     public T removeFirst();
     public T removeLast();
    public T get(int index);
   public Iterator<T> iterator();
//    boolean equals(Object o);
}
