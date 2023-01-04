package com.condingf.tictactoe.board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();
    private int turnOfPlayer = 1;

    public Board(int turnOfPlayer, int sizeOfBoard) {
    }


    @Override
    public String toString() {
        String returnString = "";
        for (List<String> element : this.board) {
            returnString += element;
        }
        return "Board{" +
                "board=" + board +
                ", turnOfPlayer=" + turnOfPlayer +
                '}';
    }

    public int getTurnOfPlayer() {
        return turnOfPlayer;
    }

    public void setTurnOfPlayer(int turnOfPlayer) {
        this.turnOfPlayer = turnOfPlayer;
    }
}
