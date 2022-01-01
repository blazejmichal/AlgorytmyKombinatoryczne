package dla_chetnych;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 12. Zaimplementuj dwa równolegle działające sita obliczające elementy ciągu U = {1, 2, 3, 4, 6,
 * 8, 11, 13, 16, 18, ...}. Na początku mamy ciąg U={1, 2}. Kolejne wyrazy ciągu spełniają warunek,
 * że są sumą dokładnie jednej pary rożnych elementów od niego mniejszych. Pierwsze sito eliminuje
 * liczby, które nie są sumami dwóch rożnych elementów, które wystąpiły wcześniej. Drugie zaś sito
 * eliminuje liczby, dla których istnieje więcej niż jedna para takich liczb. Ważne: Muszą być dwa
 * sita. 3 pkt, termin 8.01
 */
public class Task12 {

  public void run(
      int n
  ) {
    List<Integer> list = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
    for (int i = 2; i < n; i++) {
      if (!hasPair(list, i + 1)) {
        list.remove(list.indexOf(i + 1));
      } else if (!hasOnlyOnePair(list, i + 1)) {
        list.remove(list.indexOf(i + 1));
      }
    }
    System.out.println(list);
  }

  private boolean hasPair(
      List<Integer> list,
      int element
  ) {
    int n = list.indexOf(element);
    for (int i = 0; i < n; i++) {
      for (int j = (i + 1); j < n; j++) {
        if (list.get(i) + list.get(j) == element) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean hasOnlyOnePair(
      List<Integer> list,
      int element
  ) {
    int n = 0;
    for (int i = 0; i < list.indexOf(element); i++) {
      for (int j = (i + 1); j < list.indexOf(element); j++) {
        if (list.get(i) + list.get(j) == element) {
          n++;
        }
      }
    }
    return n == 1;
  }

}
