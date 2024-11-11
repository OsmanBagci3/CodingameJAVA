import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 6 Degrees of Kevin Bacon!
 * <p>
 * Le but dans ce problème c'est de faire la correspondance entre l'acteur Kevin
 * Bacon
 * et l'acteur désigné, à travers les films dans lesquelles ils ont joué, et de
 * retrouver
 * le niveau de correspondance
 **/

/**
 * Le but dans ce problème c'est de faire la correspondance entre l'acteur Kevin
 * Bacon
 * et l'acteur désigné, à travers les films dans lesquelles ils ont joué, et de
 * retrouver
 * le niveau de correspondance
 **/

/**
 * La solution proposée ici fonctionne à 100% mais ici le code a été écrit de façon brut
 * il existe des moyens de rendre le code beaucoup plus propre.
 * **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String actorName = in.nextLine();

        int n = in.nextInt();
        int baconNumber = 1; // initialisation de la variable de sortie. Il est
        List<List<String>> movieCastListWithBacon = new ArrayList<List<String>>(); //List films avec Kevin Bacon
        List<List<String>> movieCastListWithoutBacon = new ArrayList<List<String>>(); // List films sans Kevin Bacon
        List<String> movieCastCurrentList = new ArrayList<String>(); // initialisation list actuelle
        List<String> movieCastOlderList = new ArrayList<String>(); // initialisation list temporaire
        List<String> tempList = new ArrayList<String>(); // initialisation 2nd list temporaire
        List<Integer> listANePasParcourir = new ArrayList<>(); // Initialisation list déjà parcouru, afin d'éviter les doublons

        int k = 0;
        boolean baconFind = false;

        if (in.hasNextLine()) {
            in.nextLine();
        }

        /*Recherche des list de films/acteur dont Kevin Bacon fait partie et ceux dont il en fait pas partie */
        for (int i = 0; i < n; i++) {
            String movieCast = in.nextLine();
            String[] splittedMovieCast = movieCast.split(":|,");
            k = 0;
            /*parcours de chacune des listes */
            for (int j = 0; j < splittedMovieCast.length; j++) {
                if (splittedMovieCast[j].contains("Kevin Bacon")) {
                    movieCastListWithBacon.add(List.of(splittedMovieCast));
                    k++;
                    break;
                } else if (j == splittedMovieCast.length - 1) { //Rentre dans la boucle tant que nous n'avons pas matché avec "Kevin Bacon"
                    movieCastListWithoutBacon.add(List.of(splittedMovieCast));
                }
            }
        }

        k = 0;
        /*On compare les list où on a "Kevin Bacon" et ceux qui n'en ont pas et on cherche des correspondance entre les acteurs */
        for (int i = 0; i < movieCastListWithBacon.size(); i++) {
            tempList.clear();
            tempList.addAll(movieCastListWithBacon.get(i));
            for (int j = 0; j < movieCastListWithoutBacon.size(); j++) {
                tempList.retainAll(movieCastListWithoutBacon.get(j));
                if (!tempList.isEmpty()) { // si la taille de tempList est > 0 cela veut dire qu'il y a au moins une correspondance
                    k = k + 1;
                    break;
                } else {
                    tempList.addAll(movieCastListWithBacon.get(i));
                }
                if (j == (movieCastListWithoutBacon.size() - 1)) {
                    movieCastListWithBacon.remove(i); // Si il y a aucune correspondance avec aucune list, on supprime cet élémént
                    i--;
                }
            }
        }
        k = 0;
        int listIndex = 0;
        boolean actorFind = false;
        /* On traite le cas où on cherche la correspondance entre kevin Bacon et lui même donc 0 */
        if (actorName.contains("Kevin Bacon")) {
            baconNumber--;
            System.out.println(baconNumber);
        }

        /* Traitement des cas où on cherche la correspondance entre K.B et un autre acteur */
        if (baconNumber > 0) {
            if (movieCastListWithoutBacon.isEmpty()) {
                System.out.println(baconNumber); // 1 si nous avons aucune list sans K.B dedans
            } else {
                for (List<String> list : movieCastListWithBacon) {
                    for (String s : list) {
                        if (s.contains(actorName)) {
                            System.out.println(baconNumber);
                            baconFind = true;
                            break;
                        }
                    }
                    if (baconFind) {
                        break;
                    }
                }
            }
        }

        /*Cas où K.B et l'acteur recherché on aucune correspondance, dans ce cas nous devons mettre à jour l'acteur
        et faire les correspondances jusqu'à ce qu'on tombe sur K.B */
        if (!baconFind) {
            /* On doit d'abord trouver la ou les list qui correspondent à l"acteur recherché */
            for (List<String> list : movieCastListWithoutBacon) {
                for (String s : list) {
                    if (s.contains(actorName)) {
                        movieCastCurrentList = list;
                        actorFind = true;
                        listIndex = movieCastListWithoutBacon.indexOf(list);
                        break;
                    }
                }
                if (actorFind) {
                    baconNumber++;
                    break;
                }
            }

            listANePasParcourir.add(listIndex); // Après avoir trouvé la list de l'acteur concernée, nous n'avons plus besoin de le reparcourir

            actorFind = false;

            // + de 1 degree of Kevin Bacon
            if (baconNumber > 1) {
                for (int i = 0; i < movieCastListWithBacon.size(); i++) {
                    tempList.clear();
                    tempList.addAll(movieCastListWithBacon.get(i));
                    tempList.retainAll(movieCastCurrentList);
                    if (!tempList.isEmpty()) {
                        System.out.println(baconNumber);
                        break;
                    }
                    // baconNumber > 2
                    if (i == movieCastListWithBacon.size() - 1) {
                        baconNumber++;
                    }
                }
            }

            String pivotActeur = "";

            /* Cas où nous avons au minimum 3 correspondance */
            if (baconNumber > 2) {
                while (!baconFind) { // on effectue la recherche tant que cela ne match pas
                    for (int i = 0; i < movieCastListWithoutBacon.size(); i++) {
                        if (listANePasParcourir.contains(i)) {
                            continue;
                        }
                        tempList.clear();
                        tempList.addAll(movieCastListWithoutBacon.get(i));
                        tempList.retainAll(movieCastCurrentList);
                        if (!tempList.isEmpty()) {
                            pivotActeur = tempList.getFirst();
                            movieCastOlderList.addAll(movieCastCurrentList);
                            movieCastCurrentList = movieCastListWithoutBacon.get(i);
                            listIndex = i;
                            listANePasParcourir.add(listIndex); // mise à jour de la list à une plus parcourir
                            i = 0;
                            tempList.clear();
                            break;
                        }
                        /* Comme on démarre de la list où notre acteur se trouve, si on arrive à la fin de la list 
                        sans avoir pu faire toute les correspondances, il faut parcourir la première partie des list 
                        sans K.B */
                        if (i == movieCastListWithoutBacon.size() - 1) {
                            movieCastCurrentList = movieCastOlderList;
                            i = 0;
                            baconNumber--;
                        }
                    }
                    for (int i = 0; i < movieCastListWithBacon.size(); i++) {
                        tempList.addAll(movieCastListWithBacon.get(i));
                        tempList.retainAll(movieCastCurrentList);
                        if (!tempList.isEmpty()) {
                            baconFind = true;
                            System.out.println(baconNumber);
                            break;
                        }
                        if (i == movieCastListWithBacon.size() - 1) {
                            baconNumber++; // incrémentation tant qu'on ne trouve pas la dernière correspondance
                        }
                    }
                }
            }
        }
        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
    }
}