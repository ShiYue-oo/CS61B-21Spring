package deque;
import java.util.Iterator;
public class LinkedListDeque<T> implements Deque<T>{

    private class Node{  //这里嵌套类在加T 会有歧义
        public T item;
        public Node pre;
        public Node next;
        public Node(T i){
            item=i;
            pre=null;
            next=null;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    public LinkedListDeque(){
        head=new Node(null);
        tail=new Node(null);
        head.next=tail;
        tail.pre=head;
        size=0;
    }
    public void addFirst(T item){
        size++;
        Node temp= new Node(item);
        temp.next=head.next;
        head.next=temp;
        temp.next.pre=temp;
        temp.pre=head;
    }
    public void addLast(T item){
        size++;
        Node temp=new Node(item);
        temp.pre=tail.pre;
        tail.pre=temp;
        temp.pre.next=temp;
        temp.next=tail;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        for(Node p=head.next;p!=tail;p=p.next){
            System.out.print(p.item+" ");
        }
        System.out.printf("\n");
    }
    public T removeFirst(){
        if(size==0)return null;
        Node p= head.next;
        head.next=p.next;
        p.next.pre=head;
        size--;
        T res= p.item;
        p=null;
        return res;
    }
    public T removeLast(){
        if(size==0)return null;
        Node p=tail.pre;
        tail.pre=p.pre;
        p.pre.next=tail;
        size--;
        T res=p.item;
        p=null;
        return res;
    }
    public T get(int index){
        if(index<0||index>=size)return null;
        int cnt=0;
        Node p=head.next;
        while(cnt!=index){
            cnt++;
            p=p.next;
        }
        return p.item;
    }
    private Node getRecursivehelp(int index,Node p){
        if(index!=0){
            return getRecursivehelp(index-1,p.next);
        }else{
            return p;
        }
    }
    public T getRecursive(int index){
        if(index<0||index>=size)return null;
        return getRecursivehelp(index,head.next).item;
    }
    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }
    private class LinkedListIterator implements Iterator<T>{
        private int wizPos;
        private LinkedListIterator(){
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
        Iterator<T> it1=this.iterator();
        Iterator<T> it2=other.iterator();
        while(it1.hasNext()){
            if(it1.next()!=it2.next()){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int n = 99;

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i <= n; i++) {
            lld1.addLast(i);
        }

        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = n; i >= 0; i--) {
            lld2.addFirst(i);
        }

        lld1.printDeque();

        System.out.println(lld1.equals(lld2));

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            ad1.addLast(i);
        }

        System.out.println(lld1.equals(ad1));
    }
}