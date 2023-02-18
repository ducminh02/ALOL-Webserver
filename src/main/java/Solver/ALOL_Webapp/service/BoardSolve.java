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

    private List <char[][]> solution;


    public BoardSolve(Board board, BoardLogik logic) {
        this.board = board;
        this.logic = logic;
        solution = new ArrayList<>();
    }

    public void filltoken (int i, int j , char token, char[][] board) {
        board[i][j] = token;
    }
    public char [][] startingBoard (String input, int n) {
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            String[] lines = input.split("/");

            // if nums of lines doesnt match n
            if (lines.length != n) {
                throw  new InputMismatchException("Invalid Board");

            }
            for (int j = 0; j < n; j++) {
                char[] tokens = lines[j].toCharArray();

                // if nums of columns doesnt match n
                if (tokens.length != n) {
                    throw  new InputMismatchException("Invalid Board");

                }
                for (int k = 0; k < tokens.length; k++) {
                    if (tokens[k] != '1' && tokens[k] != '0' && tokens[k] != 'x') {
                        throw  new InputMismatchException("Invalid Board");

                    }
                    filltoken(j,k, tokens[k], board);
                }
            }
        }
        return board;
    }

    public void addToSolution (char[][] board) {
        char[][] possibleSolution = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, possibleSolution[i], 0, board.length);
        }
        solution.add(possibleSolution);
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
            addToSolution(board.getTheboard());
//            System.out.println(Arrays.deepToString(board.getTheboard()));
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
            board.getTheboard()[i][j] = 'x';

        }
    }


    public static void main(String[] args) {
        Board board = new Board(6);
        BoardLogik logic = new BoardLogik(board);
        BoardSolve boardSolve = new BoardSolve(board, logic);

        boardSolve.board.setTheboard(boardSolve.startingBoard("0xxxxx/xxxxxx/xxxxxx/xxxxxx/xxxxxx/0xxxxx", 6));

        boardSolve.solvedBoard(boardSolve.getBoard());
        List<char[][]> results = boardSolve.solution;
        System.out.println(results);
    }
}