package Graphs.test;

import Graphs.BinaryTreeNode;
import Graphs.LowestCommonAncestor;
import Graphs.NodeNotFoundException;
import Graphs.Traversal;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class BinarySearchTree {
  public static void main(String args[]){
    Graphs.BinarySearchTree<Integer> tree1 = new Graphs.BinarySearchTree<Integer>(new IntegerComparator());
    tree1.insert(2);
    tree1.insert(1);
    tree1.insert(4);
    tree1.insert(3);
    tree1.insert(6);
    tree1.traverse(Traversal.INORDER);
    System.out.println(tree1.contains(3));
    System.out.println(tree1.contains(7));
    try{
      tree1.delete(2);
    } catch(NodeNotFoundException e){
      System.out.println(e);
    }
    tree1.traverse(Traversal.INORDER);
    System.out.println(tree1.isBST());

    Graphs.BinarySearchTree<Integer> tree2 = new Graphs.BinarySearchTree<Integer>(new IntegerComparator());
    tree2.insert(20);
    tree2.insert(22);
    tree2.insert(8);
    tree2.insert(4);
    tree2.insert(12);
    tree2.insert(10);
    tree2.insert(14);
    tree2.traverse(Traversal.INORDER);

    LowestCommonAncestor<Integer> lca = new LowestCommonAncestor<Integer>();
    BinaryTreeNode<Integer> lcaNode = lca.lowestCommonAncestor(tree2, 4, 22);
    if(lcaNode != null){
      System.out.println("LCA : "+lcaNode.getData());
    }
  }
}
