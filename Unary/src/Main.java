import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * Le but du jeu est de coder un message uniquement avec des 0, suivant
 * certaine règle
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String MESSAGE = in.nextLine();
        char[] toChar = MESSAGE.toCharArray(); // conversion du message en char
        String binaryValue = "";
        int asciiVal;

        // Récupération de la valeur binaire du message
        for (int j = 0; j < toChar.length; j++) {
            asciiVal = (int) toChar[j];

            if (asciiVal >= 32 && asciiVal < 65) {
                binaryValue = binaryValue + "0" + Integer.toBinaryString(toChar[j]);
            } else {
                binaryValue = binaryValue + Integer.toBinaryString(toChar[j]);
            }
        }

        String[] splittedBinary = binaryValue.split("");// Création
        // tableau pour un traitement cas par cas

        String currentChar = splittedBinary[0]; //traitement du début de la chaine
        String space = " ";
        String result = "";
        if (currentChar.equals("1")) {
            result = "0" + space;
        } else {
            result = "00" + space;
        }

        //Traitement du reste de la chaine
        for (int i = 0; i < splittedBinary.length; i++) {
            while (splittedBinary[i].equals(currentChar)) {
                result = result + "0";
                if (i + 1 < splittedBinary.length) {
                    i++;
                } else {
                    break;
                }
            }

            //traitement du cas du bout de la chaine
            if (i + 1 == splittedBinary.length && splittedBinary[i].equals(currentChar)) {
                break;
            }
            currentChar = splittedBinary[i];
            if (currentChar.equals("1")) {
                result = result + space + "0" + space + "0";
            } else {
                result = result + space + "00" + space + "0";
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        System.out.println(result);

    }
}