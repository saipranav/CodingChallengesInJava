package List;

/**
 * Created by Sai Pranav on 12/23/2016.
 */
public class SplitCircularLinkedListIntoTwo {
  public static void main(String args[]){
    CircularSingleLinkedList list = new CircularSingleLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    list.print();
    splitList(list);
  }

  public static void splitList(CircularSingleLinkedList list){
    CircularSingleLinkedList.Node prevFirst = list.head;
    CircularSingleLinkedList.Node prevSecond = list.head;
    CircularSingleLinkedList.Node first = prevFirst.next;
    if (prevFirst.next != list.head){
      first = prevFirst.next.next;
    }
    CircularSingleLinkedList.Node second = prevSecond.next;

    while(first != list.head && first.next != list.head){
      prevFirst = first.next;
      first = first.next.next;
      prevSecond = second;
      second = second.next;
    }

    if(first.next == list.head){
      // Even number of elements
      prevFirst = first;
    } else {
      // Odd number of elements
      prevSecond = second;
      second = second.next;
    }

    prevSecond.next = list.head;
    prevFirst.next = second;
  }
}
