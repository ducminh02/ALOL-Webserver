package Solver.ALOL_Webapp.service;

import Solver.ALOL_Webapp.model.Board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class BoardLogik {
    private final Board board;

    public BoardLogik(Board board) {
        this.board = board;
    }
    // Hilffunktionen

    public char[] getColumn(char[][] arr, int index) {
        char[] column = new char[arr[0].length];
        for (int i = 0; i < column.length; i++) {
            column[i] = arr[i][index];
        }
        return column;
    }

    // check if there are 3 0's or 1's found on the board
    public boolean isContains3ConsecutiveNumbersInARow(Board boardd) {
        if (board.getN() >= 3) {
            for (int i = 0; i < board.getN(); i++) {
                for (int j = 0; j < board.getN() - 2; j++) {
                    if (boardd.getTheboard()[i][j] == boardd.getTheboard()[i][j + 1] &&
                            boardd.getTheboard()[i][j + 1] == boardd.getTheboard()[i][j + 2]
                            && boardd.getTheboard()[i][j] != 'x') {
                        return false;
                    }
                }
            }

            for (int i = 0; i < board.getN() - 2; i++) {
                for (int j = 0; j < board.getN(); j++) {
                    if (boardd.getTheboard()[i][j] == boardd.getTheboard()[i + 1][j] &&
                            boardd.getTheboard()[i + 1][j] == boardd.getTheboard()[i + 2][j]
                            && boardd.getTheboard()[i][j] != 'x') {
                        return false;

                    }
                }
            }
        }
        return true;
    }

    // check if there are the same number of 1's and 0's on the board
    public boolean isContainsEqualAmountOf1And0(Board boardd) {
        for (int i = 0; i < board.getN(); i++) {
            int count0 = 0;
            int count1 = 0;

            for (int j = 0; j < board.getN(); j++) {
                if (boardd.getTheboard()[i][j] == '0') {
                    count0++;
                }

                if (boardd.getTheboard()[i][j] == '1') {
                    count1++;
                }
            }
            if (count0 > board.getN() / 2 || count1 > board.getN() / 2) {
                return false;
            }
        }

        for (int i = 0; i < board.getN(); i++) {
            int count0 = 0;
            int count1 = 0;

            for (int j = 0; j < board.getN(); j++) {
                if (boardd.getTheboard()[j][i] == '0') {
                    count0++;
                }

                if (boardd.getTheboard()[j][i] == '1') {
                    count1++;
                }
            }
            if (count0 > board.getN() / 2 || count1 > board.getN() / 2) {
                return false;
            }
        }
        return true;
    }

    // Check for duplicate Rows or Column
    public boolean isContainsDuplicateRowAndColumn(Board boardd) {
        Map<String, Integer> checker = new HashMap<>();
        for (char[] x : boardd.getTheboard()) {
            String convert = Arrays.toString(x);
            if (!checker.containsKey(convert) || convert.contains("x")) {
                checker.put(convert, 1);
            } else {
                return false;
            }
        }

        Map<String, Integer> checker2 = new HashMap<>();
        for (int i = 0; i < board.getN(); i++) {
            char[] tempcoloumn = getColumn(boardd.getTheboard(), i);
            String convert = Arrays.toString(tempcoloumn);
            if (!checker2.containsKey(convert) || convert.contains("x")) {
                checker2.put(convert, 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isBoardValid(Board boardd) {
        return isContainsEqualAmountOf1And0(boardd) && isContainsDuplicateRowAndColumn(boardd) && isContains3ConsecutiveNumbersInARow(boardd);
    }
}
