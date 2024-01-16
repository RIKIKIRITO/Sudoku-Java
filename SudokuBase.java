import java.util.*;
import java.lang.*;

public class SudokuBase {

    //.........................................................................
    // Fonctions utile
    //.........................................................................


    /** pré-requis : min <= max
     *  résultat :   un entier saisi compris entre min et max, avec re-saisie éventuelle jusqu'à ce qu'il le soit
     */
    public static int saisirEntierMinMax(int min, int max){
	//________________________________________________________
        System.out.println("saisisez une valeur comprise entre " + min + "et " + max );
        int valeur = Ut.saisirEntier();
        if (valeur < min || valeur > max){
            Ut.afficher("saisisé une valeur comprise entre " + min + " et" + max );
            valeur = Ut.saisirEntier();
        }
     return valeur;


    }  // fin saisirEntierMinMax
    //.........................................................................

    /** MODIFICI
     *  pré-requis : mat1 et mat2 ont les mêmes dimensions
     *  action : copie toutes les valeurs de mat1 dans mat2 de sorte que mat1 et mat2 soient identiques
     */
    public static void copieMatrice(int[][] mat1, int[][] mat2){
        //________________________________________________________
            for (int x=0; x < mat1.length; x++){
                for (int y= 0; y< mat1[0].length; y++){
                    mat2[x][y] = mat1[x][y];
                }
            }
    }  // fin copieMatrice

    //.........................................................................


    /** pré-requis :  n >= 0
     *  résultat : un tableau de booléens représentant le sous-ensemble de l'ensemble des entiers 
     *             de 1 à n égal à lui-même 
     */
    public static boolean[] ensPlein(int n){
	//_____________________________________
        boolean [] tab = new boolean[n+1];
        for (int i = 0; i < tab.length  ; i++){
            tab[i]= true;
        }
        return tab;
    }  // fin ensPlein

    //.........................................................................


    /** pré-requis : 1 <= val < ens.length
     *  action :     supprime la valeur val de l'ensemble représenté par ens, s'il y est
     *  résultat :   vrai ssi val était dans cet ensemble
     */
    public static boolean supprime(boolean[] ens, int val){
	//______
    boolean b=false;
    if (ens[val]){
        b=true;
        ens[val]=false;
    }
    return b;
    }  // fin supprime

    //.........................................................................


    /** pré-requis : l'ensemble représenté par ens n'est pas vide
     *  résultat :   un élément de cet ensemble
     */
    public static int uneValeur(boolean[] ens){
	//_____________________________________________
        for (int i = 1; i < ens.length; i++){
            if (ens[i]){
                return i;
            }
        }
        return -1;
    }  // fin uneValeur

    //.........................................................................

    /**
 
       1 2 3 4 5 6 7 8 9
       ------------------- 
       1 |6 2 9|7 8 1|3 4 5|
       2 |4 7 3|9 6 5|8 1 2|
       3 |8 1 5|2 4 3|6 9 7|
       ------------------- 
       4 |9 5 8|3 1 2|4 7 6|
       5 |7 3 2|4 5 6|1 8 9|
       6| 1 6 4|8 7 9|2 5 3|
       ------------------- 
       7 3 8 1|5 2 7|9 6 4
       8 |5 9 6|1 3 4|7 2 8|
       9 |2 4 7|6 9 8|5 3 1|
       ------------------- 

 
       1 2 3 4 5 6 7 8 9
       ------------------- 
       1 |6 0 0|0 0 1|0 4 0|
       2 |0 0 0|9 6 5|0 1 2|
       3 |8 1 0|0 4 0|0 0 0|
       ------------------- 
       4 |0 5 0|3 0 2|0 7 0|
       5 |7 0 0|0 0 0|1 8 9|
       6||0 0 0|0 7 0|0 0 3|
       ------------------- 
       7 |3 0 0|0 2 0|9 0 4|
       8 |0 9 0|0 0 0|7 2 0|
       9 |2 4 0|6 9 0|0 0 0|
       ------------------- 


       * pré-requis : 0<=k<=3 et g est une grille k^2xk^2 dont les valeurs sont comprises 
       *              entre 0 et k^2 et qui est partitionnée en k sous-carrés kxk
       * action : affiche la  grille g avec ses sous-carrés et avec les numéros des lignes 
       *          et des colonnes de 1 à k^2.
       * Par exemple, pour k = 3, on obtient le dessin d'une grille de Sudoku
       *  
       */
    public static void afficheGrille(int k,int[][] g){
        //__________________________________________________
        int nb=1;
        Ut.clearConsole();
        String chaine="————";
        Ut.afficher("X | ");
        for(int i=1;i<=g.length;i++){
            if (i%k==0){
                Ut.afficher(i+" | ");
                chaine+="————";
            }
            else {
                Ut.afficher(i+" ");
                chaine+="——";
            }
        }
        Ut.sautLigne();
        Ut.afficher(chaine);
        Ut.sautLigne();
        for(int i=0;i<g.length;i++){
            for(int j=0; j<g.length; j++){
                if(j==0){
                    if(k>1) {
                        Ut.afficher(nb + " | " + g[i][j] + " ");
                        nb++;
                    }
                    else {
                        Ut.afficher(nb + " | " + g[i][j] + " | ");
                        nb++;
                    }
                }
                else if((j+1)%k==0){
                    Ut.afficher(g[i][j]+" | ");
                }
                else {
                    Ut.afficher(g[i][j]+" ");
                }
            }
            if ((i+1)%k==0){
                Ut.sautLigne();
                Ut.afficher(chaine);
            }
            Ut.sautLigne();
        }
    } // fin afficheGrille
    //.........................................................................

