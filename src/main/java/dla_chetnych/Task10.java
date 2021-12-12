package dla_chetnych;

import lombok.Data;

/**
 * 10. Skonstruować program wykorzystujący metodę powrotów do rozwiązania problemu hetmanów na
 * szachownicy. Program powinien znaleźć wszystkie rozwiązania dla szachownicy o zadanej wielkości.
 * 4 pkt Termin - 18.12
 */
@Data
public class Task10 {

  private int boardSize = 4;

  void printSolution(
      char board[][]
  ) {
    for (int i = 0; i < this.boardSize; i++) {
      for (int j = 0; j < this.boardSize; j++) {
        System.out.print(" " + board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /* A utility function to check if a queen can
     be placed on board[row][col]. Note that this
     function is called when "col" queens are already
     placeed in columns from 0 to col -1. So we need
     to check only left side for attacking queens */
  boolean isSafe(
      char board[][],
      int row,
      int col
  ) {
    int i, j;
    /* Check this row on left side */
    for (i = 0; i < col; i++) {
      if (board[row][i] == 'H') {
        return false;
      }
    }
    /* Check upper diagonal on left side */
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'H') {
        return false;
      }
    }
    /* Check lower diagonal on left side */
    for (i = row, j = col; j >= 0 && i < this.boardSize; i++, j--) {
      if (board[i][j] == 'H') {
        return false;
      }
    }
    return true;
  }

  /* A recursive utility function to solve N
     Queen problem */
  boolean solveNQUtil(
      char board[][],
      int col
  ) {
        /* base case: If all queens are placed
           then return true */
    if (col >= this.boardSize) {
      return true;
    }
        /* Consider this column and try placing
           this queen in all rows one by one */
    for (int i = 0; i < this.boardSize; i++) {
            /* Check if the queen can be placed on
               board[i][col] */
      if (isSafe(board, i, col)) {
        /* Place this queen in board[i][col] */
        board[i][col] = 'H';
        /* recur to place rest of the queens */
        if (solveNQUtil(board, col + 1) == true) {
          return true;
        }
                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
        board[i][col] = 'x'; // BACKTRACK
      }
    }
        /* If the queen can not be placed in any row in
           this colum col, then return false */
    return false;
  }

  /* This function solves the N Queen problem using
     Backtracking.  It mainly uses solveNQUtil () to
     solve the problem. It returns false if queens
     cannot be placed, otherwise, return true and
     prints placement of queens in the form of 1s.
     Please note that there may be more than one
     solutions, this function prints one of the
     feasible solutions.*/
  public boolean solveNQ(
  ) {
    char board[][] = {
        {'x', 'x', 'x', 'x'},
        {'x', 'x', 'x', 'x'},
        {'x', 'x', 'x', 'x'},
        {'x', 'x', 'x', 'x'}
    };
    if (solveNQUtil(board, 0) == false) {
      System.out.print("Brak rozwiazania");
      return false;
    }
    printSolution(board);
    return true;
  }
}
