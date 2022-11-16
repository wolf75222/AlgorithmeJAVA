import java.lang.Math;

public class AlgoTriv2 {

    public static void main(String[] args) {
       
        /* 
         * "Dans les affichages j'en fait un affichetab(t) qui renvoie par exemple : 1,5,7,9.......11,47,72,.........250,256,260"
         * Je n'ai pas compris ce que vous vouliez dire
         */


        // Pour voir si les fonctions marchent
        test(10, 0, 10,1);
        test(10, 0, 10,2);
        test(10, 0, 10,3);


        //Pour voir les temps d'execution
        grosTest(100000, 0, 100000,1);
        grosTest(100000, 0, 100000,2);
        grosTest(100000, 0, 100000,3);
        

        int [] tab = new int[1000000000];
        remplir(tab, 0, 100000);
        testRecherche(tab, 10579968);

    }
    
    public static void triSelection(int[] tab) {
    /* 
     * Algorithme de tri par sélection
     */
        int i, j, min;
        for (i = 0; i < tab.length - 1; i++) {
            min = i;
            for (j = i + 1; j < tab.length; j++) {
                if (tab[j] < tab[min]) {
                    min = j;
                }
            }
            echange(tab, i, min);
        }
    }


    public static void echange(int[] tab, int i, int j){
        /*
         * échange les éléments d'indice i et j du tableau tab
         */
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public static void triInsertion(int[] tab) {
    /* 
     * Algorithme de tri par insertion
     */
        int i, j, tmp;
        for (i = 1; i < tab.length; i++) {
            tmp = tab[i];
            j = i;
            while (j > 0 && tab[j - 1] > tmp) {
                tab[j] = tab[j - 1];
                j--;
            }
            tab[j] = tmp;
        }
    }
    
    public static void triDrapeau(int[] tab) {
    /* 
     * Algorithme de tri par drapeau
     */
        int i, j, tmp;
        for (i = 0; i < tab.length - 1; i++) {
            if (tab[i] > tab[i + 1]) {
                tmp = tab[i];
                tab[i] = tab[i + 1];
                tab[i + 1] = tmp;
                j = i;
                while (j > 0 && tab[j - 1] > tab[j]) {
                    tmp = tab[j];
                    tab[j] = tab[j - 1];
                    tab[j - 1] = tmp;
                    j--;
                }
            }
        }
    }
    
    public static int aleatoire(int min, int max) {
    /* 
     * Retourne un entier aléatoire compris entre min et max
     */
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static int[] remplir(int[] tab, int min, int max) {
    /* 
     * Remplit un tableau d'entiers aléatoires compris entre min et max
     */
        for (int i = 0; i < tab.length; i++) {
            tab[i] = aleatoire(min, max);
        }
        return tab;
    }

    public static void afficher(int[] tab) {
    /* 
     * Affiche un tableau d'entiers
     */
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }

    public static boolean estTrie(int[] tab) {
    /* 
     * Vérifie si un tableau est trié
     */
        for (int i = 0; i < tab.length - 1; i++) {
            if (tab[i] > tab[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void trier(int[] tab, int algo) {
    /* 
     * Trie un tableau d'entiers selon l'algorithme spécifié
     */
        //constante
        final int SELECTION = 1;
        final int INSERTION = 2;
        final int DRAPEAU = 3;

        long debut = System.currentTimeMillis();
        if (algo == SELECTION) { 
            triSelection(tab);
        } else if (algo == INSERTION) {
            triInsertion(tab);
        } else if (algo == DRAPEAU)  {
            triDrapeau(tab);
        }
        long fin = System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (fin - debut) + " ms");
    }

    public static void test(int taille, int min, int max, int algo) {
    /* 
     * Teste l'algorithme spécifié sur un tableau d'entiers aléatoires
     */
        int[] tab = new int[taille];
        remplir(tab, min, max);
        System.out.println("Tableau non trié :");
        afficher(tab);
        trier(tab, algo);
        System.out.println("Tableau trié avec "+ algo + ": ");
        afficher(tab);
        System.out.println("Est trié : " + estTrie(tab));
        System.out.println("----------------------");
    }

    public static void grosTest(int taille, int min, int max, int algo) {
    /* 
    * Teste l'algorithme spécifié sur un tableau d'entiers aléatoires, sans affichage
    */
        int[] tab = new int[taille];
        remplir(tab, min, max);
        trier(tab, algo);
        System.out.println("Tableau trié avec "+ algo);
        System.out.println("Est trié : " + estTrie(tab));
        System.out.println("----------------------");
    }

    public static void testRecherche(int[] tab, int val){
        /*
         * Teste les fonctions de recherche
         */
        long debut = System.currentTimeMillis();
        System.out.println(rechercheDicho(tab, val));
        long fin = System.currentTimeMillis();
        System.out.println("Temps d'exécution de la recherche dichotomique : " + (fin - debut) + " ms");
        debut = System.currentTimeMillis();
        System.out.println(rechercheSeq(tab, val));
        fin = System.currentTimeMillis();
        System.out.println("Temps d'exécution de la recherche Séquentielle : " + (fin - debut) + " ms");
        System.out.println("----------------------");
    }




    public static int rechercheSeq(int[] tab, int val) {
        /*
        * Recherche séquentielle
        */
            for (int i = 0; i < tab.length; i++) {
                if (tab[i] == val) {
                    return i;
                }
            }
            return -1;
        }
    
    public static int rechercheDicho(int[] tab, int val) {
    /*
    * Recherche dichotomique
    */
        int min = 0;
        int max = tab.length - 1;
        int milieu;
        while (min <= max) {
            milieu = (min + max) / 2;
            if (tab[milieu] == val) {
                return milieu;
            } else if (tab[milieu] < val) {
                min = milieu + 1;
            } else {
                max = milieu - 1;
            }
        }
        return -1;
    }

}