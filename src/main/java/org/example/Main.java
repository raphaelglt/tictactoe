package org.example;
import com.sun.jdi.IntegerValue;

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner boardSize = new Scanner(System.in);

            while (true) {
                System.out.println("Please enter the board size ");
                String ent = boardSize.nextLine();
                int number = 0;
                try {
                    number = Integer.valueOf(ent);
                } catch (Exception e) {
                    System.err.println("Please enter a valid number");

                }

                if (number > 10 || number < 3) {
                    System.err.println("Please enter number between 3 and 10");
                } else {
                    System.out.println("Enjoy dear");
                    break;
                }
            }
        boolean play = true;
            while (play) {


                // afficher le tableau
                System.out.println("tableau");
                // Demande du coup du joueur
                System.out.print("Entrez la ligne : ");
                int row = boardSize.nextInt();
                System.out.print("Entrez la colonne : ");
                int col = boardSize.nextInt();

                // Vérification que la case choisie est vide
                if ("case choise") {
                    System.out.println("Case déjà occupée, veuillez en choisir une autre.");
                    continue;
                }
            }
        }

    }

