package Solver.ALOL_Webapp.service;

import Solver.ALOL_Webapp.model.Board;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
@Service
public class BoardService {
    private Board board;
    private int n;


    public void filltoken (int i, int j , char token, char[][] board) {
        board[i][j] = token;
    }

    public Board getEmptyBoard (int n) {
        Board board = new Board(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board.getTheboard()[i][j] = 'x';
            }
        }
        return board;
    }

    public char [][] startingBoard (String input, int n) {
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String[] lines = input.split("/");

            // if nums of lines doesnt match n
            if (lines.length != n) {
                return new char[][]{};

            }
            for (int j = 0; j < n; j++) {
                char[] tokens = lines[j].toCharArray();

                // if nums of columns doesnt match n
                if (tokens.length != n) {
                    return new char[][]{};

                }
                for (int k = 0; k < tokens.length; k++) {
                    if (tokens[k] != '1' && tokens[k] != '0' && tokens[k] != 'x') {
                        return new char[][]{};

                    }
                    filltoken(j,k, tokens[k], board);
                }
            }
        }
        return board;
    }

    public char[][] getSolvedBoard(String input, int n) {
        // create 2 Board Objs

        Board result;
        Board board = new Board(n);
        char[][] startingBoard = startingBoard(input, n);
        board.setTheboard(startingBoard);

        BoardLogik logic = new BoardLogik(board);
        BoardSolve solve = new BoardSolve(board, logic);

        solve.solvedBoard(board);
        return solve.getSolution();


    }

    public char[][] addStringBoard(String input, int n) {
        Board board = new Board(n);
        char[][] startingBoard = startingBoard(input, n);
        board.setTheboard(startingBoard);

        BoardLogik logic = new BoardLogik(board);
        BoardSolve solve = new BoardSolve(board, logic);

        solve.solvedBoard(board);
        return solve.getSolution();
    }


}


