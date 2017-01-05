package Trees;

import java.util.*;

/**
 * Created by Sai Pranav on 12/26/2016.
 */
/* Always maintains complete binary tree */
public class BinaryTree<T> {

  private BinaryTreeNode<T> root;
  private Set<BinaryTreeNode<T>> visited;
  private int maxChildrenPerNode = 2;

  public BinaryTree(){
    this.root = null;
    this.visited = new HashSet<BinaryTreeNode<T>>();
  }

  public void insert(T data){
    BinaryTreeNode<T> newParent = findBestPlaceToInsert();
    BinaryTreeNode<T> newChild = new BinaryTreeNode<T>(data);
    if(newParent == null){
      root = newChild;
    } else {
      if(newParent.children[0] == null){
        newParent.children[0] = newChild;
      } else {
        newParent.children[1] = newChild;
      }
      newChild.parent = newParent;
    }
  }

  public Boolean contains(T data){
    return contains(data, Traversal.BREDTH_FIRST);
  }

  public Boolean contains(T key, Traversal traversalType){
    BinaryTreeNode returnableNode;
    switch(traversalType){
      case INORDER:{
        returnableNode = findNodeByInOrder(root, key);
        break;
      }
      case BREDTH_FIRST: default:{
        returnableNode = findNodeByBreadthFirst(root, key);
        break;
      }
    }
    if(returnableNode == null){
      return false;
    } else {
      return true;
    }
  }

  public void delete(T data) throws NodeNotFoundException{
    BinaryTreeNode toBeDeleted = findNodeByInOrder(root, data);
    if(toBeDeleted == null){
      throw new NodeNotFoundException("Node to be deleted is not found");
    }

    BinaryTreeNode swappableLeaf = findBestLeafToSwap(root, data);
    if(swappableLeaf == null){
      if(root == null){
        // This must not happen as we have something to delete but nothing to swap
        return;
      }
      swappableLeaf = root;
    }

    BinaryTreeNode rightMostLeaf = findRightMostLeaf(root);
    if(rightMostLeaf == toBeDeleted){
      for(int index = 0; index < maxChildrenPerNode; index++){
        if( toBeDeleted.parent.children[index] == toBeDeleted){
          toBeDeleted.parent.children[index] = null;
        }
      }
      toBeDeleted.data = null;
      toBeDeleted.children = null;
      toBeDeleted.parent = null;
      if(root == toBeDeleted){
        root = null;
      }
    } else {
      toBeDeleted.data = swappableLeaf.data;
      for(int index = 0; index < maxChildrenPerNode; index++){
        if( swappableLeaf.parent.children[index] == swappableLeaf){
          swappableLeaf.parent.children[index] = null;
        }
      }
      swappableLeaf.data = null;
      swappableLeaf.children = null;
      swappableLeaf.parent = null;
    }
  }

  public void traverse(Traversal traversalType){
    switch (traversalType){
      case INORDER:
        inOrderTraversal(root);
        System.out.println();
        /*inOrderTraversalUsingStack(root);
        System.out.println();*/
        break;
      case PREORDER:
        preOrderTraversal(root);
        System.out.println();
        break;
      case POSTORDER:
        postOrderTraversal(root);
        System.out.println();
        break;
      case BREDTH_FIRST:
        breadthFirstTraversal(root);
        System.out.println();
        break;
    }
  }

  public int height(){
    return calcHeight(root);
  }

  private int calcHeight(BinaryTreeNode<T> node){
    if(node == null){
      return 0;
    }

    return 1 + Math.max( calcHeight(node.children[0]), calcHeight(node.children[1]) );
  }

  public int diameter(){
    return calcDiameter(root);
  }

  private int calcDiameter(BinaryTreeNode<T> node){
    if(node == null){
      return 0;
    }

    int totalHeight = 1 + calcHeight(node.children[0]) + calcHeight(node.children[1]);
    int leftDiameter = calcDiameter(node.children[0]);
    int rightDiameter = calcDiameter(node.children[1]);
    return Math.max(totalHeight, Math.max(leftDiameter, rightDiameter));
  }

  private void inOrderTraversal(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }

