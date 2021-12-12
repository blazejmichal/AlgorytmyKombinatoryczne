package dla_chetnych;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.javatuples.Triplet;

/**
 * 9. Znaleźć największy możliwy zakres opłat pocztowych (opłaty pocztowe przyjmują kolejne wartości
 * 1, 2, 3, .......), przy założeniu, że mamy n rodzajów znaczków pocztowych, a na jednej kopercie
 * może być co najwyżej m znaczków. Zastosować komputerową implementację problemu 5 pkt
 */
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
          Triplet<Boolean, Integer, List<Integer>> subResultReducedInput = tryAlgorithmWithReducedInput(
              goal,
              iterationStamps
          );
          if (subResultReducedInput.getValue0()) {
            iterationValue = subResultReducedInput.getValue1();
            iterationStamps = subResultReducedInput.getValue2();
            continue;
          }
          List<Integer> subStamps = Lists.newArrayList(this.stamps);
          subStamps.remove(0);
          while (!subStamps.isEmpty()) {
            Triplet<Boolean, Integer, List<Integer>> subResult = this.tryAlgorithmWithReducedStampsValues(
                subStamps,
                goal
            );
            if (subResult.getValue0()) {
              iterationValue = subResult.getValue1();
              iterationStamps = subResult.getValue2();
              break;
            }
            subStamps.remove(0);
          }
          if (goal == iterationValue) {
            continue;
          } else {
            return;
          }
        }
        iterationStamps.add(stamp);
        iterationValue += stamp;
        while (iterationValue < goal) {
          if ((iterationValue + stamp) > goal) {
            break;
          }
          if ((iterationStamps.size() + 1) > this.stampAmount) {
            Triplet<Boolean, Integer, List<Integer>> subResultReducedInput = tryAlgorithmWithReducedInput(
                goal,
                iterationStamps
            );
            if (subResultReducedInput.getValue0()) {
              iterationValue = subResultReducedInput.getValue1();
              iterationStamps = subResultReducedInput.getValue2();
              continue;
            }
            List<Integer> subStamps = Lists.newArrayList(this.stamps);
            subStamps.remove(0);
            while (!subStamps.isEmpty()) {
              Triplet<Boolean, Integer, List<Integer>> subResult = this.tryAlgorithmWithReducedStampsValues(
                  subStamps,
                  goal
              );
              if (subResult.getValue0()) {
                iterationValue = subResult.getValue1();
                iterationStamps = subResult.getValue2();
                break;
              }
              subStamps.remove(0);
            }
            if (goal == iterationValue) {
              continue;
            } else {
              return;
            }
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

  public Triplet<Boolean, Integer, List<Integer>> tryAlgorithmWithReducedStampsValues(
      List<Integer> stamps,
      Integer goal
  ) {
    List<Integer> iterationStamps = Lists.newLinkedList();
    Integer iterationValue = 0;
    for (Integer stamp : stamps) {
      if (iterationValue == goal) {
        return new Triplet<>(
            Boolean.TRUE,
            iterationValue,
            iterationStamps
        );
      }
      if (stamp > goal || (iterationValue + stamp) > goal) {
        continue;
      }
      if ((iterationStamps.size() + 1) > this.stampAmount) {
        return new Triplet<>(
            Boolean.FALSE,
            iterationValue,
            iterationStamps
        );
      }
      iterationStamps.add(stamp);
      iterationValue += stamp;
      while (iterationValue < goal) {
        if ((iterationValue + stamp) > goal) {
          break;
        }
        if ((iterationStamps.size() + 1) > this.stampAmount) {
          return new Triplet<>(
              Boolean.FALSE,
              iterationValue,
              iterationStamps
          );
        }
        iterationStamps.add(stamp);
        iterationValue += stamp;
      }
    }
    return new Triplet<>(
        Boolean.TRUE,
        iterationValue,
        iterationStamps
    );
  }

  public Triplet<Boolean, Integer, List<Integer>> tryAlgorithmWithReducedInput(
      Integer goal,
      List<Integer> input
  ) {
    List<Integer> clonedInput = Lists.newArrayList(input);
    List<Integer> stampsToTry = Lists.newArrayList(this.stamps);
    List<Integer> iterationStamps = Lists.newLinkedList();
    for (int i = 0; i < clonedInput.size(); i++) {
      iterationStamps = Lists.newArrayList(clonedInput);
      for (int j = 0; j <= i; j++) {
        iterationStamps.remove(0);
      }
      Integer removedStamp = clonedInput.get(0);
      iterationStamps = iterationStamps
          .stream()
          .filter(stamp -> stamp >= removedStamp)
          .collect(Collectors.toList());
      stampsToTry = stampsToTry
          .stream()
          .filter(stamp -> !Objects.equals(stamp, removedStamp))
          .collect(Collectors.toList());
      Integer iterationValue = iterationStamps.stream().reduce(0, Integer::sum);
      for (Integer stamp : stampsToTry) {
        if (iterationValue == goal) {
          return new Triplet<>(
              iterationStamps.stream().reduce(0, Integer::sum).equals(goal),
              iterationValue,
              iterationStamps
          );
        }
        if (stamp > goal || (iterationValue + stamp) > goal) {
          continue;
        }
        if ((iterationStamps.size() + 1) > this.stampAmount) {
          break;
        }
        iterationStamps.add(stamp);
        iterationValue += stamp;
        while (iterationValue < goal) {
          if ((iterationValue + stamp) > goal) {
            break;
          }
          if ((iterationStamps.size() + 1) > this.stampAmount) {
            break;
          }
          iterationStamps.add(stamp);
          iterationValue += stamp;
        }
      }
    }
    return new Triplet<>(
        iterationStamps.stream().reduce(0, Integer::sum).equals(goal),
        iterationStamps.stream().reduce(0, Integer::sum),
        iterationStamps
    );
  }

}