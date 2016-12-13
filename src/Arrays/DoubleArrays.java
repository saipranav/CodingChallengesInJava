package Arrays;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Sai Pranav on 11/17/2016.
 */
public class DoubleArrays {
  public static void main(String args[]) {
    int n, q, selectedIndex, seq;
    String query;
      int lastAns = 0;
    List<List<Integer>> seqList = new ArrayList<List<Integer>>();
    Scanner in = new Scanner(System.in);
    n = in.nextInt();
    for (long index = 0; index < n; index++) {
      seqList.add(new ArrayList<Integer>());
    }
    q = in.nextInt();
    in.nextLine();
    for (long queryIndex = 0; queryIndex < q; queryIndex++) {
      query = in.nextLine();
      String[] queryArray = query.split(" ");
      if(queryArray[0].equals("1")){
        seq = ( ( Integer.parseInt(queryArray[1]) ^ lastAns ) % n );
        seqList.get(seq).add(Integer.parseInt(queryArray[2]));
      } else {
        seq = ( ( Integer.parseInt(queryArray[1]) ^ lastAns ) % n );
        selectedIndex = ( Integer.parseInt(queryArray[2]) % seqList.get(seq).size() );
        lastAns = seqList.get(seq).get(selectedIndex);
        System.out.println(lastAns);
      }
    }
  }
}
