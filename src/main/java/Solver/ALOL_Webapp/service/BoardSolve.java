package Solver.ALOL_Webapp.service;

import Solver.ALOL_Webapp.model.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class BoardSolve {
    private final Board board;
    private final BoardLogik logic;

    private char[][] solution;


    public BoardSolve(Board board, BoardLogik logic) {
        this.board = board;
        this.logic = logic;
        solution = new char[board.getN()][board.getN()];
    }

    public boolean isFull(Board boardd) {
        for (int i = 0; i < board.getN(); i++) {
            for (int j = 0; j < board.getN(); j++) {
                if (boardd.getTheboard()[i][j] == 'x') {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Character> possibleEntries(Board boardd, int i, int j) {
        List<Character> possibilities = new ArrayList<>();

        boardd.getTheboard()[i][j] = '0';

        if (logic.checkall(boardd)) {
            possibilities.add('0');
        }
        boardd.getTheboard()[i][j] = '1';
        if (logic.checkall(boardd)) {
            possibilities.add('1');
        }
        return possibilities;
    }

    public void solvedBoard (Board board) {

        int i = 0;
        int j = 0;
        List<Character> possibilities;


        if (isFull(board)) {
            System.out.println("Board solved!");
            solution = this.board.getTheboard();

        }

        else {
            for (int x = 0; x < board.getN(); x++) {
                for (int y = 0; y < board.getN(); y++) {
                    if (board.getTheboard()[x][y] == 'x') {
                        i = x;
                        j = y;
                        break;
                    }
                }
            }
            possibilities = possibleEntries(board, i , j);
            for (char c : possibilities ) {
                board.getTheboard()[i][j] = c;
                solvedBoard(board);
            }
//            board.getTheboard()[i][j] = 'x';

        }
    }

}