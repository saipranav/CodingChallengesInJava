package Trees.Test;

import Trees.Traversal;

/**
 * Created by Sai Pranav on 12/26/2016.
 */
public class BinaryTree {
  public static void main(String args[]){
    Trees.BinaryTree<Integer> tree = new Trees.BinaryTree<Integer>();
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(5);
    tree.insert(6);
    tree.insert(7);
    tree.traverse(Traversal.INORDER);
    tree.traverse(Traversal.PREORDER);
    tree.traverse(Traversal.POSTORDER);
    tree.traverse(Traversal.BREDTH_FIRST);
    /*System.out.println(tree.contains(4));
    System.out.println(tree.contains(8));
    try{
      tree.delete(7);
    } catch(NodeNotFoundException e){
      System.out.println(e);
    }
    tree.traverse(Traversal.INORDER);
    System.out.println(tree.height());
    System.out.println(tree.diameter());*/
  }
}
