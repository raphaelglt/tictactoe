package org.example;
import com.condingf.tictactoe.board.Board;

import java.util.Scanner;
public class Main {

    private static boolean endOfGameCheck(int boardSize, Board board) {
        //regarde si toutes les cases sont prises
        int numberOfUntaken = 0;
        for (int i = 0; i<boardSize; i++) {
            for (int j = 0; j<boardSize; j++) {
                if (board.getBoard().get(i).get(j).getPlayer() == 0) {
                    numberOfUntaken++;
                }
            }
        }
       if(numberOfUntaken==0) {
            System.out.println("Fin de partie : toutes les places sont prises");
            return false;
        }


        //vérifie les lignes
        for (int i = 0; i<boardSize; i++) {
            int numberOfSame = 0;
            for (int j = 0; j<boardSize-1; j++) {
                if (board.getBoard().get(i).get(j).getPlayer() == board.getBoard().get(i).get(j+1).getPlayer() && board.getBoard().get(i).get(j).getPlayer() != 0) {
                    numberOfSame++;
                }
            }
           if (numberOfSame+1 == boardSize) return false;
        }


        //vérifie les colonnes
        for (int i = 0; i<boardSize; i++) {
            int numberOfSame = 0;
            for (int j = 0; j<boardSize-1; j++) {
                if (board.getBoard().get(j).get(i).getPlayer() == board.getBoard().get(j+1).get(i).getPlayer() && board.getBoard().get(j).get(i).getPlayer() != 0) {
                    numberOfSame++;
                }
            }
            if (numberOfSame+1 == boardSize) return false;
        }


        //vérifie la diagonale gauche
        int numberOfSame = 0;
        for (int i = 0; i<boardSize-1; i++) {
            if (board.getBoard().get(i).get(i).getPlayer() == board.getBoard().get(i).get(i+1).getPlayer() && board.getBoard().get(i).get(i).getPlayer() != 0) {
                numberOfSame++;
            }
        }
        if (numberOfSame+1 == boardSize) return false;


        //vérifie la diagonale droite
        numberOfSame = 0;
        int boardLengthSize = boardSize-1;
        for (int i = 0; i<boardSize; i++) {
            if (board.getBoard().get(i).get(boardLengthSize-i).getPlayer() == board.getBoard().get(boardLengthSize-i).get(i).getPlayer() && board.getBoard().get(boardLengthSize-i).get(i).getPlayer() != 0) {
                numberOfSame++;
            }
        }
        return numberOfSame != boardSize;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean validation = false;
        int ent = 0;
        while (validation == false) {
            System.out.println("Please enter the board size ");
            ent = input.nextInt();
            if (ent < 3 || ent > 10) {
                System.out.println("Please enter number between 3 and 10");
            } else {
                System.out.println("Enjoy dear");
                validation = true;
            }
        }

        Board board = new Board(ent);
        boolean play = true;
        int actualPlayer = 1;
        while (play) {
            boolean validPlacement = false;
            while (validPlacement == false) {
                boolean validLine = false;
                int numLine = 0;
                while (validLine == false) {
                    System.out.println("Which Line ? : ");
                    numLine = input.nextInt();
                    if (numLine < 0 || numLine >= ent) {
                        System.out.println("Take a line between 0 and " + ent);
                    } else {
                        validLine = true;
                    }
                }


                boolean validColumn = false;
                int numColumn = 0;
                while (validColumn == false) {
                    System.out.println("Which column ? : ");
                    numColumn = input.nextInt();
                    if (numColumn < 0 || numColumn >= ent) {
                        System.out.println("Take a column between 0 and " + ent);
                    } else {
                        validColumn = true;
                    }
                }
                if (board.getBoard().get(numLine).get(numColumn).getPlayer() != 0){
                    System.out.println("There is already something on this square");
                }
                else{
                    board.getBoard().get(numLine).get(numColumn).setPlayer(actualPlayer);
                    System.out.println(board);
                    if (actualPlayer == 1) {
                        actualPlayer = 2;
                    } else if (actualPlayer == 2) {
                        actualPlayer = 1;
                    } else {
                        System.out.println("Player's number unknown");
                    }
                    validPlacement = true;
                }
            }
        }
    }
}
