import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * L'objectif est de retrouver la température la plus proche de 0
 * Parmi une liste de température.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int pivot = 5527; /*initialisation d'un pivot qui sera aussi la valeur
        de température  en sortie*/

        // Cas n = 0, Pas de température
        if (n == 0) {
            System.out.println("0");
        }

        // Cas n différent de 0
        if (n != 0) {
            // Boucle sur toutes les températures
            for (int i = 0; i < n; i++) {
                int t = in.nextInt(); // a temperature expressed as an integer ranging from -273 to 5526
                if (Math.abs(t) < Math.abs(pivot)) {
                    pivot = t; //Nouvelle valeur de pivot si on trouve une température plus proche de 0
                } else if (Math.abs(t) == Math.abs(pivot) && (t > 0 || pivot > 0)) {  //Gestion du cas où pivot et t ont les mêmes valeurs absolue
                    pivot = Math.abs(t);
                }
                ;
            }
            System.out.println(pivot);

        }
    }
}