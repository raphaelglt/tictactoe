package org.example;
import com.condingf.tictactoe.board.Board;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
public class Main {
    static int rounds = 1;
    static boolean play = true;

    private static int getRandomNumberUsingNextInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    private static void selectRandomPlace (Board board, int actualPlayer) {
        int randomX = getRandomNumberUsingNextInt(board.getBoard().size());
        int randomY = getRandomNumberUsingNextInt(board.getBoard().size());
        while (board.getBoard().get(randomY).get(randomX).getPlayer() != 0) {
            randomX = getRandomNumberUsingNextInt(board.getBoard().size());
            randomY = getRandomNumberUsingNextInt(board.getBoard().size());
        }
        board.getBoard().get(randomY).get(randomX).setPlayer(actualPlayer);
    }

    private static void endOfTurn(Board board, int ent, int actualPlayer) {
        rounds+=1;
        System.out.println(board);
        play = endOfGameCheck(ent, board);
        if (actualPlayer == 1) {
            board.setTurnOfPlayer(2);
        } else if (actualPlayer == 2) {
            board.setTurnOfPlayer(1);
        } else {
            System.out.println("Player's number unknown");
        }
    }

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
            if (numberOfSame+1 == boardSize) {
                System.out.println("Player "+board.getTurnOfPlayer()+" is the tic TaC TOE monster !");
                return false;
            }
        }


        //vérifie les colonnes
        for (int i = 0; i<boardSize; i++) {
            int numberOfSame = 0;
            for (int j = 0; j<boardSize-1; j++) {
                if (board.getBoard().get(j).get(i).getPlayer() == board.getBoard().get(j+1).get(i).getPlayer() && board.getBoard().get(j).get(i).getPlayer() != 0) {
                    numberOfSame++;
                }
            }
            if (numberOfSame+1 == boardSize) {
                System.out.println("Player "+board.getTurnOfPlayer()+" is the tic TaC TOE monster !");
                return false;
            }
        }


        //vérifie la diagonale gauche
        int numberOfSame = 0;
        for (int i = 0; i<boardSize-1; i++) {
            if (board.getBoard().get(i).get(i).getPlayer() == board.getBoard().get(i+1).get(i+1).getPlayer() && board.getBoard().get(i).get(i).getPlayer() != 0) {
                numberOfSame++;
            }
        }
        if (numberOfSame+1 == boardSize) {
            System.out.println("Player "+board.getTurnOfPlayer()+" is the tic TaC TOE monster !");
            return false;
        }


        //vérifie la diagonale droite
        numberOfSame = 0;
        int boardLengthSize = boardSize-1;
        for (int i = 0; i<boardSize; i++) {
            if (board.getBoard().get(i).get(boardLengthSize-i).getPlayer() == board.getBoard().get(boardLengthSize-i).get(i).getPlayer() && board.getBoard().get(boardLengthSize-i).get(i).getPlayer() != 0) {
                numberOfSame++;
            }
        }
        if (numberOfSame == boardSize) {
            System.out.println("Player "+board.getTurnOfPlayer()+" is the tic TaC TOE monster !");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean replay = true;
        while (replay) {
            int numberOfPlayers;
            System.out.println("Please enter the number of players (1 or 2) :");
            String numberOfPlayersInput = input.next();
            try {
                numberOfPlayers = Integer.parseInt(numberOfPlayersInput);
                if (numberOfPlayers != 1 && numberOfPlayers != 2) {
                    continue;
                }
            } catch (Exception e) {
                continue;
            }

            boolean validation = false;
            int ent = 3;
            while (!validation) {
                System.out.println("Please enter the board size (between 3 and 10 included) :");
                String entInput = input.next();
                try {
                    ent = Integer.parseInt(entInput);
                    if (ent < 3 || ent > 10) {
                        System.out.println("Please enter number between 3 and 10");
                    } else {
                        System.out.println("Enjoy dear");
                        validation = true;
                    }
                } catch (Exception e) {
                    System.out.println("Please enter a number !");
                }
            }

            Board board = new Board(ent);
            System.out.println(board);
            while (play) {
                System.out.println("Round "+rounds+" "+" player's " + board.getTurnOfPlayer() + " turn");
                if (numberOfPlayers == 2 || rounds%2 == 1) {
                    boolean validPlacement = false;
                    while (!validPlacement) {
                        boolean validLine = false;
                        int numLine = 0;
                        while (!validLine) {
                            System.out.println("Which line ? : ");
                            numLine = input.nextInt();
                            if (numLine < 0 || numLine >= ent) {
                                System.out.println("Take a line between 0 and " + ent);
                            } else {
                                validLine = true;
                            }
                        }

                        boolean validColumn = false;
                        int numColumn = 0;
                        while (!validColumn) {
                            System.out.println("Which column ? : ");
                            numColumn = input.nextInt();
                            if (numColumn < 0 || numColumn >= ent) {
                                System.out.println("Take a column between 0 and " + ent);
                            } else {
                                validColumn = true;
                            }
                        }
                        if (board.getBoard().get(numLine).get(numColumn).getPlayer() != 0) {
                            System.out.println("There is already something on this square");
                        } else {
                            int actualPlayer = board.getTurnOfPlayer();
                            validPlacement=true;
                            board.getBoard().get(numLine).get(numColumn).setPlayer(actualPlayer);
                            endOfTurn(board, ent, actualPlayer);
                        }
                    }
                } else {
                    int actualPlayer = board.getTurnOfPlayer();
                    selectRandomPlace(board, actualPlayer);
                    endOfTurn(board, ent, actualPlayer);
                }
            }
            System.out.println("Do you want to replay ? (y/n) : ");
            String wantToReplay = input.next();
            if (!Objects.equals(wantToReplay, "y")) {
                replay = false;
            }
        }
    }
}
