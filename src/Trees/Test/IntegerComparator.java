package Trees.Test;

import java.util.Comparator;

/**
 * Created by Sai Pranav on 12/27/2016.
 */
public class IntegerComparator implements Comparator<Integer> {
  @Override
  public int compare(Integer o1, Integer o2) {
    if(o1 > o2){
      return 1;
    } else if (o1 < o2){
      return -1;
    }
    return 0;
  }
}
