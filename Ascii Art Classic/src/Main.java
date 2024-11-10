import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int L = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String T = in.nextLine();
        T = T.toUpperCase(); //Rendre le texte non sensible à la casse et on considère uniquement les majuscules
        StringBuilder answer = new StringBuilder(); //Initialisation d'un stringBuilder pour la sortie
        String ROW = "";

        for (int i = 0; i < H; i++) {
            ROW = in.nextLine(); //Alphabet ordonné + "?" représenté en art ASCII en fonction de H

            /* Pour chaque caractère de notre texte, calcul de la position
            puis ajout de sa représentation ASCII dans le StringBuilder*/
            for (char ch : T.toCharArray()) {
                int position = ch < 65 || ch > 90 ? 26 : ch - 65;
                answer.append(ROW.substring(position * L, position * L + L));

            }
            answer.append("\n"); // Saut de ligne
        }
        System.out.println(answer);

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
    }
}