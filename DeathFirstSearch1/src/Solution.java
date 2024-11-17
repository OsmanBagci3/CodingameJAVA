import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * L'objectif de ce problème consiste à traquer une entité et restreindre ses
 * mouvement afin qu'il ne puisse pas atteindre la sortie. Pour cela,
 * à chaque tour nous pouvons bloquer 1 chemin. L'idée ici est de bloquer chaque
 * chemin en fonction de la position de cet entité.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways

        List<List<Integer>> coupleList = new ArrayList<>(); // initialisation de la liste qui va contenir les couples de noeuds des liens


        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            coupleList.add(new ArrayList<>(List.of(N1, N2))); //ajout des couples de noeuds
        }

        List<Integer> sortieList = new ArrayList<>(); // Liste des index des passerelles de sortie du réseau

        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            sortieList.add(EI); // ajout des index
        }

        String sortie = ""; // Initialisation variable de sortie


        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Bobnet agent is positioned this turn

            sortie = BloquerLien(SI, sortieList, coupleList);

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            System.out.println(sortie);
        }
    }

    /* Cette méthode permet de récupérer un couple d'entier en fonction de la position de l'agent, de la position
    des passerelles de sortie  */
    public static String BloquerLien(int SI, List<Integer> EI, List<List<Integer>> coupleList) {
        StringBuilder sb = new StringBuilder();

        /* Vérifier si l'agent est à 1 déplacement de la sortie. Si c'est le cas
        on bloque immédiatement le lien entre l'agent et la sortie */
        for (List<Integer> couple : coupleList) {
            for (int j = 0; j < EI.size(); j++) {
                if (couple.equals(List.of(SI, EI.get(j))) || couple.equals(List.of(EI.get(j), SI))) {
                    sb.append(String.valueOf(SI));
                    sb.append(" ");
                    sb.append(String.valueOf(EI.get(j)));
                    return sb.toString();
                }
                ;
            }
        }
        /*Si l'agent n'est pas proche d'une sortie, on bloque 1 chemin proche de l'agent*/
        for (List<Integer> couple : coupleList) {
            for (int i = 0; i < couple.size(); i++) {
                if (couple.get(i) == SI) {
                    i = 0;
                    sb.append(String.valueOf(couple.get(i)));
                    sb.append(" ");
                    sb.append(String.valueOf(couple.get(i + 1)));
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }
}