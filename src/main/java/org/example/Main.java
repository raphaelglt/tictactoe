package org.example;
import com.condingf.tictactoe.board.Board;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner boardSize = new Scanner(System.in);
        boolean validation = false;
        int ent = 0;
        while (validation == false) {
            System.out.println("Please enter the board size ");
            ent = boardSize.nextInt();
            if (ent < 3 || ent > 10) {
                System.out.println("Please enter number between 3 and 10");
            } else {
                System.out.println("Enjoy dear");
                validation = true;
            }
        }

        Board board = new Board(ent);
        boolean play = true;
        while (play) {
            boolean validPlacement = false;
            while (validPlacement == false) {
                Scanner ligne = new Scanner(System.in);
                boolean validLine = false;
                int numLine = 0;
                while (validLine == false) {
                    System.out.println("Which Line ? : ");
                    numLine = boardSize.nextInt();
                    if (numLine < 0 || numLine > ent) {
                        System.out.println("Take a line between 0 and " + ent);
                    } else {
                        validLine = true;
                    }
                }



                Scanner colonne = new Scanner(System.in);
                boolean validColumn = false;
                int numColumn = 0;
                while (validColumn == false) {
                    System.out.println("Which column ? : ");
                    numColumn = boardSize.nextInt();
                    if (numColumn < 0 || numColumn > ent) {
                        System.out.println("Take a column between 0 and " + ent);
                    } else {
                        validColumn = true;
                    }
                }
                if (board.getBoard().get(numLine).get(numColumn).getPlayer() != 0){
                    System.out.println("There is already something on this square");
                }
                else{
                    board.getBoard().get(numLine).get(numColumn).setPlayer(1);
                    System.out.println(board);
                    validPlacement = true;
                }
            }
        }
    }
}
