import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * Un mot kangourou est « un mot qui contient son propre synonyme,
 * avec les lettres pour épeler ce synonyme déjà placées dans le bon ordre »
 * L'objectif de ce problème consiste à trouver des mots kangourou, ainsi que leurs synonymes, en fonction
 * d'une liste de mot donnée en entrée. Si on a plusieurs mots kangourou, on affiche le résultat par ordre
 * alphabétique.
 **/

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        List<String> resultList = new ArrayList<>(); //initialisation de la liste qui va contenir les mots kangourou et leur(s) synonyme(s)
        boolean existingKangaroo = false;
        List<List<String>> wordList = new ArrayList<>(); // liste 2D qui va contenir les groupes de mots
        for (int i = 0; i < N; i++) {
            String LINES = in.nextLine();
            String[] tabWord = LINES.split(", "); // découpage des données d'entrée
            wordList.add(AddTableElement(tabWord)); // ajout dans la liste 2D
        }
        StringBuilder sb = new StringBuilder(); // initialisation d'un StringBuilder qui va nous permettre de faire un affichage sous la forme : Kangaroo word: First joey, /.../ Last joey

        /* on parcourt chacune des listes de notre liste 2D. Pour chaque groupe nous aurons au moins 1 ligne,
        sous condition qu'il y ait au moins un mot kangourou et un synonyme*/
        for (List<String> strings : wordList) {
            for (int i = 0; i < strings.size(); i++) {
                String word1 = strings.get(i);
                sb.setLength(0);
                for (int j = 0; j < strings.size(); j++) {
                    /*Dans cette boucle on compare chaque mot pour voir si ils ont des synonymes */
                    if (!(i == j)) { // on évite la comparaison avec soit-même
                        String word2 = strings.get(j);
                        if (word1.length() >= word2.length()) {
                            if (CompareWords(word1, word2)) { //appel de la fonction CompareWords
                                existingKangaroo = true;
                                if (sb.isEmpty()) {
                                    sb.append(word1).append(":").append(" ").append(word2);
                                } else {
                                    sb.append(", ").append(word2);
                                }
                            }
                            ;
                        }
                    }
                }
                if (existingKangaroo) { //on écrit dans notre liste résultat uniquement si on a trouvé des synonymes
                    resultList.add(sb.toString());
                    existingKangaroo = false;
                }
            }
        }

        Collections.sort(resultList); // Tri de la liste de sortie par ordre alphabétique

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");

        //Affiche NONE si on trouve aucun synonyme
        if (resultList.isEmpty()) {
            System.out.println("NONE");
        }
        for (String s : resultList) {
            System.out.println(s);
        }

    }

    /* Cette fonction permet d'ajouter les éléments d'un tableau dans une liste et de renvoyer cette liste */
    public static List<String> AddTableElement(String[] table) {
        List<String> liste = new ArrayList<>();
        Collections.addAll(liste, table);
        return liste;
    }

    /* Cette méthode permet de comparer 2 mots et de renvoyer vrai si le second mot est contenu dans le premier */
    public static boolean CompareWords(String longer, String shorter) {
        boolean notAkangarooWord = false;
        int k = 0;

        for (int i = 0; i < shorter.length(); i++) {
            if (k == longer.length()) {
                notAkangarooWord = true;
                break;
            }
            char c = shorter.charAt(i);
            if (!notAkangarooWord) {
                for (int j = k; j < longer.length(); j++) {
                    k++;
                    if (Objects.equals(c, longer.charAt(j))) {
                        break;
                    }
                    if (k == longer.length()) {
                        notAkangarooWord = true;
                    }
                }
            } else {
                break;
            }
        }

        if (!notAkangarooWord) {
            return true;
        } else {
            return false;
        }
    }
}