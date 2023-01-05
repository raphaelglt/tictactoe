package org.example;
import com.condingf.tictactoe.board.Board;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean validation = false;
        int ent = 0;
        while (validation == false) {
            System.out.println("Please enter the board size ");
            ent = input.nextInt();
            if (ent < 3 || ent > 10) {
                System.out.println("Please enter number between 3 and 10");
            }
            else {
                System.out.println("Enjoy dear");
                validation = true;
            }
        }

        Board board = new Board(ent);
        boolean play = true;
        int actualPlayer = 1;
        while (play) {
            boolean validPlacement = false;
            while (!validPlacement) {
                boolean validLine = false;
                int numLine = 0;
                while (!validLine) {
                    System.out.println("Which Line ? : ");
                    String numLineInput = input.next();
                    try{
                        numLine = Integer.parseInt(numLineInput);
                        if (numLine < 0 || numLine >= ent) {
                            System.out.println("Take a line between 0 and " + ent);
                        } else {
                            validLine = true;
                        }
                    }
                    catch (Exception e){
                        System.out.println("Please enter a valid number");
                    }
                }

                boolean validColumn = false;
                int numColumn = 0;
                while (!validColumn) {
                    System.out.println("Which column ? : ");
                    String numColumnInput = input.next();
                    try {
                        numColumn = Integer.parseInt(numColumnInput);
                        if (numColumn < 0 || numColumn >= ent) {
                            System.out.println("Take a column between 0 and " + ent);
                        }
                        else{
                            validColumn = true;
                        }
                    } catch (Exception e) {
                        System.out.println("please enter a valid number");
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
