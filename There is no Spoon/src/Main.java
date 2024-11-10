import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/

/**
 * L'objectif est de parcourir une grille, identifier les noeuds et
 * leurs voisins et de renvoyer les coordonnées du noeud et de ses voisins
 * associés. Si le noeud n'a pas de voisin ou, a déjà un voisin identifié,
 * les coordonnées renvoyées sont -1;-1 .
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }

        int x1, y1, x2, y2, x3, y3 = 0; // initialisation des coordonnées
        int k = 0;
        String line = "";

        if (height < 2) { // Cas horizontal
            String[] Vector = new String[width];
            line = in.nextLine(); // width characters, each either 0 or .
            for (int j = 0; j < line.length(); j++) {
                Vector[j] = line.substring(j, j + 1);
            }

            x3 = y3 = -1; // dans le cas horizontal, pas de voisin sur l'axe vertical

            for (int j = 0; j < Vector.length; j++) {
                x1 = x2 = y1 = y2 = 0;
                if (Vector[j].equals("0")) { // check si noeud
                    x1 = j;
                    y1 = k;
                    if (j + 1 < Vector.length) { // check si à droite j'ai un voisin
                        for (int i = j + 1; i < Vector.length; i++) {
                            if (Vector[i].equals("0")) {
                                x2 = i;
                                y2 = y1;
                                break;
                            }
                        }
                    } else {
                        x2 = -1;
                        y2 = -1;
                    }
                    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3);
                }
            }

        } else if (width < 2) { // traite le cas Vertical
            String[] Vector = new String[height];
            for (int i = 0; i < height; i++) {
                line = in.nextLine();
                Vector[i] = line;
            }
            x2 = y2 = -1;
            for (int j = 0; j < Vector.length; j++) {
                x1 = x3 = y1 = y3 = 0;
                if (Vector[j].equals("0")) { // check si noeud
                    x1 = k;
                    y1 = j;
                    if (j + 1 < Vector.length) { // check si en dessous j'ai un voisin
                        for (int i = j + 1; i < Vector.length; i++) {
                            if (Vector[i].equals("0")) {
                                x3 = x1;
                                y3 = i;
                                break;
                            }
                        }
                    } else {
                        x3 = -1;
                        y3 = -1;
                    }
                    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3);
                }
            }
        } else { // tous les autres cas
            String[][] matrix = new String[width][height];
            for (int i = 0; i < height; i++) {
                line = in.nextLine(); // width characters, each either 0 or .
                String[] splittedLine = line.split("");
                for (int j = 0; j < line.length(); j++) {
                    matrix[i][j] = splittedLine[j];
                }
            }
            for (String[] row : matrix) {
                x1 = y1 = y2 = x3 = x2 = y3 = 0;
                for (int j = 0; j < row.length; j++) {
                    if (row[j].equals("0")) { // check si noeuD
                        x1 = j;
                        y1 = k;
                        if (k < height - 1) { // check si voisin en dessous
                            for (int i = k + 1; i < height; i++) {
                                if (matrix[i][j].equals("0")) {
                                    x3 = x1;
                                    y3 = i;
                                    break;
                                } else {
                                    x3 = -1;
                                    y3 = -1;
                                }
                            }
                        } else {
                            x3 = -1;
                            y3 = -1;
                        }
                        if (j + 1 < row.length) { // check si à droite j'ai un voisin
                            for (int i = j + 1; i < row.length; i++) {
                                if (row[i].equals("0")) {
                                    x2 = i;
                                    y2 = y1;
                                    break;
                                } else {
                                    x2 = -1;
                                    y2 = -1;
                                }
                            }
                        } else {
                            x2 = -1;
                            y2 = -1;
                        }
                        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3);
                    }
                }
                k = k + 1;
            }
        }

    }

    // Write an action using System.out.println()
    // To debug: System.err.println("Debug messages...");

    // Three coordinates: a node, its right neighbor, its bottom neighbor

}
