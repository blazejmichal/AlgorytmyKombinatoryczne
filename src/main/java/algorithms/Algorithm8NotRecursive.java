package algorithms;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Algorithm8NotRecursive {

  private List<Integer> P = Lists.newArrayList();
  private Integer count = 0;

  public Algorithm8NotRecursive(List<Integer> p) {
    P = p;
  }

  public Integer silnia(Integer n) {

    Integer result = (n == 1 || n == 0) ? 1 : silnia(n - 1) * n;

    return result;
  }

  public void swap(int i, int j) {

    Integer left = this.P.get(i);
    Integer right = this.P.get(j);
    this.P.set(i, right);
    this.P.set(j, left);
  }

  public Integer B(int m, int i) {

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

  public void PERM(int m) {

    List<Integer> I = Lists.newArrayList(Integer.MIN_VALUE);
    for (int i = 1; i <= m; i++) {
      I.add(1);
    }
    Integer Mi = 1;
    while (this.count < silnia(m)) {
      if (I.get(Mi) == Mi) {
        if (I.get(Mi) == 1 && Mi == 1) {
          count++;
          System.out.println(String.format("count: ", this.count));
          System.out.println(P.stream().skip(1).collect(Collectors.toList()));
        }
        for (int i = 1; i <= Mi; i++) {
          I.set(i, 1);
        }
        Mi++;
      }
      if (I.get(Mi) < Mi) {
        Integer i = I.get(Mi);
        this.swap(B(Mi, i), Mi);
        I.set(Mi, I.get(Mi) + 1);
        Mi = 1;
      }
    }
  }

}