    /** pré-requis : k > 0, 0 <= i< k^2 et 0 <= j < k^2
     *  résultat : (i,j) étant les coordonnées d'une case d'une grille k^2xk^2 partitionnée 
     *             en k sous-carrés kxk, retourne les coordonnées de la case du haut à gauche
     *             du sous-carré de la grille contenant cette case.
     *  Par exemple, si k=3, i=5 et j=7, la fonction retourne (3,6).
     */
    public static int[] debCarre(int k,int i,int j){
	//__________________________________________________
    return new int[]{(i/k) * k, (j/k) * k};
    }  // fin debCarre


    //.........................................................................

    // Initialisation
    //.........................................................................


    /** pré-requis : gComplete est une matrice 9X9
     *  action   :   remplit gComplete pour que la grille de Sudoku correspondante soit complète
     *  stratégie :  les valeurs sont données directement dans le code et on peut utiliser copieMatrice pour mettre à jour gComplete
     */
    public static void initGrilleComplete(int [][] gComplete){
        //________________________________________________
        int [][] sudoku = {
                {6,2,9,7,8,1,3,4,5},
                {4,7,3,9,6,5,8,1,2},
                {8,1,5,2,4,3,6,9,7},
                {9,5,8,3,1,2,4,7,6},
                {7,3,2,4,5,6,1,8,9},
                {1,6,4,8,7,9,2,5,3},
                {3,8,1,5,2,7,9,6,4},
                {5,9,6,1,3,4,7,2,8},
                {2,4,7,6,9,8,5,3,1}
        };
        copieMatrice( sudoku, gComplete);




    } // fin initGrilleComplete

    //.........................................................................

    /** MODIFICI
     *  pré-requis : gSecret est une grille de Sudoku complète de mêmes dimensions que gIncomplete et 0 <= nbTrous <= 81
     *  action :     modifie gIncomplete pour qu'elle corresponde à une version incomplète de la grille de Sudoku gSecret (gIncomplete peut être complétée en gSecret),
     *               avec nbTrous trous à des positions aléatoires
     */
    public static void initGrilleIncomplete(int nbTrous, int [][] gSecret, int[][] gIncomplete){
	//___________________________________________________________________________
        for ( int i =0 ; i< gSecret.length ; i++) {          // initialiser la grille incomplète
            for ( int j = 0 ; j<gSecret[0].length ; j++) {
                gIncomplete[i][j] = gSecret[i][j];
            }
        }
        Random random= new Random();                          // créer nouvel objet pour générer des nombres aléatoires
        while ( nbTrous>0) {
            int randomI= random.nextInt(gIncomplete.length);
            int randomJ= random.nextInt(gIncomplete[0].length);
            if ( gIncomplete[randomI][randomJ] != 0) {
                gIncomplete[randomI][randomJ] = 0 ;
                nbTrous --;
            }
        }

    } // fin initGrilleIncomplete

    //.........................................................................


