import java.lang.Math;

public class AlgoTri {

    public static void main(String[] args) {
       
        // Pour voir si les fonctions marchent
        test(10, 0, 10,"selection");
        test(10, 0, 10,"insertion");
        test(10, 0, 10,"drapeau");

        System.out.println("----------------------");

        //Pour voir les temps d'execution
        grosTest(100000, 0, 100000,"selection");
        grosTest(100000, 0, 100000,"insertion");
        grosTest(100000, 0, 100000,"drapeau");

    }
    
    public static void triSelection(int[] tab) {
    /* 
     * Algorithme de tri par sélection
     */
        int i, j, min, tmp;
        for (i = 0; i < tab.length - 1; i++) {
            min = i;
            for (j = i + 1; j < tab.length; j++) {
                if (tab[j] < tab[min]) {
                    min = j;
                }
            }
            tmp = tab[i];
            tab[i] = tab[min];
            tab[min] = tmp;
        }
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

    public static void trier(int[] tab, String algo) {
    /* 
     * Trie un tableau d'entiers selon l'algorithme spécifié
     */
        long debut = System.currentTimeMillis();
        if (algo.equals("selection")) {
            triSelection(tab);
        } else if (algo.equals("insertion")) {
            triInsertion(tab);
        } else if (algo.equals("drapeau")) {
            triDrapeau(tab);
        }
        long fin = System.currentTimeMillis();
        System.out.println("Temps d'exécution : " + (fin - debut) + " ms");
    }

    public static void test(int taille, int min, int max, String algo) {
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
    }

    public static void grosTest(int taille, int min, int max, String algo) {
    /* 
    * Teste l'algorithme spécifié sur un tableau d'entiers aléatoires, sans affichage
    */
        int[] tab = new int[taille];
        remplir(tab, min, max);
        trier(tab, algo);
        System.out.println("Tableau trié avec "+ algo);
        System.out.println("Est trié : " + estTrie(tab));
    }

}