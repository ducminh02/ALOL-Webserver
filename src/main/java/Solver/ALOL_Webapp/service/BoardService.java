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

    public String getTest () {
        return "this is a test";
    }
//    public Board getEmptyBoard(int n) {
//        Board board = new Board(n);
//        BoardLogik logik = new BoardLogik(board);
//        BoardSolve solve = new BoardSolve(board, logik);
//        solve.emptyBoard();
//        return board;
//    }

//    public void filltoken (int i, int j , char token, char[][] board) {
//        board[i][j] = token;
//    }
//
//    public Board getEmptyBoard (int n) {
//        Board board = new Board(n);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                board.getTheboard()[i][j] = 'x';
//            }
//        }
//        return board;
//    }
//
//    public char [][] startingBoard (String input, int n) {
//        char[][] board = new char[n][n];
//
//        for (int i = 0; i < n; i++) {
//            String[] lines = input.split("/");
//
//            // if nums of lines doesnt match n
//            if (lines.length != n) {
//                throw  new InputMismatchException("Invalid Board");
//
//            }
//            for (int j = 0; j < n; j++) {
//                char[] tokens = lines[j].toCharArray();
//
//                // if nums of columns doesnt match n
//                if (tokens.length != n) {
//                    throw  new InputMismatchException("Invalid Board");
//
//                }
//                for (int k = 0; k < tokens.length; k++) {
//                    if (tokens[k] != '1' && tokens[k] != '0' && tokens[k] != 'x') {
//                        throw  new InputMismatchException("Invalid Board");
//
//                    }
//                    filltoken(j,k, tokens[k], board);
//                }
//            }
//        }
//        return board;
//    }

    public char[][] getSolvedBoard(String input, int n) {
        // create 2 Board Objs

        Board board = new Board(n);
        BoardLogik logic = new BoardLogik(board);
        BoardSolve solve = new BoardSolve(board, logic);

        char[][] startingBoard = solve.startingBoard(input, n);
        board.setTheboard(startingBoard);



        solve.solvedBoard(board);
        int random = (int)Math.floor(Math.random() * (solve.getSolution().size()-1 + 1) + 0);
        return solve.getSolution().get(random);
    }

    public static void main(String[] args) {
        BoardService service = new BoardService();
        char[][] result = new char[4][4];

        result = service.getSolvedBoard("xxxx/xxxx/xxxx/xxxx", 4);

        System.out.println(Arrays.deepToString(result));
    }
}
