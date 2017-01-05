package Trees;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sai Pranav on 12/29/2016.
 */
public class LowestCommonAncestor<T> {
  Set<BinaryTreeNode<T>> set = new HashSet<BinaryTreeNode<T>>();
  Comparator<T> comparator;

  public BinaryTreeNode lowestCommonAncestor(BinarySearchTree<T> tree1, T key1, T key2){
    if(tree1.contains(key1) != true || tree1.contains(key2) != true){
      return null;
    }
    comparator = tree1.getComparator();
    putInMap(tree1.getRoot(), key1);
    return lowestCommonAncestorRecursion(tree1.getRoot(), key2, null);
  }

  private BinaryTreeNode lowestCommonAncestorRecursion(BinaryTreeNode<T> node, T key, BinaryTreeNode<T> prev){
    if(node == null){
      return null;
    }
    if(set.contains(node) == true){
      int compareResult = comparator.compare(node.data, key);
      if(compareResult > 0){
        prev = node;
        return lowestCommonAncestorRecursion(node.children[0], key, prev);
      } else if(compareResult < 0){
        prev = node;
        return lowestCommonAncestorRecursion(node.children[1], key, prev);
      } else {
        return node;
      }
    } else {
      return prev;
    }
  }

  private void putInMap(BinaryTreeNode<T> node, T key){
    if(node == null){
      return;
    }

    set.add(node);

    int compareResult = comparator.compare(node.data, key);
    if(compareResult > 0){
      putInMap(node.children[0], key);
    } else if(compareResult < 0){
      putInMap(node.children[1], key);
    }
  }
}
