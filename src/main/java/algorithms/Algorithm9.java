package algorithms;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Algorithm9 {

  public List<List<Integer>> run(List<Integer> input) {

    List<List<Integer>> results = Lists.newLinkedList();
    results.add(input);
    for (int sourceIndex = 0; sourceIndex < input.size(); sourceIndex++) {
      for (int targetIndex = 0; targetIndex < input.size(); targetIndex++) {
        if (sourceIndex != targetIndex) {
          List<Integer> iterationResult = Lists.newLinkedList();
          int indexToTake = 0;
          for (int indexToPut = 0; indexToPut < input.size(); indexToPut++) {
            if (indexToTake == sourceIndex && ((indexToTake + 1) < input.size())) {
              indexToTake = indexToTake + 1;
            }
            if (indexToPut == targetIndex) {
              iterationResult.add(
                  input.get(sourceIndex)
              );
              continue;
            } else {
              iterationResult.add(
                  input.get(indexToTake)
              );
            }
            indexToTake++;
          }
          results.add(iterationResult);
        }
      }
    }

    return results;
  }

  public void runVersion2(Integer n) {

    List<Integer> P = Lists.newArrayList(Integer.MIN_VALUE);
    List<Integer> C = Lists.newArrayList(Integer.MIN_VALUE);
    List<Boolean> PR = Lists.newArrayList(Boolean.TRUE);
    for (int i = 1; i <= n; i++) {
      P.add(i);
      C.add(1);
      PR.add(Boolean.TRUE);
    }
    C.set(n, 0);
    System.out.println(P.stream().skip(1).collect(Collectors.toList()));
    Integer i = 1;
    while (i < n) {
      i = 1;
      Integer x = 0;
      while (C.get(i) == (n - i + 1)) {
        PR.set(i, !PR.get(i));
        C.set(i, 1);
        if (PR.get(i)) {
          x = x + 1;
        }
        i = i + 1;
      }
      if (i < x) {
        Integer k;
        if (PR.get(i)) {
          k = C.get(i) + x;
        } else {
          k = n - i + 1 - C.get(i) + x;
        }
        Integer left = P.get(k);
        Integer right = P.get(k + 1);
        P.set(k, right);
        P.set(k + 1, left);
        System.out.println(P.stream().skip(1).collect(Collectors.toList()));
        C.set(i, C.get(i) + 1);
      }
    }
  }

  public void runVersion3(Integer m) {

    Set<List<Integer>> results = Sets.newLinkedHashSet();
    List<Integer> P = Lists.newArrayList(Integer.MIN_VALUE);
    for (int i = 1; i <= m; i++) {
      P.add(i);
    }
    Integer left;
    Integer right;
    results.add(P.stream().skip(1).collect(Collectors.toList()));
    for (int i = 1; i < m; i++) {
      left = P.get(m - 1);
      right = P.get(m);
      P.set(m - 1, right);
      P.set(m, left);
      results.add(P.stream().skip(1).collect(Collectors.toList()));
      for (int k = 1; k < m; k++) {
        left = P.get(k);
        right = P.get(k + 1);
        P.set(k, right);
        P.set(k + 1, left);
        results.add(P.stream().skip(1).collect(Collectors.toList()));
      }
      left = P.get(1);
      right = P.get(2);
      P.set(1, right);
      P.set(2, left);
      results.add(P.stream().skip(1).collect(Collectors.toList()));
      for (int l = m; l > 1; l--) {
        left = P.get(l);
        right = P.get(l - 1);
        P.set(l, right);
        P.set(l - 1, left);
        results.add(P.stream().skip(1).collect(Collectors.toList()));
      }
    }
    results.forEach(System.out::println);
  }

}