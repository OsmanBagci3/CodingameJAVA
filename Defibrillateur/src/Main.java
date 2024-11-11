import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * Le but de ce problème est de faire un système qui permet de trouver les défibrillateurs
 * les plus proches en fonction de la position de l'utilisateur
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        String LAT = in.next();
        LAT = LAT.replace(",", ".");
        LON = LON.replace(",", ".");
        int N = in.nextInt();

        double distance_calcule; //initialisation variable de la distance calculée
        double distance_retenue = 1000000;  //initialisation de la distance retenue

        if (in.hasNextLine()) {
            in.nextLine();
        }
        double x;
        double y;
        String nom_retenu = ""; // initialisation de la variable de sortie
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            String[] splittedDEFIB = DEFIB.split(";");

            /* calcul de la position du defibrilateur DEFIB */
            x = Math.cos((Double.parseDouble(LAT) + Double.parseDouble(splittedDEFIB[5].replace(",", "."))) / 2) * (Double.valueOf(splittedDEFIB[4].replace(",", ".")) - Double.valueOf(LON));
            y = Double.parseDouble(splittedDEFIB[5].replace(",", ".")) - Double.valueOf(LAT);
            distance_calcule = 6371 * Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

            /* Comparaison et mise à jour si plus proche */
            if (distance_calcule < distance_retenue) {
                distance_retenue = distance_calcule;
                nom_retenu = splittedDEFIB[1]; // Position du Nom du lieu dans le tableau splittedDEFIB
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(nom_retenu);
    }
}