    inOrderTraversal(node.children[0]);
    System.out.print(" [ "+node.data.toString()+" ] ");
    inOrderTraversal(node.children[1]);
  }

  private void inOrderTraversalUsingStack(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }
    Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();
    BinaryTreeNode<T> currentNode = node;

    while(true){
      while(currentNode != null){
        stack.push(currentNode);
        currentNode = currentNode.children[0];
      }
      if(stack.isEmpty() == true){
        break;
      }
      currentNode = stack.pop();
      System.out.print(" [ " + currentNode.data + " ] ");
      if(currentNode.children[1] != null){
        currentNode = currentNode.children[1];
      }
    }
  }

  private void preOrderTraversal(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }

    System.out.print(" [ "+node.data.toString()+" ] ");
    preOrderTraversal(node.children[0]);
    preOrderTraversal(node.children[1]);
  }

  private void postOrderTraversal(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }

    postOrderTraversal(node.children[0]);
    postOrderTraversal(node.children[1]);
    System.out.print(" [ "+node.data.toString()+" ] ");
  }

  private void breadthFirstTraversal(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }
    Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
    queue.add(node);
    while(queue.isEmpty() == false){
      BinaryTreeNode<T> currentNode = queue.remove();
      System.out.print(" [ " + currentNode.data.toString() + " ] ");
      for(int index = 0; index < currentNode.children.length; index++){
        if(currentNode.children[index] != null){
          queue.add(currentNode.children[index]);
        }
      }
    }
  }

  private BinaryTreeNode findBestPlaceToInsert() {
    if(root == null){
      return null;
    }
    // Do breadth first search and insert the node
    Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
    queue.add(root);

    while(queue.isEmpty() == false){
      BinaryTreeNode<T> currentNode = queue.remove();
      if (isVisited(currentNode) == false && numberOfChildren(currentNode) < maxChildrenPerNode){
        clearVisited();
        return currentNode;
      }
      for( int index = 0; index < currentNode.children.length; index++){
        if(currentNode.children[index] != null){
          queue.add(currentNode.children[index]);
        }
      }
      setVisited(currentNode);
    }
    // It is a perfect tree as of now

    // Below return null should never occur as
    // there will be some leaf node which does
    // not have children at full capacity
    // for example left most leaf node
    clearVisited();
    return null;
  }

  private BinaryTreeNode findBestLeafToSwap(BinaryTreeNode<T> node, T key){
    if(node == null){
      return null;
    }
    if(numberOfChildren(node) == 0 && !(node.data.equals(key)) ){
      return node;
    }
    BinaryTreeNode returnableNode;
    returnableNode = findBestLeafToSwap(node.children[1], key);
    if(returnableNode != null){
      return returnableNode;
    }
    returnableNode = findBestLeafToSwap(node.children[0], key);
    if(returnableNode != null){
      return returnableNode;
    }
    return null;
  }

  private BinaryTreeNode findNodeByInOrder(BinaryTreeNode<T> node, T key){
    if(node == null){
      return null;
    }
    BinaryTreeNode returnableNode;
    returnableNode = findNodeByInOrder(node.children[0], key);
    if(returnableNode != null){
      return returnableNode;
    }
    if(node.data.equals(key)){
      return node;
    }
    returnableNode = findNodeByInOrder(node.children[1], key);
    if(returnableNode != null){
      return returnableNode;
    }
    return null;
  }

  private BinaryTreeNode findNodeByBreadthFirst(BinaryTreeNode<T> node, T key){
    if (node == null){
      return null;
    }

    Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
    queue.add(node);
    while(queue.isEmpty() == false){
      BinaryTreeNode currentNode = queue.remove();
      if(currentNode.data.equals(key)){
        return currentNode;
      }
      for(int index = 0; index < maxChildrenPerNode; index++){
        if(currentNode.children[index] != null){
          queue.add(currentNode.children[index]);
        }
      }
    }
    return null;
  }

  private BinaryTreeNode findRightMostLeaf(BinaryTreeNode<T> node){
    if(node == null){
      return null;
    }
    if(numberOfChildren(node) == 0){
      return node;
    }

    BinaryTreeNode returnableNode;
    returnableNode = findRightMostLeaf(node.children[1]);
    if(returnableNode != null){
      return returnableNode;
    }
    returnableNode = findRightMostLeaf(node.children[0]);
    if(returnableNode != null){
      return returnableNode;
    }
    return null;
  }

  private BinaryTreeNode findLeftMostLeaf(BinaryTreeNode<T> node){
    if(node == null){
      return null;
    }
    if(numberOfChildren(node) == 0){
      return node;
    }

    BinaryTreeNode returnableNode;
    returnableNode = findLeftMostLeaf(node.children[0]);
    if(returnableNode != null){
      return returnableNode;
    }
    returnableNode = findLeftMostLeaf(node.children[1]);
    if(returnableNode != null){
      return returnableNode;
    }
    return null;
  }

  private int numberOfChildren(BinaryTreeNode<T> node){
    int count = 0;
    // Have to iterator for all children as left most can be null and right most not null
    for (int index = 0; index < maxChildrenPerNode; index++){
      if(node.children[index] != null){
        count++;
      }
    }
    return count;
  }

  private boolean isVisited(BinaryTreeNode<T> node){
    return visited.contains(node);
  }

  private boolean setVisited(BinaryTreeNode<T> node){
    return visited.add(node);
  }

  private void clearVisited(){
    visited.clear();
  }
}
