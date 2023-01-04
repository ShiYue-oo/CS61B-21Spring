package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;
    public ArrayDeque(){
        items=(T []) new Object[8];
        size=0;
        head=1;
        tail=0;
    }
    private void resize(){
        T[] a=(T [])new Object[items.length*2];
        Iterator<T> it=this.iterator();
        int cnt=items.length-1;
        while(it.hasNext()){
            a[cnt]=it.next();
            cnt--;
        }
        head=items.length;
        tail=cnt;
        items=a;
    }
    private void subsize(){
        T[] a=(T[])new Object[items.length/2];
        Iterator<T> it=this.iterator();
        int cnt=size;
        while(it.hasNext()){
            a[cnt]=it.next();
            cnt--;
        }
        head=size+1;
        tail=0;
        items=a;
    }
    public void addFirst(T item){
        if(head==tail){
            this.resize();
        }
        items[head]=item;
        head=(head+1)%items.length;
        size++;
    }
    public void addLast(T item){
        if(head==tail){
            this.resize();
        }
        items[tail]=item;
        tail=(tail-1+items.length)%items.length;
        size++;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        int p=(head-1+items.length)%items.length;
        while(p!=tail){
            System.out.print(items[p]+" ");
            p=(p-1+items.length)%items.length;
        }
        System.out.printf("\n");
    }
    public T removeFirst(){
        if(this.isEmpty())return null;
        T res=items[(head-1+items.length)%items.length];
        head=(head-1+items.length)%items.length;
        size--;
        if(items.length>8&&size*4<=items.length){
            this.subsize();
        }
        return res;
    }
    public T removeLast(){
        if(this.isEmpty())return null;
        T res=items[(tail+1)%items.length];
        tail=(tail+1)%items.length;
        size--;
        if(items.length>8&&size*4<=items.length){
            this.subsize();
        }
        return res;
    }
    public T get(int index){
        if(index<0&&index>=(head-tail-1+items.length)%items.length)return null;
        return items[(head-index-1+items.length)%items.length];
    }
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        private ArrayDequeIterator(){
            wizPos=0;
        }

        @Override
        public boolean hasNext() {
            return wizPos!=size;
        }
        public T next(){
            T item=get(wizPos);
            wizPos+=1;
            return item;
        }
    }
    public boolean equals(Object o){
        if(this==o)return true;
        if(o==null)return false;
        if(!(o instanceof Deque))return false;
        Deque<T> other=(Deque<T>) o;
        if(this.size()!=other.size())return false;
        for(int i=0;i<this.size();i++){
            if(this.get(i).equals(other.get(i))){
                return false;
            }
        }
        return true;
    }

}
