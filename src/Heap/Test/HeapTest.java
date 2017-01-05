package Heap.Test;

import Trees.Test.IntegerComparator;
import Heap.Heap;

/**
 * Created by Sai Pranav on 1/1/2017.
 */
public class HeapTest {
  public static void main(String[] args){
    Heap<Integer> heap = new Heap<Integer>(new IntegerComparator());
    heap.insert(5);
    heap.insert(2);
    heap.insert(8);
    heap.insert(6);
    heap.insert(0);
    heap.insert(9);
    heap.insert(10);
    heap.insert(60);
    heap.insert(3);
    heap.print();

    heap.delete(5);
    heap.delete(20);
    heap.extractRoot();
    heap.print();
  }
}
