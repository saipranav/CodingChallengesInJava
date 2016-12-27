package List;

/**
 * Created by Sai Pranav on 12/23/2016.
 */
public class SwapLinks{
  public static void main(String args[]){
    SingleLinkedList list = new SingleLinkedList();
    list.add(10);
    list.add(15);
    list.add(12);
    list.add(13);
    list.add(20);
    list.add(14);
    list.print();
    System.out.println("");
    list = swapLinks(list, 12, 20);
    list.print();
    System.out.println("");
    list = swapLinks(list, 10, 20);
    list.print();
    System.out.println("");
    list = swapLinks(list, 20, 10);
    list.print();
    System.out.println("");
    list = swapLinks(list, 12, 13);
    list.print();
    System.out.println("");
    list = swapLinks(list, 13, 12);
    list.print();
  }

  private static SingleLinkedList swapLinks (SingleLinkedList list, Object x, Object y) throws NullPointerException {
    if(list == null) {
      throw new NullPointerException("Empty List");
    }

    SingleLinkedList.Found X = list.find(x);
    SingleLinkedList.Found Y = list.find(y);

    if(X.key == null || Y.key == null){
      throw new NullPointerException("X or Y not found in list");
    }

    if(X.key == Y.key){
      // No need to swap
      return list;
    }

    if(X.next == Y.key){
      // Special case : 12 -> 13 to be swapped
      X.key.next = Y.next;
      Y.key.next = X.key;
      X.prev.next = Y.key;
    } else if (Y.next == X.key) {
      // Special case : 12 -> 13 but given as Y and X
      Y.key.next = X.next;
      X.key.next = Y.key;
      Y.prev.next = X.key;
    } else {
      // All other cases
      //       X           Y
      // 10 -> 15 -> 12 -> 13 -> 20

      X.key.next = Y.next;
      Y.key.next = X.next;

      if (X.prev != null) {
        X.prev.next = Y.key;
      }
      if (Y.prev != null) {
        Y.prev.next = X.key;
      }
    }

    // If head or tail is affected then change list head or tail respectively
    if(list.head == X.key){
      list.head = Y.key;
    } else if(list.head == Y.key){
      list.head = X.key;
    }

    if(list.tail == X.key){
      list.tail = Y.key;
    } else if(list.tail == Y.key){
      list.tail = X.key;
    }

    return list;
  }

}
