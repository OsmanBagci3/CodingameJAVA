import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * Nous cherchons une solution permettant de récupérer le nombre de fois qu'un chiffre k apparaît dans un interval
 * [0; n] d'entier positif, avec n donnée en entrée.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();

        int power = 1; // nous allons procéder avec les puissances de 10, initialisation de la variable

        int x2 = CalculOccurrence(n, power, k); // appelle de la méthode permettant de calculer le nombre d'occurrence de k

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(x2);
    }


    public static int CalculOccurrence(int n, int power, int k) {
        int compteur = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        do {
            a = n / (power * 10);
            b = n % (power * 10);
            c = b / power;
            compteur += a * power;
            if (c > k) {
                compteur += power;
            } else if (c == k) {
                compteur += (b % power) + 1;
            }
            power *= 10;

        } while (power <= n);

        return compteur;
    }


}