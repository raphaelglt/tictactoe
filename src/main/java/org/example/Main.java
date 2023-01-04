package org.example;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner boardSize = new Scanner(System.in);
        System.out.println("Please enter the board size ");
        int ent = boardSize.nextInt();
        if (ent>10){
            System.out.println("Please enter number between 3 and 10");
        }else {
            System.out.println("Enjoy dear");
        }
        boolean play = true;

        while (play){

        }

    }
}
