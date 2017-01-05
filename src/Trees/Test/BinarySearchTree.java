package Trees.Test;

import Trees.*;
import List.SingleLinkedList;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class BinarySearchTree {
  public static void main(String args[]){
    Trees.BinarySearchTree<Integer> tree1 = new Trees.BinarySearchTree<Integer>(new IntegerComparator());
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

    Trees.BinarySearchTree<Integer> tree2 = new Trees.BinarySearchTree<Integer>(new IntegerComparator());
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

    CeilAndFloor<Integer> ceilAndFloor = new CeilAndFloor<Integer>();
    ceilAndFloor.findCeilAndFloor(tree2, 3);
    System.out.println("Key:" + 3 + " Ceil:" + ceilAndFloor.ceil + " Floor:" + ceilAndFloor.floor);
    ceilAndFloor.findCeilAndFloor(tree2, 12);
    System.out.println("Key:" + 12 + " Ceil:" + ceilAndFloor.ceil + " Floor:" + ceilAndFloor.floor);
    ceilAndFloor.findCeilAndFloor(tree2, 13);
    System.out.println("Key:" + 13 + " Ceil:" + ceilAndFloor.ceil + " Floor:" + ceilAndFloor.floor);
    ceilAndFloor.findCeilAndFloor(tree2, 23);
    System.out.println("Key:" + 23 + " Ceil:" + ceilAndFloor.ceil + " Floor:" + ceilAndFloor.floor);

    SingleLinkedList list = new SingleLinkedList();
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    list.add(6);
    SortedListToBst<Integer> sortedListToBst = new SortedListToBst<Integer>();
    BinaryTreeNode<Integer> rootNode = sortedListToBst.sortedListToBst(list, list.getSize());
    // Debug and Test rootNode for now

  }
}
