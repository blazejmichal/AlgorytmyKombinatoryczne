package dla_chetnych;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

public class Task9 {

  private List<Integer> stamps;
  private Integer stampAmount;
  private List<Integer> resultStamps = Lists.newLinkedList();
  private Integer resultValue;

  public Task9(
      List<Integer> stamps,
      Integer stampAmount
  ) {
    this.stamps = stamps;
    Collections.sort(this.stamps, Collections.reverseOrder());
    this.stampAmount = stampAmount;
    this.run();
  }

  public void run(
  ) {
    List<Integer> iterationStamps = Lists.newLinkedList();
    Integer iterationValue = 0;
    for (int goal = 1; goal <= Integer.MAX_VALUE; goal++) {
      for (Integer stamp : this.stamps) {
        if (iterationValue == goal) {
          break;
        }
        if (stamp > goal || (iterationValue + stamp) > goal) {
          continue;
        }
        if ((iterationStamps.size() + 1) > this.stampAmount) {
          return;
        }
        iterationStamps.add(stamp);
        iterationValue += stamp;
        while (iterationValue < goal) {
          if ((iterationValue + stamp) > goal) {
            break;
          }
          if ((iterationStamps.size() + 1) > this.stampAmount) {
            return;
          }
          iterationStamps.add(stamp);
          iterationValue += stamp;
        }
      }
      this.resultStamps = Lists.newArrayList(iterationStamps);
      this.resultValue = iterationValue;
      iterationStamps.clear();
      iterationValue = 0;
    }
  }
}

//  public List<Integer> minimumStamp(
//      Set<Integer> stamps,
//      Integer totalStampAmount
//  ) {
//    List<Integer> stampList = Lists.newArrayList();
//    int sumOfStamps = 0;
//    int remainingStampAmount = 0;
//    for (int currentStampAmount : stamps) {
//      remainingStampAmount = totalStampAmount - sumOfStamps;
//      if (remainingStampAmount % currentStampAmount == 0) {
//        int howMany = remainingStampAmount / currentStampAmount;
//        while (howMany > 0) {
//          stampList.add(currentStampAmount);
//          howMany--;
//        }
//        break;
//      } else if (totalStampAmount == (sumOfStamps + currentStampAmount)) {
//        stampList.add(currentStampAmount);
//        break;
//      } else if (totalStampAmount > (sumOfStamps + currentStampAmount)) {
//        int howMany = remainingStampAmount / currentStampAmount;
//        if (howMany > 0) {
//          while (howMany > 0) {
//            stampList.add(currentStampAmount);
//            sumOfStamps += currentStampAmount;
//            howMany--;
//          }
//        } else {
//          stampList.add(currentStampAmount);
//          sumOfStamps += currentStampAmount;
//        }
//      }
//    }
//    return stampList;
//  }
//}