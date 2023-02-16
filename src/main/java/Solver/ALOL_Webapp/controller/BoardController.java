package Solver.ALOL_Webapp.controller;

import Solver.ALOL_Webapp.model.Board;
import Solver.ALOL_Webapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (path = "api/v1/board")
public class BoardController{
    private int n;
//    private Board board;

    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

//    @GetMapping
//    public Board getEmptyBoard() {
//        return boardService.getEmptyBoard(4);
//    }

    @GetMapping
    public char[][] getSolvedBoard() {
        return boardService.getSolvedBoard("10x1/xx10/1x0x/0011", 4);
    }

    
}