    /** pré-requis : 0 <= nbTrous <= 81
     *  résultat :   une grille  9x9 saisie dont les valeurs sont comprises ente 0 et 9
     *               avec exactement  nbTrous valeurs nulles
     *               et avec re-saisie jusqu'à ce que ces conditions soient vérifiées.
     *               On suppose dans la version de base que la grille saisie est bien une grille de Sudoku incomplète.
     *  stratégie : utilise la fonction saisirEntierMinMax
     */
    public static void saisirGrilleIncomplete(int nbTrous, int [][]g){
	//_________________________________________________
        int compt = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (compt ==nbTrous){
                    g[i][j]= saisirEntierMinMax(1,9);
                }
                else{
                    g[i][j] = saisirEntierMinMax(0,9);
                    if (g[i][j] == 0){
                        compt ++;
                    }
                }
                afficheGrille(3,g);
            }
        }
    }  // fin saisirGrilleIncomplete

    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque trou de gOrdi
     *           et leur nombre dans nbValPoss       
     */
    public static void initPleines(int [][] gOrdi, boolean[][][] valPossibles, int [][] nbValPoss){
	//________________________________________________________________________________________________
        for (int i=0; i< gOrdi.length; i++){
            for(int j=0; j<gOrdi[0].length; j++){
                if (gOrdi[i][j]==0) {
                    nbValPoss[i][j] = 9;
                        valPossibles[i][j]=ensPlein(9);
                }
            }
        }
    }  // fin initPleines

    //.........................................................................


    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               0<=i<9, 0<=j<9,g[i][j]>0,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : supprime dans les matrices valPossibles et nbValPoss la valeur gOrdi[i][j] pour chaque case de la ligne,
     *           de la colonne et du carré contenant la case (i,j) correspondant à un trou de gOrdi.
     */
    public static void suppValPoss(int [][] gOrdi, int i, int j, boolean[][][] valPossibles, int [][]nbValPoss){
	//_____________________________________________________________________________________________________________
        for (int x=0; x< gOrdi.length;x++){
            if(supprime(valPossibles[x][j],gOrdi[i][j])){
                nbValPoss[x][j]--;
            }
        }
        for (int y=0; y< gOrdi.length;y++){
            if(supprime(valPossibles[i][y],gOrdi[i][j])){
                nbValPoss[i][y]--;
            }
        }
        for (int x=debCarre(3,i,j)[0];x<debCarre(3,i,j)[0]+3;x++){
            for (int y=debCarre(3,i,j)[1];y<debCarre(3,i,j)[1]+3;y++){
                if(supprime(valPossibles[x][y],gOrdi[i][j])){
                    nbValPoss[x][y]--;
                }
            }
        }
    }  // fin suppValPoss


    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     * action :      met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *               et leur nombre dans nbValPoss       
     */
    public static void initPossibles(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
	//________________________________________________________________________________________________
        initPleines(gOrdi, valPossibles,nbValPoss);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gOrdi[i][j] > 0) {
                    suppValPoss(gOrdi, i, j,valPossibles, nbValPoss);
                }
            }
        }
    }  // fin initPossibles

    //.........................................................................


    /** pré-requis : gSecret, gHumain et gOrdi sont des grilles 9x9
     *  action :     demande au joueur humain de saisir le nombre nbTrous compris entre 0 et 81,
     *               met dans gSecret une grille de Sudoku complète,
     *               met dans gHumain une grille de Sudoku incomplète, pouvant être complétée en gSecret
     *               et ayant exactement nbTrous trous de positions aléatoires,
     *               met dans gOrdi une grille de Sudoku incomplète saisie par le joueur humain
     *               ayant  nbTrous trous,
     *               met dans valPossibles l'ensemble des valeurs possibles de chaque trou de gOrdi
     *               et leur nombre dans nbValPoss.
     * retour : la valeur de nbTrous
     */
    public static int initPartie(int [][] gSecret, int [][] gHumain, int [][] gOrdi,
				 boolean[][][] valPossibles, int [][]nbValPoss){
	//______________________________________________________________________________________________
        Ut.afficherSL("nombre de troue que vous voulez pour votre grille");
        int nbTrous = saisirEntierMinMax(0,81);            // joueur humain saisit nbTrous entre 0 et 81
        initGrilleComplete(gSecret);                       // gSecret est une grille de Sudoku complète
        initGrilleIncomplete(nbTrous,gSecret,gHumain);     // gHumain grille incomplète pouvant etre complétée en gSecret avec nbTrous aléatoires
        initGrilleIncomplete(nbTrous,gSecret,gOrdi);
        //saisirGrilleIncomplete( nbTrous,gOrdi);           // gOrdi grille incomplète avec nbTrous
        initPossibles(gOrdi,valPossibles,nbValPoss);     //  ensembles valeurs possibles de chaque trous et nbValPoss
        return nbTrous;
    }  // fin initPartie

    //...........................................................
    // Tour du joueur humain
    //...........................................................

    /** pré-requis : gHumain est une grille de Sudoju incomplète pouvant se compléter en 
     *               la  grille de Sudoku complète gSecret
     *
     *  résultat :   le nombre de points de pénalité pris par le joueur humain pendant le tour de jeu
     *
     *  action :     effectue un tour du joueur humain   
     */
    public static int tourHumain(int [][] gSecret, int [][] gHumain){
	//___________________________________________________________________
        int malus=0;
        int i;
        int j;
        do {
            Ut.sautLigne();
            afficheGrille(3, gHumain);
            Ut.afficher("voulez-vous utiliser un joker ? (y/n): ");
            char joker=Ut.saisirCaractere();
            if (joker == 'y'||joker=='Y') {
                malus++;
                Ut.afficherSL("ou voulez vous demander un joker ? (lingne, colone)");
                i = saisirEntierMinMax(1, 9)-1;
                j = saisirEntierMinMax(1, 9)-1;
                gHumain[i][j] = gSecret[i][j];
                Ut.afficher("la valeur qui était sur cette case est : " + gHumain[i][j]);
            } else {
                Ut.afficherSL("rentrez une coordoner ou vous voulez placer un nombre puis donner le ");
                i = saisirEntierMinMax(1, 9)-1;
                j = saisirEntierMinMax(1, 9)-1;
                if(gHumain[i][j]==0) {
                    int nb= saisirEntierMinMax(1, 9);
                    if(nb!= gSecret[i][j]){
                        Ut.afficherSL("faux reeseyer");
                        malus++;
                    }
                    else {
                        gHumain[i][j]=nb;
                    }
                }
                else if (gHumain[i][j]>0) {
                    Ut.afficher("ce si n'est pas un trou");
                }

            }
        }while (gHumain[i][j] != gSecret[i][j]);

        return malus;
    }  // fin  tourHumain

    //.........................................................................

    // Tour de l'ordinateur
    //.........................................................................

    /** pré-requis : gOrdi et nbValPoss sont des matrices 9x9
     *  résultat :   le premier trou (i,j) de gOrdi (c'est-à-dire tel que gOrdi[i][j]==0)
     *               évident (c'est-à-dire tel que nbValPoss[i][j]==1) dans l'ordre des lignes,
     *                s'il y en a, sinon le premier trou de gOrdi dans l'ordre des lignes
     * 
     */
    public static int[] chercheTrou(int[][] gOrdi,int [][]nbValPoss){
	//___________________________________________________________________
        int [] x = new int[2];
        boolean b =true;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(gOrdi[i][j] == 0 ){
                    if (nbValPoss[i][j] == 1){
                        x[0] = i;
                        x[1] = j;
                        return x;
                    }
                    else if (b) {
                     x[0] = i;
                     x[1] = j;
                     b=false;
                    }
                }
            }
        }
        return x;
    }  // fin chercheTrou

    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoju incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action :     effectue un tour de l'ordinateur      
     */
    public static int tourOrdinateur(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss){
	//________________________________________________________________________________________________
        int penalty = 0;
        int[] trou = chercheTrou(gOrdi, nbValPoss);    // cherche Trou et l'assigne
        int i = trou[0];
        int j = trou[1];
        if (nbValPoss[i][j] == 1) {                   // 1 seule possibilité de valeur
            int val = uneValeur(valPossibles[i][j]);
            gOrdi[i][j] = val;
            suppValPoss(gOrdi,i,j,valPossibles, nbValPoss);
        } else {                                       // Plusieurs valeurs possibles et l'ordinateur demande un joker
            System.out.println("L'ordinateur prend un joker pour la case (" + (i + 1) + "," + (j + 1) + ")" + "Saisissez la valeur de la case : ");
            int valeurSaisieH = saisirEntierMinMax(1, 9);      // Humain saisit une valeur toujours juste
            gOrdi[i][j] = valeurSaisieH;
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss);  // Mets a jour les valeurs possibles
            penalty++;
        }
        return penalty;


    }  // fin tourOrdinateur

    //.........................................................................

    // Partie
    //.........................................................................



    /** pré-requis : aucun
     *  action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     *  résultat :   0 s'il y a match nul, 1 si c'est le joueur humain qui gagne et 2 sinon
     */
    public static int partie(){
	//_____________________________
        int [][] gSecret= new int[9][9];
        int [][] gHumain = new int[9][9];
        int [][]gOrdie = new int [9][9];
        boolean[][][] valPossibles =new boolean [9][9][10];
        int [][]nbValPoss= new int [9][9];
        int nbTrous=initPartie(gSecret,gHumain, gOrdie,valPossibles, nbValPoss);
        int malusOrdi=0;
        int malusHumain=0;
        for (int i=0; i<nbTrous; i++){
            malusHumain+=tourHumain(gSecret, gHumain);
            malusOrdi+=tourOrdinateur(gOrdie,valPossibles,nbValPoss);
        }
        if (malusOrdi>malusHumain){
            return 1;
        }
        else if (malusOrdi==malusHumain){
            return 0;
        }
        else {
            return 2;
        }
    }  // fin partie


    //.........................................................................


    /** pré-requis : aucun
     *  action :     effectue une partie de Sudoku entre le joueur humain et l'ordinateur
     *               et affiche qui a gagné
     */
    public static void main(String[] args){
	//________________________________________
       int x =  partie();
        if (x == 0 ){
            System.out.println("match nul ");
        }
        else if (x == 1) {
            System.out.println("le joueur a gagné ");
        }
        else {
            System.out.println("le robot a gagné ");
        }

    }  // fin main

} // fin SudokuBase



