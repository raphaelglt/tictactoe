package com.condingf.tictactoe.board;

import com.codingf.tictactoe.square.Square;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
    private int turnOfPlayer = 1;

    public Board(int sizeOfBoard) {
        for (int i = 0; i<sizeOfBoard; i++) {
            ArrayList<Square> line = new ArrayList<Square>();
            for (int j = 0; j<sizeOfBoard; j++) {
                Square square = new Square();
                line.add(square);
            }
            this.board.add(line);
        }
        System.out.println(this.board+" "+this.board.size()+" "+this.board.get(0).size());
    }


    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (int i = 0; i<this.board.size(); i++) {
            for (int j = 0; j<this.board.get(0).size(); j++) {
                if (this.board.get(i).get(j).getPlayer() == 0) {
                    returnString.append("[-]");
                } else if (this.board.get(i).get(j).getPlayer() == 1) {
                    returnString.append("[X]");
                } else if (this.board.get(i).get(j).getPlayer() == 2) {
                    returnString.append("[O]");
                }
            }
            returnString.append("\n");
        }
        return returnString.toString();
    }

    public int getTurnOfPlayer() {
        return turnOfPlayer;
    }

    public void setTurnOfPlayer(int turnOfPlayer) {
        this.turnOfPlayer = turnOfPlayer;
    }
};