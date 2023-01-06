package org.example;
import com.condingf.tictactoe.board.Board;

import javax.sound.sampled.Line;
import java.util.Random;
import java.util.Scanner;
public class Main {
    //déclaration des variables statiques (accessible depuis tout le code)
    static int rounds = 1;
    static boolean play = true;
    static Board board;

    //prend un numéro au hasard entre 0 et le nombre passé en paramètre
    private static int getRandomNumberUsingNextInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    //place la case au hasard
    private static void selectRandomPlace (int actualPlayer) {
        int randomX = getRandomNumberUsingNextInt(board.getBoard().size());
        int randomY = getRandomNumberUsingNextInt(board.getBoard().size());
        while (board.getBoard().get(randomY).get(randomX).getPlayer() != 0) {
            randomX = getRandomNumberUsingNextInt(board.getBoard().size());
            randomY = getRandomNumberUsingNextInt(board.getBoard().size());
        }
        board.getBoard().get(randomY).get(randomX).setPlayer(actualPlayer);
    }

    //incrémente le round, affiche le tableau, vérifie les conditions de fin et change le joueur
    private static void endOfTurn(int ent, int actualPlayer) {
        rounds+=1;
        System.out.println(board);
        play = endOfGameCheck(ent);
        if (actualPlayer == 1) {
            board.setTurnOfPlayer(2);
        } else if (actualPlayer == 2) {
            board.setTurnOfPlayer(1);
        } else {
            System.out.println("Player's number unknown");
        }
    }

    //vérifie les conditions de fin de partie
    private static boolean endOfGameCheck(int boardSize) {
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
        return true;
    }

    public static void main(String[] args) {
        int numberOfPlayers = 0;
        int ent = 0;
        boolean replay = true;
        //boucle sur tous les paramètres
        for (int i = 0; i<args.length; i++) {
            //vérifie le paramètre players s'il est présent
            if (args[i].equals("--players") || args[i].equals("-p")) {
                try {
                    int numberOfPlayersTemp = Integer.parseInt(args[i+1]);
                    if (numberOfPlayersTemp == 1 || numberOfPlayersTemp == 2) {
                        System.out.println("Argument --players is correct");
                        numberOfPlayers = numberOfPlayersTemp;
                    } else {
                        System.err.println("Arguments --players isn't 1 nether 2");
                    }
                } catch (Exception e) {
                    System.err.println("Argument --player isn't a number");
                }
            }

            //vérifie le paramètre size s'il est présent
            if (args[i].equals("--size") || args[i].equals("-s")) {
                try {
                    int entTemp = Integer.parseInt(args[i+1]);
                    if (entTemp >= 3 && entTemp <= 10) {
                        System.out.println("Argument --size is correct");
                        ent = entTemp;
                    } else {
                        System.err.println("Argument --size isn't included between 3 and 10");
                    }
                } catch (Exception e) {
                    System.err.println("Argument --size isn't a number");
                }
            }

            //affiche l'aide
            if (args[i].equals("--help") || args[i].equals("-h")) {
                replay = false;
                System.out.println("Options :\n -p,\t--players\tNumber of players (1 or 2)\n -s,\t--size\t\tSize of the board (between 3 and 10 included)");
            }
        }
        Scanner input = new Scanner(System.in);

        while (replay) {
            if (numberOfPlayers == 0) {
                boolean validNumberOfPlayers = false;
                while (!validNumberOfPlayers) {
                    System.out.println("Please enter the number of players (1 or 2) :");
                    String numberOfPlayersInput = input.next();
                    try {
                        numberOfPlayers = Integer.parseInt(numberOfPlayersInput);
                        if (numberOfPlayers == 1 || numberOfPlayers == 2) {
                            validNumberOfPlayers = true;
                        } else {
                            System.err.println("Please enter a valid number of player");
                        }
                    } catch (Exception e) {
                        System.err.println("Please enter a valid number of player");
                    }
                }
            }

            if (ent == 0) {
                boolean validation = false;
                while (!validation) {
                    System.out.println("Please enter the board size (between 3 and 10 included) :");
                    String entInput = input.next();
                    try {
                        ent = Integer.parseInt(entInput);
                        if (ent < 3 || ent > 10) {
                            System.err.println("Please enter number between 3 and 10");
                        } else {
                            System.out.println("Enjoy dear");
                            validation = true;
                        }
                    } catch (Exception e) {
                        System.err.println("Please enter a number !");
                    }
                }
            }

            board = new Board(ent);
            System.out.println(board);
            while (play) {
                //Affichage du round à chaque tour jusqu'à la fin de la partie
                System.out.println("Round " + rounds + " player's " + board.getTurnOfPlayer() + " turn");
                if (numberOfPlayers == 2 || rounds % 2 == 1) {
                    boolean validPlacement = false;
                    while (!validPlacement) {
                        boolean validLineColumn = false;
                        int line = 0;
                        int column = 0;
                        //Boucle vérifiant le choix des coordonnées du placement du joueur
                        while (!validLineColumn) {
                            System.out.println("Which line and column ? : ");
                            String lineColumnInput = input.next();
                            var fields = lineColumnInput.split(",");
                            try{
                                line = Integer.parseInt(fields[0]);
                                column = Integer.parseInt(fields[1]);
                                //vérifie que les coordonnées se situent dans le tableau
                                if (line < 0 || line >= ent || column < 0 || column >= ent){
                                    System.err.println("Take a line and a column between 0 and " + (ent - 1));
                                }
                                else {
                                    validLineColumn = true;
                                }
                            } catch (Exception e){
                                System.err.println("Please enter some valid numbers");
                            }
                        }

                        // vérifie si la case est déjà prise ou non
                        if (board.getBoard().get(line).get(column).getPlayer() != 0) {
                            System.out.println("There is already something on this square");
                        } else {
                            int actualPlayer = board.getTurnOfPlayer();
                            validPlacement = true;
                            board.getBoard().get(line).get(column).setPlayer(actualPlayer);
                            endOfTurn(ent, actualPlayer);
                        }
                    }
                } else {
                    int actualPlayer = board.getTurnOfPlayer();
                    selectRandomPlace(actualPlayer);
                    endOfTurn(ent, actualPlayer);
                }
            }
            //propose de rejouer à la fin de la partie
            System.out.println("Do you want to replay ? (y/n) : ");
            String wantToReplay = input.next();
            if (!wantToReplay.toLowerCase().equals("y")) {
                replay = false;
            } else {
                rounds = 1;
                play = true;
            }
        }
    }
}
