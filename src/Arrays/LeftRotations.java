package Arrays;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sai Pranav on 11/18/2016.
 */
public class LeftRotations {
  public static void main(String args[]){
    int n, r;
    List<Integer> array = new ArrayList<Integer>();
    List<Integer> temp = new ArrayList<Integer>();
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    r = in.nextInt();
    for (int index = 0; index < n; index++){
      array.add(in.nextInt());
    }
    for(int i=0;i<r;i++){
      temp.add(array.get(i));
    }
    for(int i=0;i<n-r;i++){
      array.set(i,array.get(i+r));
    }
    int j=0;
    for(int i=n-r;i<n;i++){
      array.set(i,temp.get(j++));
    }
    System.out.println(array);
  }
}
