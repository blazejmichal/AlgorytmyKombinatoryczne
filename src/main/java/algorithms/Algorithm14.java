package algorithms;

import com.google.common.collect.Lists;
import java.util.List;

public class Algorithm14 {

  public void run(
      int n
  ) {
    List<Integer> S =
        Lists.newArrayList(null, null, null, null, null, null, null, null, null, null, null);
    List<Integer> R =
        Lists.newArrayList(null, null, null, null, null, null, null, null, null, null, null);
    S.set(1, n);
    R.set(1, 1);
    int d = 1;
    System.out.println("S: " + S + " and R: " + R);
    while (S.get(1) > 1) {
      int sum = 0;
      if (S.get(d) == 1) {
        sum = sum + R.get(d);
        d = d - 1;
      }
      sum = sum + S.get(d);
      R.set(d, R.get(d) - 1);
      int l = S.get(d) - 1;
      if (R.get(d) > 0) {
        d = d + 1;
      }
      S.set(d, l);
      R.set(d, sum / l);
      l = sum % l;
      if (l != 0) {
        d = d + 1;
        S.set(d, l);
        R.set(d, 1);
      }
      System.out.println("S: " + S + " and R: " + R);
    }
  }

}
