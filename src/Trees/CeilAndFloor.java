package Trees;

import java.util.Comparator;
import java.util.Stack;

/**
 * Created by Sai Pranav on 12/29/2016.
 */
public class CeilAndFloor<T> {
  private BinaryTreeNode<T> prev, next, temp, currentNode;
  public T ceil, floor;
  private Comparator<T> comparator;

  public void findCeilAndFloor(BinarySearchTree tree, T key){
    ceil = floor = null;
    comparator = tree.getComparator();
    Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
    int compareResult = 0;
    currentNode = tree.getRoot();

    if(currentNode == null){
      return;
    }

    while(currentNode != null){
      // Traverse to leftmost node
      stack.push(currentNode);
      currentNode = currentNode.children[0];
    }

    temp = tree.getRoot();
    compareResult = comparator.compare(temp.data, key);
    if(compareResult == 0){
      prev = temp;
      next = temp;
    } else {
      while(stack.isEmpty() == false){
        prev = currentNode;
        currentNode = stack.pop();
        compareResult = comparator.compare(currentNode.data, key);
        if(compareResult == 0) {
          prev = currentNode;
          next = currentNode;
          stack.clear();
          break;
        } else if (compareResult > 0){
          next = currentNode;
          stack.clear();
          break;
        } else {
          temp = currentNode;
          while(temp.children[1] != null){
            temp = temp.children[1];
            stack.push(temp);
            if(temp.children[0] != null){
              while(temp.children[0] != null){
                temp = temp.children[0];
                stack.push(temp);
                next = temp;
              }
            } else {
              next = temp;
            }
          }
        }
      }
    }

    // To handle key larger than last element in inorder elements
    compareResult = comparator.compare(currentNode.data, key);
    if(compareResult < 0){
      prev = currentNode;
      next = null;
    }

    // Set floor and ceil from prev and next
    if(stack.isEmpty() == true && ( prev != null || next != null)){
      if(prev != null){
        floor = prev.getData();
      }
      if(next != null){
        ceil = next.getData();
      }
    }
  }
}
