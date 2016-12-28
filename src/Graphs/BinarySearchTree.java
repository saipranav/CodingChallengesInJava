package Graphs;

import java.util.*;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class BinarySearchTree<T> {
  class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T>[] children;
    BinaryTreeNode<T> parent;

    BinaryTreeNode(T data){
      this.data = data;
      this.children = new BinaryTreeNode[2];
      this.parent = null;
    }
  }

  private BinaryTreeNode<T> root;
  private Set<BinaryTreeNode<T>> visited;
  private int maxChildrenPerNode = 2;
  private Comparator<T> comparator;

  public BinarySearchTree(Comparator<T> comparator){
    this.root = null;
    this.visited = new HashSet<BinaryTreeNode<T>>();
    this.comparator = comparator;
  }

  public void insert(T data){
    BinaryTreeNode<T> newParent = findBestPlaceToInsert(root, data);
    BinaryTreeNode<T> newChild = new BinaryTreeNode(data);

    if(newParent == null && root == null){
      root = newChild;
    } else {
      int compareResult = comparator.compare(data, newParent.data);
      if(compareResult >= 0){
        newParent.children[1] = newChild;
      } else {
        newParent.children[0] =newChild;
      }
      newChild.parent = newParent;
    }
  }

  public Boolean contains(T data){
    if(root == null){
      return false;
    }

    BinaryTreeNode returnableNode = findNode(root, data);
    if(returnableNode != null){
      return true;
    }
    return false;
  }

  public void delete(T data) throws NodeNotFoundException{
    BinaryTreeNode toBeDeleted = findNode(root, data);
    if(toBeDeleted == null){
      throw new NodeNotFoundException("Node to be deleted is not found");
    }

    if(numberOfChildren(toBeDeleted) == 0){
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
      BinaryTreeNode swappableLeaf = findBestLeafToSwap(toBeDeleted);
      if(swappableLeaf == null){
        if(root == null){
          // This must not happen as we have something to delete but nothing from root
          return;
        }
        swappableLeaf = root;
      }
      toBeDeleted.data = swappableLeaf.data;
      for(int index = 0; index < maxChildrenPerNode; index++){
        if( swappableLeaf.parent.children[index] == swappableLeaf){
          swappableLeaf.parent.children[index] = null;
        }
      }
      swappableLeaf.data = null;
      swappableLeaf.children = null;
      swappableLeaf.parent = null;

      BinaryTreeNode swappedNode = toBeDeleted;

      satisfyBinarySearchTree(swappedNode);
    }
  }

  public void traverse(Traversal traversalType){
    switch (traversalType){
      case INORDER:
        inOrderTraversal(root);
        System.out.println();
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

  private BinaryTreeNode findBestPlaceToInsert(BinaryTreeNode<T> node ,T data){
    if(node == null){
      return null;
    }

    int compareResult = comparator.compare(data, node.data);
    BinaryTreeNode returnableNode = null;

    if(compareResult >= 0){
      if(node.children[1] == null){
        return node;
      } else{
        returnableNode = findBestPlaceToInsert(node.children[1], data);
        if (returnableNode != null) {
          return returnableNode;
        }
      }
    } else {
      if(node.children[0] == null){
        return node;
      } else {
        returnableNode = findBestPlaceToInsert(node.children[0], data);
        if(returnableNode != null){
          return returnableNode;
        }
      }
    }
    return null;
  }

  private void inOrderTraversal(BinaryTreeNode<T> node){
    if(node == null){
      return;
    }

    inOrderTraversal(node.children[0]);
    System.out.print(" [ "+node.data.toString()+" ] ");
    inOrderTraversal(node.children[1]);
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

  private BinaryTreeNode findNode(BinaryTreeNode<T> node, T key){
    if(node == null){
      return null;
    }

    int compareResult = comparator.compare(key, node.data);
    BinaryTreeNode returnableNode = null;

    if(compareResult > 0){
      returnableNode = findNode(node.children[1], key);
    } else if(compareResult < 0){
      returnableNode = findNode(node.children[0], key);
    } else {
      return node;
    }
    return returnableNode;
  }

  private void satisfyBinarySearchTree(BinaryTreeNode<T> node){
    BinaryTreeNode<T> currentNode = node;
    while(currentNode != null){
      if(currentNode.children[0] != null){
        int compareResult = comparator.compare(currentNode.data, currentNode.children[0].data);
        if(compareResult >= 0){
          // no problem
        } else {
          T temp = currentNode.data;
          currentNode.data = currentNode.children[0].data;
          currentNode.children[0].data = temp;
          currentNode = currentNode.children[0];
          // continue to skip next if for checking right node
          continue;
        }
      }
      if(currentNode.children[1] != null){
        int compareResult = comparator.compare(currentNode.data, currentNode.children[1].data);
        if(compareResult >= 0){
          T temp = currentNode.data;
          currentNode.data = currentNode.children[0].data;
          currentNode.children[0].data = temp;
          currentNode = currentNode.children[0];
        } else {
          // no problem
          currentNode = null;
        }
      }
    }
  }

  private BinaryTreeNode findBestLeafToSwap(BinaryTreeNode<T> node){
    if(node == null){
      return null;
    }
    BinaryTreeNode returnableNode;
    returnableNode = findLeftMostLeaf(node.children[1]);
    if(returnableNode != null){
      return returnableNode;
    }
    returnableNode = findRightMostLeaf(node.children[0]);
    if(returnableNode != null){
      return returnableNode;
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
