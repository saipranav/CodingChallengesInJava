package Graphs.test;

import Graphs.NodeNotFoundException;
import Graphs.Traversal;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class BinarySearchTree {
  public static void main(String args[]){
    Graphs.BinarySearchTree<Integer> tree = new Graphs.BinarySearchTree<Integer>(new IntegerComparator());
    tree.insert(2);
    tree.insert(1);
    tree.insert(4);
    tree.insert(3);
    tree.insert(6);
    tree.traverse(Traversal.INORDER);
    System.out.println(tree.contains(3));
    System.out.println(tree.contains(7));
    try{
      tree.delete(2);
    } catch(NodeNotFoundException e){
      System.out.println(e);
    }
    tree.traverse(Traversal.INORDER);
  }
}
