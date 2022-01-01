package dla_chetnych;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/**
 * 10. Skonstruować program wykorzystujący metodę powrotów do rozwiązania problemu hetmanów na
 * szachownicy. Program powinien znaleźć wszystkie rozwiązania dla szachownicy o zadanej wielkości.
 * 4 pkt Termin - 18.12
 */
@Data
public class Task10 {

  private Integer size = 4;
  private boolean[] columns = new boolean[8 * this.size];
  private boolean[] leftDiagonal = new boolean[200 * this.size];
  private boolean[] rightDiagonal = new boolean[200 * this.size];
  private List<List<Integer>> results = Lists.newArrayList();

  public Task10(
      Integer size
  ) {
    this.size = size;
    // true jesli jest hetman
    boolean[] columns = new boolean[this.size];
    // true jesli jest hetman
    // indeks to kolumna + wiersz
    boolean[] leftDiagonal = new boolean[2 * this.size];
    // true jesli jest hetman
    // indeks to wiersz - kolumna + rozmiar tablicy
    boolean[] rightDiagonal = new boolean[2 * this.size];
    this.run();
  }

  public void run() {
    List<Integer> initializedCombination = this.initializeCombination();
    this.resolveProblem(this.results, 0, initializedCombination);
    this.printResults();
  }

  public List<Integer> initializeCombination(
  ) {
    List<Integer> initializedCombination = Lists.newArrayList();
    for (int i = 0; i < this.size; i++) {
      initializedCombination.add(0);
    }
    return initializedCombination;
  }

  public void resolveProblem(
      List<List<Integer>> argumentResults,
      int rowIndex,
      List<Integer> combination
  ) {
    // Gdy juz wszyscy hetmani sa rozlozeni na planszy
    if (rowIndex == this.size) {
      argumentResults.add(Lists.newArrayList(combination));
      return;
    }
    for (int col = 0; col < this.size; col++) {
      // Sytuacja gdy nie mozemy postawic hetmana, bo bilby sie z innym'
      if (
          this.columns[col]
              || this.leftDiagonal[rowIndex + col]
              || this.rightDiagonal[rowIndex - col + this.size]
      ) {
        continue;
      }
      // sytuacja gdy stawiamy hetmana
      // oznaczamy ze kolumna jest zajeta
      this.columns[col] = true;
      // oznaczamy ze przekatna jest zajeta
      this.leftDiagonal[rowIndex + col] = true;
      // oznaczamy ze przekatna jest zajeta
      this.rightDiagonal[rowIndex - col + this.size] = true;
      combination.set(col, rowIndex + 1);
      // rozwiazujemy nastepny wiersz
      this.resolveProblem(argumentResults, rowIndex + 1, combination);
      // moze sie zdazyc ze continue przejdzie przez caly for i metoda powrotow sie uruchomi
      this.columns[col] = false;
      this.leftDiagonal[rowIndex + col] = false;
      this.rightDiagonal[rowIndex - col + this.size] = false;
    }
  }

  public void printResults() {
    for (List<Integer> result : this.results) {
      this.printResult(result);
    }
  }

  public void printResult(
      List<Integer> queensPositions
  ) {
    List<List<Character>> board = this.buildEmptyBoard();
    for (int i = 0; i < this.size; i++) {
      List<Character> row = board.get(i);
      Integer position = queensPositions.get(i) - 1;
      row.set(position, 'H');
    }
    board.forEach(System.out::println);
    System.out.println();
  }

  public List<List<Character>> buildEmptyBoard(
  ) {
    List<List<Character>> board = Lists.newLinkedList();
    for (int i = 0; i < this.size; i++) {
      List<Character> row = Lists.newLinkedList();
      for (int j = 0; j < this.size; j++) {
        row.add('x');
      }
      board.add(row);
    }
    return board;
  }

}
