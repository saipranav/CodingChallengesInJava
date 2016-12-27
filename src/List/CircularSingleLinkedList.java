package List;

/**
 * Created by Sai Pranav on 12/23/2016.
 */
public class CircularSingleLinkedList<E> {
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

  public void add(E data) {
    Node newNode = new Node(data);
    if(this.tail != null) {
      this.tail.next = newNode;
      this.tail = newNode;
      newNode.next = this.head;
    } else {
      this.head = newNode;
      this.tail = newNode;
    }
    this.size++;
  }

  public void print(){
    Node runner = this.head;
    do {
      System.out.printf("%s -> ",runner.data);
      runner = runner.next;
    }while(runner != this.head);
    System.out.print("null");
  }
}
