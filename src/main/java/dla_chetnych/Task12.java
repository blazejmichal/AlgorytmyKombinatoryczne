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

  private List<Integer> input;

  public Task12(
      int n
  ) {
    this.buildInput(n);
    this.run();
  }

  public void run(
  ) {
    for (int i = 2; i < this.input.size(); i++) {
//      int element = this.input.get(i);
      int element = i + 1;
      if (
          !hasPair(element)
              || !onlyOnePair(element)
      ) {
        this.input.remove(
            this.input.indexOf(element)
        );
      }
    }
  }

  public void buildInput(
      int n
  ) {
    this.input = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
  }

  public Boolean hasPair(
      int element
  ) {
    int n = this.input.indexOf(element);
    for (int i = 0; i < n; i++) {
      for (int j = (i + 1); j < n; j++) {
        if (this.input.get(i) + this.input.get(j) == element) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean onlyOnePair(
      int element
  ) {
    int n = 0;
    for (int i = 0; i < this.input.indexOf(element); i++) {
      for (int j = (i + 1); j < this.input.indexOf(element); j++) {
        if (this.input.get(i) + this.input.get(j) == element) {
          n++;
        }
      }
    }
    return n == 1;
  }

}
