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
