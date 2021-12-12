package dla_chetnych;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;

public class Task9BruteForce {

  private List<Integer> stamps;
  private Integer stampAmount;
  private List<List<Integer>> combinations = Lists.newLinkedList();
  private Integer resultValue;
  private List<Integer> resultStamps;

  public Task9BruteForce(
      List<Integer> stamps,
      Integer stampAmount
  ) {
    this.stamps = stamps;
    this.stampAmount = stampAmount;
    this.run();
  }

  public void combinationUtil(
      List<Integer> arr,
      Integer[] data,
      int start,
      int end,
      int index,
      int r
  ) {
    if (index == r) {
      this.combinations.add(
          Arrays.asList(data)
      );
      return;
    }
    for (int i = start; i <= end; i++) {
      data[index] = arr.get(i);
      this.combinations.add(
          Arrays.asList(data)
      );
      combinationUtil(arr, data, i + 1, end, index + 1, r);
    }
  }

  public void run(
  ) {
    Integer[] data = new Integer[this.stampAmount];
    List<Integer> clonedStamps = Lists.newArrayList(this.stamps);
    for (int i = 0; i < this.stampAmount - 1; i++) {
      this.stamps.addAll(clonedStamps);
    }
    for (int goal = 1; goal < Integer.MAX_VALUE; goal++) {
      for (int i = 0; i < this.stamps.size() - 1; i++) {
        this.combinationUtil(
            this.stamps,
            data,
            0,
            i,
            0,
            this.stampAmount
        );
      }
      for (List<Integer> combination : this.combinations) {
        Integer sum = combination.stream().reduce(0, Integer::sum);
        if (sum.equals(goal)) {
          this.resultValue = sum;
          this.resultStamps = combination;
          break;
        }
      }
      if (this.resultValue == null || this.resultValue != goal) {
        break;
      }
    }
  }

}
