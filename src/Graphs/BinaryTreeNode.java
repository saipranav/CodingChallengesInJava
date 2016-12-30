package Graphs;

/**
 * Created by Sai Pranav on 12/29/2016.
 */
public class BinaryTreeNode<T> {
  T data;
  BinaryTreeNode<T>[] children;
  BinaryTreeNode<T> parent;

  public BinaryTreeNode(T data){
    this.data = data;
    this.children = new BinaryTreeNode[2];
    this.parent = null;
  }

  public T getData(){
    return data;
  }
}
