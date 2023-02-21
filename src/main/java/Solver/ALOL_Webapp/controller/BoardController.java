package Solver.ALOL_Webapp.controller;

import Solver.ALOL_Webapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/board")
public class BoardController {
    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping(path = "/sendInput")
    public char[][] sendInput(@RequestBody String input) {
        return boardService.getSolvedBoard(input, 6);
    }

}
