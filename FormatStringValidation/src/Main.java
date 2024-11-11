import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * <p>
 * L'objectif de ce problème est de vérifier que un texte donné correspond bien à un format de texte
 * donnée, suivant les règles ci-dessous :
 * - dans la variable format "?" doit match avec un seul caractère
 * - dans la variable format tout les caractères qui ne sont ni "?" ou le caractère vague
 * doit matcher avec lui même
 * - dans la variable format le caractère vague (126e decimal de la table ASCII) peut
 * matcher avec plusieurs caractère jusqu'à qu'il y ait "?" ou quelconque autre
 * caractère
 * <p>
 * Si toutes ces règles sont respectées, on renvoit en sortie "MATCH" sinon "FAIL"
 **/

/**
 * L'objectif de ce problème est de vérifier que un texte donné correspond bien à un format de texte
 * donnée, suivant les règles ci-dessous :
 * - dans la variable format "?" doit match avec un seul caractère
 * - dans la variable format tout les caractères qui ne sont ni "?" ou le caractère vague
 * doit matcher avec lui même
 * - dans la variable format le caractère vague (126e decimal de la table ASCII) peut
 * matcher avec plusieurs caractère jusqu'à qu'il y ait "?" ou quelconque autre
 * caractère
 *
 * Si toutes ces règles sont respectées, on renvoit en sortie "MATCH" sinon "FAIL"
 **/

/**
 * pour le moment cette solution fonctionne à 87%
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine(); // entrée texte
        String format = in.nextLine(); // entrée format

        String[] splittedFormat = format.split("");

        boolean match = true; // initialisation d'un boolean pour affichage
        /* initialisation variables */
        char charActuel;
        int charActuelInt;

        int textPos = 0;
        char textCharActuel;
        int textCharActuelInt;
        String nextChar = "";
        char charNextChar;
        int intCharNextChar;

        /* Parcours de la variable format */
        for (int i = 0; i < format.length(); i++) {
            charActuel = format.charAt(i);
            charActuelInt = (int) charActuel;
            /* Vérifier si on est ni sur un "?" ni une vague */
            if (!(charActuelInt == 126) && !(charActuelInt == 63)) {
                textCharActuel = text.charAt(textPos);
                textCharActuelInt = (int) textCharActuel;
                textPos += 1;
                /*
                 * c'est les deux caractères dans le texte et dans format ne sont pas les mêmes
                 * on renvoie FAIL
                 */
                if (!(charActuelInt == textCharActuelInt)) {
                    match = false;
                }
            } else if (charActuelInt == 63) { // Cas où c'est un "?"
                textPos += 1;
            } else {
                /*
                 * ici on traite le cas où on a le caractère vague dans format.
                 * On compare chaque caractère en fonction de notre
                 * position dans text et par rapport au caractère suivant dans format
                 * tant que ce caractère suivant dans format ne match pas avec un caractère
                 * dans text, on avance de un caractère dans text
                 */

                if (i < format.length() - 1) {
                    nextChar = checkNextChar(i, splittedFormat);
                    charNextChar = nextChar.charAt(0);
                    intCharNextChar = (int) charNextChar;
                    if (!(intCharNextChar == 126)) {
                        for (int j = textPos; j < text.length(); j++) {
                            textCharActuel = text.charAt(j);
                            textCharActuelInt = (int) textCharActuel;
                            if (textCharActuelInt == intCharNextChar) {
                                textPos = j;
                                break;
                            } else if (j == text.length() - 1) {
                                match = false;
                            }
                        }
                    }
                }
            }

        }

        /* Si tout s'est bien passé, on MATCH, sinon on FAIL */
        if (match) {
            System.out.println("MATCH");
        } else {
            System.out.println("FAIL");
        }
    }

    /*
     * Cette méthode permet de récupérer le prochain caractère par rapport à la
     * position
     * actuelle
     */
    public static String checkNextChar(int i, String[] format) {
        int formatLength = format.length;

        String nextChar = "";
        if (i < formatLength - 1) {
            nextChar = format[i + 1];
        }
        return nextChar;
    }
}