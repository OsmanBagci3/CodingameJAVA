import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

/**
 * Dans ce problème nous cherchons à trouver tous les chemins possible qu'on peut
 * parcourir à partir d'un point de départ, et en comptant le nombre de mouvement
 * en utilisant les chiffres 0 - 9 puis les lettres de l'alphabet A - Z
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int w = in.nextInt();
        int h = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        boolean startingPoint = false;
        String[][] tabs = new String[h][w]; // initialisation d'un tableau représentant le labyrinthe
        int zero = 0;
        int actuel = 48; // correspond à la valeur décimal de "0" dans la table ASCII
        int compteur = 0;
        String s = "";
        char charActuelle = (char) actuel;

        /*On récupère toute les données d'entrée, et on affecte le tout dans un tableau.
        Par ailleurs, on identifie le point de départ en même temps. */
        for (int i = 0; i < h; i++) {
            String ROW = in.nextLine();
            for (int j = 0; j < w; j++) {
                if (!startingPoint) {
                    if (ROW.charAt(j) == 'S') {
                        startingPoint = true;
                        tabs[i][j] = String.valueOf(zero);
                        continue;
                    }
                }
                tabs[i][j] = ROW.substring(j, j + 1);
                if (ROW.charAt(j) == '.') {
                    compteur = compteur + 1;
                }
            }
        }

        boolean depart = false;
        int x0;
        int y0;
        int tentative = 0;
        MyObject myobject = new MyObject(compteur, tabs);
        CoordonneesVoisins myVoisins = new CoordonneesVoisins();

        while (myobject.getCompteur() > 0) { // tant qu'il y a des points atteignables et qui ne sont pas encore traités
            tentative++;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (myobject.getTabs()[i][j].equals("0")) { // position de départ
                        depart = true;
                    }
                    /*On ne travaille pas sur les "." qui sont des cases à remplir à partir de cases connus et "#" représentant des murs du labyrinthe*/
                    if (depart && !(myobject.getTabs()[i][j].equals(".")) && !(myobject.getTabs()[i][j].equals("#"))) {
                        x0 = i;
                        y0 = j;
                        s = myobject.getTabs()[i][j];
                        charActuelle = s.charAt(0); // on converti en char du String actuel
                        actuel = (int) charActuelle;
                        actuel = Actuel(actuel); // Gestion du passage 9 vers A
                        myVoisins = VerifVoisin(tabs, w, h, s); //Recherche de voisins
                        /* S'il existe au moins un voisin non défini, on appelle la méthode "spread"  */
                        if (myVoisins.getVerificationVoisin()) {
                            for (int u = 0; u < myVoisins.getCoordonneesList().size(); u++) {
                                myobject = spread(myobject.getTabs(), myVoisins.getCoordonneesList().get(u).get(0), myVoisins.getCoordonneesList().get(u).get(1), actuel, w, h, myobject.getCompteur());
                            }
                        }
                        myVoisins.setVerificationVoisin(false);
                        myVoisins.getCoordonneesList().clear();
                    }
                }
            }
            /* Gestion du cas où il y a des entités pas encore défini mais que nous nous trouvons bloqué dans le labyrinthe */
            if (tentative > 20) {
                myobject.setCompteur(0);
            }
        }
        tabs = myobject.getTabs();

        /* Construction du labyrinthe pour affichage */
        for (int i = 0; i < h; i++) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder = ArrayToString(tabs[i]);
            s = sBuilder.toString();
            System.out.println(s);
        }
    }

    /* Méthode permettant d'alimenter les voisins */
    public static MyObject spread(String[][] tab, int x0, int y0, int actuel,
                                  int width, int height, int compteur) {
        MyObject myObject = new MyObject(compteur, tab);
        myObject = spreadRight(tab, x0, y0, actuel, width, height, myObject.getCompteur());
        myObject = spreadLeft(tab, x0, y0, actuel, width, height, myObject.getCompteur());
        myObject = spreadUp(tab, x0, y0, actuel, width, height, myObject.getCompteur());
        myObject = spreadDown(tab, x0, y0, actuel, width, height, myObject.getCompteur());
        return myObject;
    }

    /* Sous méthode de Spread */
    public static MyObject spreadRight(String[][] tab, int x0, int y0, int actuel,
                                       int width, int height, int compteur) {
        MyObject myObject = new MyObject(compteur, tab);
        char ValeurActuelle;
        boolean mur = false;
        boolean throughBorders = false;
        if (y0 + 1 == width) {
            if (myObject.getTabs()[x0][0].equals("#")) {
                mur = true;
            }
            if (!mur && myObject.getTabs()[x0][0].equals(".")) {
                throughBorders = true;
            }
            if (throughBorders) {
                actuel = actuel + 1;
                ValeurActuelle = (char) actuel;
                myObject.getTabs()[x0][0] = String.valueOf(ValeurActuelle);
                actuel = Actuel(actuel);
                myObject.setCompteur(myObject.getCompteur() - 1);
                throughBorders = false;
            }
            return myObject;
        }
        if (myObject.getTabs()[x0][y0 + 1].equals("#")) {
            mur = true;
        }
        if (!mur && myObject.getTabs()[x0][y0 + 1].equals(".")) {
            actuel = actuel + 1;
            ValeurActuelle = (char) actuel;
            myObject.getTabs()[x0][y0 + 1] = String.valueOf(ValeurActuelle);
            actuel = Actuel(actuel);
            myObject.setCompteur(myObject.getCompteur() - 1);
        }
        return myObject;
    }

    /* Sous méthode de Spread */
    public static MyObject spreadLeft(String[][] tab, int x0, int y0, int actuel,
                                      int width, int height, int compteur) {
        MyObject myObject = new MyObject(compteur, tab);
        char ValeurActuelle;
        boolean mur = false;
        boolean throughBorders = false;
        if (y0 - 1 == -1) {
            if (myObject.getTabs()[x0][width - 1].equals("#")) {
                mur = true;
            }
            if (!mur && myObject.getTabs()[x0][width - 1].equals(".")) {
                throughBorders = true;
            }
            if (throughBorders) {
                actuel = actuel + 1;
                ValeurActuelle = (char) actuel;
                myObject.getTabs()[x0][width - 1] = String.valueOf(ValeurActuelle);
                actuel = Actuel(actuel);
                myObject.setCompteur(myObject.getCompteur() - 1);
                throughBorders = false;
            }
            return myObject;
        }
        if (myObject.getTabs()[x0][y0 - 1].equals("#")) {
            mur = true;
        }
        if (!mur && myObject.getTabs()[x0][y0 - 1].equals(".")) {
            if (y0 - 1 == 0 && myObject.getTabs()[x0][width - 1].equals(".")) {
                throughBorders = true;
            }
            actuel = actuel + 1;
            ValeurActuelle = (char) actuel;
            myObject.getTabs()[x0][y0 - 1] = String.valueOf(ValeurActuelle);
            actuel = Actuel(actuel);
            myObject.setCompteur(myObject.getCompteur() - 1);
        }
        return myObject;
    }

    /* Sous méthode de Spread */
    public static MyObject spreadUp(String[][] tab, int x0, int y0, int actuel,
                                    int width, int height, int compteur) {
        MyObject myObject = new MyObject(compteur, tab);
        char ValeurActuelle;
        boolean mur = false;
        boolean throughBorders = false;
        if (x0 - 1 == -1) {
            if (myObject.getTabs()[height - 1][y0].equals("#")) {
                mur = true;
            }
            if (!mur && myObject.getTabs()[height - 1][y0].equals(".")) {
                throughBorders = true;
            }
            if (throughBorders) {
                actuel = actuel + 1;
                ValeurActuelle = (char) actuel;
                myObject.getTabs()[height - 1][y0] = String.valueOf(ValeurActuelle);
                actuel = Actuel(actuel);
                myObject.setCompteur(myObject.getCompteur() - 1);
                throughBorders = false;
            }
            return myObject;
        }
        if (tab[x0 - 1][y0].equals("#")) {
            mur = true;
        }
        if (!mur && myObject.getTabs()[x0 - 1][y0].equals(".")) {
            actuel = actuel + 1;
            ValeurActuelle = (char) actuel;
            myObject.getTabs()[x0 - 1][y0] = String.valueOf(ValeurActuelle);
            actuel = Actuel(actuel);
            myObject.setCompteur(myObject.getCompteur() - 1);
        }
        return myObject;
    }

    /* Sous méthode de Spread */
    public static MyObject spreadDown(String[][] tab, int x0, int y0, int actuel,
                                      int width, int height, int compteur) {
        MyObject myObject = new MyObject(compteur, tab);
        char ValeurActuelle;
        boolean mur = false;
        boolean throughBorders = false;
        if (x0 + 1 == height) {
            if (tab[0][y0].equals("#")) {
                mur = true;
            }
            if (!mur && tab[0][y0].equals(".")) {
                throughBorders = true;
            }
            if (throughBorders) {
                actuel = actuel + 1;
                ValeurActuelle = (char) actuel;
                tab[0][y0] = String.valueOf(ValeurActuelle);
                actuel = Actuel(actuel);
                myObject.setCompteur(myObject.getCompteur() - 1);
                throughBorders = false;
            }
            return myObject;
        }
        if (tab[x0 + 1][y0].equals("#")) {
            mur = true;
        }
        if (!mur && tab[x0 + 1][y0].equals(".")) {
            actuel = actuel + 1;
            ValeurActuelle = (char) actuel;
            tab[x0 + 1][y0] = String.valueOf(ValeurActuelle);
            actuel = Actuel(actuel);
            myObject.setCompteur(myObject.getCompteur() - 1);
        }
        return myObject;
    }

    /* StringBuilder pour un tableau */
    public static StringBuilder ArrayToString(String[] tab) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : tab) {
            stringBuilder.append(s);
        }
        return stringBuilder;
    }

    /* Méthode permettant de passer du chiffre 9 à A */
    public static int Actuel(int actuel) {
        actuel = actuel == 57 ? 64 : actuel;
        return actuel;
    }

    /* cette méthode permet de vérifier qu'au point où on se trouve il existe bien des voisins définis par "." */
    public static CoordonneesVoisins VerifVoisin(String[][] tab, int w, int h, String charPrincipal) {
        CoordonneesVoisins voisin = new CoordonneesVoisins();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (tab[i][j].equals(charPrincipal)) {
                    voisin.setCoordonneesList(i, j);
                    if (i == 0) {
                        if (tab[h - 1][j].equals(".") || tab[i + 1][j].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        } else if (!(j == 0) && !(j == w - 1)) {
                            if (tab[i][j - 1].equals(".") || tab[i][j + 1].equals(".")) {
                                voisin.setVerificationVoisin(true);
                            }
                        }
                    } else if (j == 0) {
                        if (tab[i][w - 1].equals(".") || tab[i][j + 1].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        } else if (!(i == h - 1)) {
                            if (tab[i - 1][j].equals(".") || tab[i + 1][j].equals(".")) {
                                voisin.setVerificationVoisin(true);
                            }
                        }
                    } else if (j == (w - 1)) {
                        if (tab[i][0].equals(".") || tab[i][j - 1].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        } else if (!(i == h - 1)) {
                            if (tab[i - 1][j].equals(".") || tab[i + 1][j].equals(".")) {
                                voisin.setVerificationVoisin(true);
                            }
                        }
                    } else if (i == (h - 1)) {
                        if (tab[0][j].equals(".") || tab[i - 1][j].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        } else if (!(j == w - 1)) {
                            if (tab[i][j - 1].equals(".") || tab[i][j + 1].equals(".")) {
                                voisin.setVerificationVoisin(true);
                            }
                        }
                    } else if (i > 0 && i < h - 1 && j > 0 && j < w - 1) {
                        if (tab[i][j - 1].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        }
                        if (tab[i][j + 1].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        }
                        if (tab[i + 1][j].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        }
                        if (tab[i - 1][j].equals(".")) {
                            voisin.setVerificationVoisin(true);
                        }
                    }

                }
            }
        }
        return voisin;
    }


}

/*Création d'une classe MyObject qui va être mis à jour souvent*/
class MyObject {
    private int compteur;
    private String[][] tabs;

    public MyObject(int compteur, String[][] tabs) {
        this.compteur = compteur;
        this.tabs = tabs;
    }

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int c) {
        this.compteur = c;
    }

    public String[][] getTabs() {
        return tabs;
    }

    public void setTabs(String[][] t) {
        this.tabs = t;
    }


}

class CoordonneesVoisins {
    private boolean verificationVoisin;
    private final List<List<Integer>> coordonneesList = new ArrayList<>();

    public boolean getVerificationVoisin() {
        return verificationVoisin;
    }

    public void setVerificationVoisin(boolean b) {
        this.verificationVoisin = b;
    }

    public List<List<Integer>> getCoordonneesList() {
        return coordonneesList;
    }

    public void setCoordonneesList(int x, int y) {
        List<Integer> tempList = new ArrayList<>();
        tempList.add(x);
        tempList.add(y);
        this.coordonneesList.add(tempList);
    }
}