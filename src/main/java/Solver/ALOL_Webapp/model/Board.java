package Solver.ALOL_Webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter


public class Board {
    private final int n;
    private char[][] theboard;


    // Constructor
    public Board(int n) {
        if (n < 1 || n > 15 || n % 2 == 1) {
            throw new InputMismatchException("Invalid Board-size ");
        }
        this.n = n;
        this.theboard = new char[n][n];

    }

}