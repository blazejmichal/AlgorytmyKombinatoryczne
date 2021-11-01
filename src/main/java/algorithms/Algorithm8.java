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
public class Algorithm8 {

  private List<Integer> P = Lists.newArrayList();

  public Integer B(Integer m, Integer i) {

    Integer B;
    if ((m % 2 == 0) && (m > 2)) {
      if (i < m - 1) {
        B = i;
      } else {
        B = m - 2;
      }
    } else {
      B = m - 1;
    }
    return B;
  }

  public void PERM(Integer m) {

    if (m == 1) {
      System.out.println(P.stream().skip(1).collect(Collectors.toList()));
    } else {
      for (int i = 1; i <= m; i++) {
        this.PERM(m - 1);
        if (i < m) {
          Integer bResult = this.B(m, i);
          Integer left = P.get(m);
          Integer right = P.get(bResult);
          P.set(m, right);
          P.set(bResult, left);
        }
      }
    }
  }

}
