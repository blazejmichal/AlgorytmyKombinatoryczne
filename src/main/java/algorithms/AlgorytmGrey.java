package algorithms;

import com.google.common.collect.Lists;
import java.util.List;

public class AlgorytmGrey {

  public void run() {
    List<Integer> A = Lists.newArrayList(0, 1, 2, 3, 4);
    Integer k = 4;
    Integer n = 6;
    Integer p = k;
    while (p >= 1) {
      System.out.println(A);
      Integer left = A.get(k);
      Integer right = n;
      if (A.get(k).equals(n)) {
        p = p - 1;
      } else {
        p = k;
      }
      if (p >= 1) {
        for (int i = k; i >= p; i--) {
          Integer get = A.get(p);
          A.set(i, A.get(p) + i - p + 1);
        }
      }
    }
  }

}
