package algorithms;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.math3.util.CombinatoricsUtils;

public class Algorithm8NotRecursive {

  public void PERM(
      int m
  ) {
    Integer counter = 0;
    Integer x = 1;
    List<Integer> C = Lists.newArrayList(Integer.MIN_VALUE);
    List<Integer> P = Lists.newArrayList(Integer.MIN_VALUE);
    for (int i = 1; i <= m; i++) {
      C.add(1);
      P.add(i);
    }
    while (counter < CombinatoricsUtils.factorial(m)) {
      if (Objects.equals(C.get(x), x)) {
        if (C.get(x) == 1 && x == 1) {
          counter++;
          System.out.println(P.stream().skip(1).collect(Collectors.toList()));
        }
        for (int i = 1; i <= x; i++) {
          C.set(i, 1);
        }
        x++;
      }
      if (C.get(x) < x) {
        Integer i = C.get(x);
        this.swap(
            this.B(x, i),
            x,
            P
        );
        C.set(x, C.get(x) + 1);
        x = 1;
      }
    }
  }

  public Integer B(
      int m,
      int i
  ) {
    if ((m % 2 == 0) && (m > 2)) {
      if (i < (m - 1)) {
        return i;
      } else {
        return m - 2;
      }
    } else {
      return m - 1;
    }
  }

  public void swap(
      int i,
      int j,
      List<Integer> P
  ) {
    Integer left = P.get(i);
    Integer right = P.get(j);
    P.set(i, right);
    P.set(j, left);
  }

}
