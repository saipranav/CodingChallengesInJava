package Trees;

import List.SingleLinkedList;

/**
 * Created by Sai Pranav on 12/30/2016.
 */
public class SortedListToBst<T> {
  public BinaryTreeNode<T> sortedListToBst(SingleLinkedList list, int numberOfNodes){
    list.resetPointers();
    return sortedListToBstRecursion(list, numberOfNodes);
  }

  private BinaryTreeNode<T> sortedListToBstRecursion(SingleLinkedList<T> list, int numberOfNodes){
    if(numberOfNodes <= 0){
      return null;
    }
    BinaryTreeNode<T> left = sortedListToBstRecursion(list, numberOfNodes/2);
    BinaryTreeNode<T> root = new BinaryTreeNode<T>(list.getNext());
    BinaryTreeNode<T> right = sortedListToBstRecursion(list, (numberOfNodes-numberOfNodes/2)-1);
    root.children[0] = left;
    root.children[1] = right;
    return root;
  }
}
