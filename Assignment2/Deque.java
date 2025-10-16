import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque implements Iterable<Integer>{


    private static class Node{

    public Node( int d){ 
        data = d; 
    }

    public void next(Node n){
        next = n;
    }

    public int data;
    public Node next;
}

    private class NIterator implements Iterator<Integer>{

        private Deque.Node current;

        public NIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Integer next() {
            if (hasNext() == false) {
                throw new NoSuchElementException("No more elements");
            }
            int data = current.data;
            current = current.next;
            return data;
        }
    }

    public Iterator<Integer> iterator(){
        return new NIterator();
    }

    public int count;
    public Node first;
    public Node last;


    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void addFirst(int a){
        Node curr = new Node(a);
        if (isEmpty() == true){
            last = curr;
            first = curr;
        }
        else{ 
            curr.next(first);
            first = curr;
        }
    
        count += 1;
    }

    public void addLast(int a){
        Node curr = new Node(a);
        if (isEmpty() == true){
            last = curr;
            first = curr;
        }
        else{
            last.next(curr);
            last = curr;
            }

        count += 1;
    }

    public void removeFirst(){
        if (isEmpty() == false){
            first = first.next;
        }
        else{ 
            throw new NoSuchElementException("No elements can be removed because the deque is empty");
        }
        count -= 1;
    }

    public void removeLast(){
        if (isEmpty() == false){
            Node curr = first;
            Node before_curr = first;
            while (curr != last){
                before_curr = curr;
                curr = curr.next;
            }
            before_curr.next(null);
        }
        else{ 
            throw new NoSuchElementException("No elements can be removed because the deque is empty");
        }

        count -= 1;
    }


public static void main(String[] args) {
    Deque ll = new Deque();
    System.out.println(ll.size()); // 0
    System.out.println(ll.isEmpty()); // true
    //ll.removeFirst(); //triggers no such element error
    ll.addFirst(1);
    ll.addFirst(2);
    ll.addLast(3);

    for (int element: ll){
        System.out.println(element);
    }
    //2,1,3

    ll.removeFirst();
    System.out.println("First removed");
    for (int element: ll){
        System.out.println(element);
    }
    //1,3

    ll.removeLast();
    System.out.println("Last removed");
    for (int element: ll){
        System.out.println(element);
    }
    //1
}
}



