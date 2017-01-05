package Heap;

import java.util.*;

/**
 * Created by Sai Pranav on 1/2/2017.
 */
public class FindKMin {

  public static void main(String[] args){
    List<Vote> data = generateData();
    String[] results = calculateTopKAtT(data, 5, 3);
    for(int index = 0; index < 3; index++){
      System.out.println(results[index]);
    }
  }

  private static String[] calculateTopKAtT(List<Vote> votes, int t, int k){
    if(k < 0){
      return null;
    }
    Map<String, Integer> result = new HashMap<String, Integer>();
    for(Vote vote: votes){
      if(vote.time <= t){
        if(result.containsKey(vote.candidate)){
          result.put(vote.candidate, result.get(vote.candidate)+1);
        } else {
          result.put(vote.candidate, 1);
        }
      }
    }
    Set<Map.Entry<String, Integer>> entries = result.entrySet();
    Comparator comparator = new VoteResultComparator();
    Heap<VoteResult> minHeap = new Heap<VoteResult>(comparator, k);

    for(Map.Entry<String, Integer> entry: entries){
      VoteResult voteResult = new VoteResult(entry.getKey(), entry.getValue());
      VoteResult minimumOfMaximum = minHeap.poolRoot();
      if(minimumOfMaximum == null){
        minHeap.insert(voteResult);
      } else {
        if(minHeap.size() < k){
          minHeap.insert(voteResult);
        } else {
          int compareResult = comparator.compare(minimumOfMaximum, voteResult);
          if(compareResult < 0){
            minHeap.insert(voteResult);
          }
        }

      }
    }

    String[] returnable = new String[k];
    for (int index = k-1; index >= 0; index--){
      returnable[index] = minHeap.extractRoot().candidate;
    }
    return returnable;
  }

  private static List<Vote> generateData(){
    List<Vote> returnList = new ArrayList<Vote>();
    returnList.add(new Vote("a", 1));
    returnList.add(new Vote("b", 1));
    returnList.add(new Vote("c", 2));
    returnList.add(new Vote("d", 4));
    returnList.add(new Vote("a", 5));
    returnList.add(new Vote("d", 5));
    returnList.add(new Vote("c", 6));
    returnList.add(new Vote("b", 6));
    returnList.add(new Vote("c", 9));
    returnList.add(new Vote("b", 8));
    returnList.add(new Vote("a", 8));
    return returnList;
  }
}

class Vote{
  public String candidate;
  public int time;

  public Vote(String candidate, int time){
    this.candidate = candidate;
    this.time = time;
  }
}

class VoteResult{
  public String candidate;
  public int votes;

  public VoteResult(String candidate, int votes){
    this.candidate = candidate;
    this.votes = votes;
  }

  public boolean equals(Object o){
    VoteResult b = (VoteResult) o;
    if(this.candidate.equals(b.candidate) && this.votes == b.votes){
      return true;
    }
    return false;
  }
}

class VoteResultComparator implements Comparator<VoteResult>{
  public int compare(VoteResult a, VoteResult b){
    /*if(a.votes > b.votes){
      return 1;
    } else if (a.votes < b.votes){
      return -1;
    }
    else {
      return 0;
    }*/
    return a.votes - b.votes;
  }
}
