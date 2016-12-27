package List;

/**
 * Created by Sai Pranav on 12/23/2016.
 */
public class SingleLinkedList<E> {
  Node<E> head;
  Node<E> tail;
  int size;

  public class Node<E> {
    E data;
    Node<E> next;

    Node(E data){
      this.data = data;
      this.next = null;
    }
  }

  public class Found{
    Node<E> prev;
    Node<E> key;
    Node<E> next;
  }

  public void add(E data) {
    Node newNode = new Node(data);
    if(this.tail != null) {
      this.tail.next = newNode;
      this.tail = newNode;
    } else {
      this.head = newNode;
      this.tail = newNode;
    }
    this.size++;
  }

  public Found find(E data){
    Found found = new Found();
    Node runner = this.head;
    while(runner != null){
      if(runner.data.equals(data)){
        found.key = runner;
        found.next = runner.next;
        if(runner == this.head) {
          found.prev = null;
        }
        break;
      }
      found.prev = runner;
      runner = runner.next;
    }
    return found;
  }

  public void print(){
    Node runner = this.head;
    while(runner != null){
      System.out.printf("%s -> ",runner.data);
      runner = runner.next;
    }
    System.out.print("null");
  }
}
