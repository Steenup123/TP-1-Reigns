package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // début du jeu
        System.out.println("Bienvenue sur Reigns");

        Scanner scanner = new Scanner(System.in);
        System.out.flush();
        if (scanner.nextInt() == 1) {
            new JeuGOT().InitJeu();
        } else {
            new Jeu().InitJeu();
        }
    }
}
