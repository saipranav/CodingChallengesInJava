package Heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sai Pranav on 1/1/2017.
 */

// Min heap
public class Heap<T> {
  private T[] array;
  private int lastIndex;
  private int capacity;
  private int fanOut;
  private Comparator<T> comparator;
  private boolean shouldGrow;

  public Heap (Comparator<T> comparator){
    this.capacity = 10;
    this.fanOut = 2;
    this.lastIndex = -1;
    this.comparator = comparator;
    this.shouldGrow = true;
    this.array = (T[]) new Object[capacity];
  }

  public Heap (Comparator<T> comparator, int fixedCapacity){
    this.capacity = fixedCapacity;
    this.fanOut = 2;
    this.lastIndex = -1;
    this.comparator = comparator;
    this.shouldGrow = false;
    this.array = (T[]) new Object[capacity];
  }

  private void grow(int newCapacity){
    T[] newArray = (T[]) new Object[newCapacity];
    for(int index = 0; index < capacity; index++){
      newArray[index] = array[index];
    }
    array = newArray;
  }

  // O(logn) siftUps the last index which is recently added
  // Returns root data if capacity is full
  public T insert(T data){
    T returnData = null;
    if( lastIndex + 1 == capacity ){
      if(shouldGrow == true){
        grow( (lastIndex + 1) * fanOut);
      } else {
        returnData = extractRoot();
      }
    }
    lastIndex++;
    array[lastIndex] = data;
    siftUp(lastIndex);
    return returnData;
  }

  // O(n) not necessarily sorted
  private int find(T data){
    int compareResult = 0;
    for(int index = 0; index <= lastIndex; index++){
      compareResult = comparator.compare(array[index], data);
      if(compareResult == 0){
        return index;
      }
    }
    return -1;
  }

  // O(1)
  public T get(int index){
    if(lastIndex <= index){
      return array[index];
    }
    return null;
  }

  // O(1)
  public T poolRoot(){
    if(lastIndex == -1){
      return null;
    }
    return array[0];
  }

  // O(logn)
  public T extractRoot(){
    T returnable = poolRoot();
    delete(0);
    return returnable;
  }

  // O(logn)
  public boolean delete(T data){
    int deleteIndex = find(data);
    if(deleteIndex == -1){
      return false;
    }
    return delete(deleteIndex);
  }

  public int size(){
    return lastIndex+1;
  }

  private boolean delete(int deleteIndex){
    swap(deleteIndex, lastIndex);
    array[lastIndex] = null;
    lastIndex--;
    siftDown(deleteIndex);
    return true;
  }

  // O(n)
  public void print(){
    for(int index = 0;index <= lastIndex; index++){
      System.out.print(array[index]+" ");
    }
    System.out.println();
  }

  private void siftUp(int childIndex){
    int parent = ( childIndex - 1 ) / fanOut;
    int compareResult = comparator.compare(array[parent], array[childIndex]);
    while(compareResult > 0){
      swap(childIndex, parent);
      childIndex = parent;
      parent = ( childIndex - 1 ) / fanOut;
      if (parent < 0){
        break;
      }
      compareResult = comparator.compare(array[parent], array[childIndex]);
    }
  }

  private void siftDown(int parentIndex){
    while(parentIndex < lastIndex){
      List<Integer> children = new ArrayList<Integer>();
      for(int index = 0; index < fanOut; index++){
        int childIndex = parentIndex + (parentIndex + 1) + index;
        if(childIndex <= lastIndex){
          children.add(childIndex);
        } else {
          break;
        }
      }
      if(children.isEmpty()){
        break;
      }
      int minimumChildIndex = minimum(children);
      int compareResult = comparator.compare(array[parentIndex], array[minimumChildIndex]);
      if(compareResult > 0){
        swap(parentIndex, minimumChildIndex);
        parentIndex = minimumChildIndex;

      } else {
        break;
      }
    }
  }

  private int minimum(List<Integer> indexArray){
    T minimum = array[indexArray.get(0)];
    int minimumIndex = indexArray.get(0);
    int compareResult;
    for(int index = 1; index < indexArray.size(); index++){
      compareResult = comparator.compare(array[indexArray.get(index)], minimum);
      if(compareResult < 0){
        minimum = array[indexArray.get(index)];
        minimumIndex = indexArray.get(index);
      }
    }
    return minimumIndex;
  }

  private void swap(int firstIndex, int secondIndex){
    T temp =  array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }

}
