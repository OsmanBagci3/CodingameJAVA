import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * L'objectif est de résoudre un problème mathématique simple, qui consiste
 * à faire la différence entre les 2 valeurs les plus proches parmi
 * les nombres en entrées.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int diffPivot = 1000000;
        int piPivot = 0;
        int diff = 0;

        List<Integer> listPuissance = new ArrayList<>(); // Création d'une liste
        for (int i = 0; i < n; i++) {
            int pi = in.nextInt();
            listPuissance.add((Integer) pi); // Affectation de toute les valeurs d'entrée dans la liste
        }

        Collections.sort(listPuissance); // tri de la liste

        piPivot = listPuissance.getFirst(); // récupération de la première valeur

        //Parcourir la liste à partir de la deuxième valeur et mettre à jour la
        //plus petite différence dans la liste
        for (int i = 1; i < listPuissance.size(); i++) {
            diff = Math.abs(listPuissance.get(i) - piPivot);
            if (diff <= diffPivot) {
                diffPivot = diff;
            }
            piPivot = listPuissance.get(i);
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(diffPivot);
    }
}