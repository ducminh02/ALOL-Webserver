package Solver.ALOL_Webapp.service;

import Solver.ALOL_Webapp.model.Board;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BoardService {
    public char[][] getSolvedBoard(String input, int n) {
        // create 2 Board Objs

        Board board = new Board(n);
        BoardLogik logic = new BoardLogik(board);
        BoardSolve solve = new BoardSolve(board, logic);

        char[][] startingBoard = solve.startingBoard(input, n);
        board.setTheboard(startingBoard);
        solve.solveBoard(board);
        int random = (int) Math.floor(Math.random() * (solve.getSolution().size() - 1 + 1) + 0);
        return solve.getSolution().get(random);
    }

    public static void main(String[] args) {
        BoardService service = new BoardService();
        char[][] result;

        result = service.getSolvedBoard("xxxx/xxxx/xxxx/xxxx", 4);

        System.out.println(Arrays.deepToString(result));
    }
}
