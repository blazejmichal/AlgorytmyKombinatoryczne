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

  private List<List<Integer>> results = Lists.newArrayList();
  private boolean[] columns;
  private boolean[] leftDiagonal;
  private boolean[] rightDiagonal;
  private Integer size = 4;

  // Driver code
  public void run() {
    this.resolveProblem();
    this.printResults();
  }

  /* This function solves the N Queen problem using
  Backtracking. It mainly uses solveNQUtil() to
  solve the problem.
  */
  public List<List<Integer>> resolveProblem(
  ) {
    // cols[i] = true if there is a queen previously placed at ith column
    this.columns = new boolean[this.size];
    // leftDiagonal[i] = true if there is a queen previously placed at
    // i = (row + col )th left diagonal
    this.leftDiagonal = new boolean[2 * this.size];
    // rightDiagonal[i] = true if there is a queen previously placed at
    // i = (row - col + n - 1)th rightDiagonal diagonal
    this.rightDiagonal = new boolean[2 * this.size];
    this.results = Lists.newArrayList();
    List<Integer> temporary = Lists.newArrayList();
    for (int i = 0; i < this.size; i++) {
      temporary.add(0);
    }
    this.findSolution(this.results, 0, temporary);

    return results;
  }

  public void findSolution(List<List<Integer>> result, int row, List<Integer> combination) {
    if (row == this.size) {
      // if row==n it means we have successfully placed all n queens.
      // hence add current arrangement to our answer
      // comb represent current combination
      result.add(Lists.newArrayList(combination));
      return;
    }
    for (int col = 0; col < this.size; col++) {
      // if we have a queen previously placed in the current column
      // or in current left or right diagonal we continue
      if (
          this.columns[col]
              || this.leftDiagonal[row + col]
              || this.rightDiagonal[row - col + this.size]
      ) {
        continue;
      }
      // otherwise we place a queen at cell[row][col] and
      //make current column, left diagonal and righ diagonal true
      this.columns[col] = true;
      this.leftDiagonal[row + col] = true;
      this.rightDiagonal[row - col + this.size] = true;
      combination.set(col, row + 1);
      // then we goto next row
      this.findSolution(result, row + 1, combination);
      // then we backtrack and remove our currently placed queen
      this.columns[col] = false;
      this.leftDiagonal[row + col] = false;
      this.rightDiagonal[row - col + this.size] = false;
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
