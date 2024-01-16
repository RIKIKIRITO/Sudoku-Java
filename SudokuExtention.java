import java.util.Random;


public class SudokuExtention {

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
                        afficheGrille(3, g);
                        if (Ext32.verif(i,j,g) == false){
                            while(Ext32.verif(i,j,g) != true ) {
                                System.out.println("la valeur est fausse");
                                System.out.println("resaisier une valuer correcte ");
                                g[i][j] = saisirEntierMinMax(1, 9);
                                afficheGrille(3, g);
                            }
                        }
                    }
                    else{
                        g[i][j] = saisirEntierMinMax(0,9);
                        afficheGrille(3, g);
                        if (g[i][j] != 0){
                            while(Ext32.verif(i,j,g) != true ) {
                                    System.out.println("la valeur est fausse");
                                    System.out.println("resaisier une valuer correcte ");
                                    g[i][j] = saisirEntierMinMax(1, 9);
                                    afficheGrille(3, g);
                            }
                        }
                        if (g[i][j] == 0){
                            compt ++;
                        }
                    }
                }
            }
        }
      // fin saisirGrilleIncomplete

    //.........................................................................

    /** pré-requis : gOrdi est une grille de Sudoku incomplète,
     *               valPossibles est une matrice 9x9 de tableaux de 10 booléens
     *               et nbValPoss est une matrice 9x9 d'entiers
     *  action : met dans valPossibles l'ensemble des entiers de 1 à 9 pour chaque trou de gOrdi
     *           et leur nombre dans nbValPoss       
     */
    public static void initPleines(int [][] gOrdi, boolean[][][] valPossibles, int [][] nbValPoss,
                                   boolean[][] ligPossibles, int[]nbLigPoss,
                                   boolean[][] colPossibles,int []nbColPoss,
                                   boolean[][][] carPossibles,int[][] nbCarPoss,
                                   EE[][] matEns){
	//________________________________________________________________________________________________
        for (int i=0; i< gOrdi.length; i++){
            for(int j=0; j<gOrdi[0].length; j++){
                if (gOrdi[i][j]==0) {
                    nbValPoss[i][j] = 9;
                    valPossibles[i][j]=ensPlein(9);
                    Ext36.initPleinesLCC(ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,i,j);
                    Ext37.initEns(matEns,i,j);
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
    public static void suppValPoss(int [][] gOrdi, int i, int j, boolean[][][] valPossibles, int [][]nbValPoss,
                                   boolean[][] ligPossibles, int[]nbLigPoss,
                                   boolean[][] colPossibles,int []nbColPoss,
                                   boolean[][][] carPossibles,int[][] nbCarPoss,
                                   EE[][] matEns){
	//_____________________________________________________________________________________________________________
        int nb=gOrdi[i][j];
        for (int x=0; x< gOrdi.length;x++){
            if(supprime(valPossibles[i][x],gOrdi[i][j])){
                nbValPoss[i][x]--;
                if(matEns[i][x]!=null) {
                    matEns[i][x].retraitElt(nb);
                }
            }
            Ext36.suppValPossLCC(gOrdi,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,i,j,x,j);
        }
        for (int y=0; y< gOrdi[0].length;y++){
            if(supprime(valPossibles[y][j],gOrdi[i][j])){
                nbValPoss[y][j]--;
                if(matEns[y][j]!=null) {
                    matEns[y][j].retraitElt(nb);
                }
            }
            Ext36.suppValPossLCC(gOrdi,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,i,j,i,y);
        }
        for (int x=debCarre(3,i,j)[0];x<debCarre(3,i,j)[1]+3;x++){
            for (int y=debCarre(3,i,j)[1];y<debCarre(3,i,j)[0]+3;y++){
                if(supprime(valPossibles[x][y],gOrdi[i][j])){
                    nbValPoss[x][y]--;
                    if(matEns[y][x]!=null) {
                        matEns[y][x].retraitElt(nb);
                    }
                }
                Ext36.suppValPossLCC(gOrdi,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,i,j,x,y);
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
    public static void initPossibles(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss,
                                     boolean[][] ligPossibles, int[]nbLigPoss,
                                     boolean[][] colPossibles,int []nbColPoss,
                                     boolean[][][] carPossibles,int[][] nbCarPoss,
                                     EE[][] matEns){
	//________________________________________________________________________________________________
        initPleines(gOrdi, valPossibles,nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gOrdi[i][j] > 0) {
                    suppValPoss(gOrdi,i,j,valPossibles,nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                }
                Ext37.testEns(matEns,i,j,valPossibles,nbValPoss);
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
				 boolean[][][] valPossibles, int [][]nbValPoss,
                                 boolean[][] ligPossibles, int[]nbLigPoss,
                                 boolean[][] colPossibles,int []nbColPoss,
                                 boolean[][][] carPossibles,int[][] nbCarPoss,
                                 int[][] tabTrous,
                                 EE[][] matEns){
	//______________________________________________________________________________________________
        int nbTrous;
        initGrilleComplete(gSecret);    // gSecret est une grille de Sudoku complète
        Ext34.transformation(gSecret);
        //afficheGrille(3,gSecret);
        copieMatrice(gSecret,gHumain);
        Ut.afficherSL("voulez vous faire une grille facile?(y/n)");
        char rep1=Ut.saisirCaractere();
        if(rep1=='y'||rep1=='Y'){
            nbTrous=Ext35.suppTRouEvid(gHumain);
            Ut.afficherSL("votre grille aura "+nbTrous+" troue");
        }
        else {
            Ut.afficherSL("nombre de troue que vous voulez pour votre grille");
            nbTrous = saisirEntierMinMax(0,81);            // joueur humain saisit nbTrous entre 0 et 81
            initGrilleIncomplete(nbTrous,gSecret,gHumain);     // gHumain grille incomplète pouvant etre complétée en gSecret avec nbTrous aléatoires
        }
        Ut.afficherSL("voulez vous rentrer tout les valeur ?(y/n)");
        char rep2=Ut.saisirCaractere();
        if(rep2=='y'||rep1=='Y'){
            saisirGrilleIncomplete( nbTrous,gOrdi);// gOrdi grille incomplète avec nbTrous
        }
        else{
            Ut.afficherSL("la grille de l'ordinateur");
            Ext34.transformation(gSecret);
            afficheGrille(3,gSecret);
            initGrilleIncomplete(nbTrous,gSecret,gOrdi);//gOrdi grille incomplète avec nbTrous
        }
        initPossibles(gOrdi,valPossibles,nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns); //  ensembles valeurs possibles de chaque trous et nbValPoss
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
        boolean b=true;
        do {
            b=true;
            Ut.sautLigne();
            afficheGrille(3, gHumain);
            Ut.afficher("voulez-vous utiliser un joker ? (y/n): ");
            char joker=Ut.saisirCaractere();
            if (joker == 'y'||joker=='Y') {
                malus++;
                Ut.afficherSL("ou voulez vous demander un joker ? (lingne, colone)");
                i = saisirEntierMinMax(1, 9)-1;
                j = saisirEntierMinMax(1, 9)-1;
                if (gHumain[i][j]>0) {
                    Ut.afficher("ce si n'est pas un trou");
                    b=false;
                }
                else {
                    if(gSecret[i][j] == 0){
                        System.out.println("le joueur a triché");
                        return 99;
                    }
                    gHumain[i][j] = gSecret[i][j];
                    Ut.afficherSL("la valeur qui était sur cette case est : " + gHumain[i][j]);
                }
            } else {
                Ut.afficherSL("rentrez une coordoner ou vous voulez placer un nombre puis donner le ");
                i = saisirEntierMinMax(1, 9)-1;
                j = saisirEntierMinMax(1, 9)-1;
                if(gHumain[i][j]==0) {
                    int nb= saisirEntierMinMax(1, 9);
                    if(gSecret[i][j] == 0){
                        System.out.println("le joueur a triché");
                        return 99;
                    }
                    if(nb!= gSecret[i][j]){
                        Ut.afficherSL("faux reeseyer");
                        b=false;
                        malus++;
                    }
                    else {
                        gHumain[i][j]=nb;
                    }
                }
                else if (gHumain[i][j]>0) {
                    Ut.afficher("ce si n'est pas un trou");
                    b=false;
                }

            }
        }while (!b);

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
        int nb=9;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(gOrdi[i][j] == 0 ){
                    if (nbValPoss[i][j] == 1){
                        x [0] = i;
                        x[1] = j;
                        return x;
                    }
                    else if (nb>nbValPoss[i][j]){
                        x[0]=i;
                        x[1]=j;
                        nb=nbValPoss[i][j];
                    }
                    else if (x==null) {
                     x[0] = i;
                     x[1] = j;
                     nb=nbValPoss[i][j];
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
    public static int tourOrdinateur(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss,
                                     boolean[][] ligPossibles, int[]nbLigPoss,
                                     boolean[][] colPossibles,int []nbColPoss,
                                     boolean[][][] carPossibles,int[][] nbCarPoss,
                                     EE[][] matEns,
                                     int[][] tabTrous,
                                     int nbTroues){
	//________________________________________________________________________________________________
        int penalty = 0;
        int [] trou= new int [2];
        trou[0]=chercheTrou(gOrdi,nbValPoss)[0];          // Récupère les coordonées du dernier trou ajouté
        trou[1]=chercheTrou(gOrdi,nbValPoss)[1];
        int i = trou[0];
        int j = trou[1];
        if (nbValPoss[i][j] == 1) {
            int val = uneValeur(valPossibles[i][j]);
            gOrdi[i][j] = val;
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
        }
        else if (nbLigPoss[i]==1) {
            gOrdi[i][j]=uneValeur(ligPossibles[i]);
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
        }
        else if (nbColPoss[j]==1) {
            gOrdi[i][j]=uneValeur(colPossibles[j]);
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);

        }
        else if (nbCarPoss[i/3][j/3]==1){
            gOrdi[i][j]=uneValeur(carPossibles[i/3][j/3]);
            suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
        }
        else if (nbValPoss[i][j] == 2) {
            int val = Ext31.uneValeurExt31(valPossibles[i][j], Ut.randomMinMax(0, 1));
            System.out.println("Pour la case située en (" + (i + 1) + "," + (j + 1) + ")");
            System.out.println("L'ordinateur choisit : " + val);
            System.out.println("Le chiffre est-il correct ? Réponses possibles: 'oui' ou 'non'");
            String Humainr = Ut.saisirChaine();
            if (Humainr.equals("oui")) {
                gOrdi[i][j] = val;
                suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
            } else {
                System.out.println("Mauvaise réponse de l'ordinateur ");
                System.out.println("L'ordinateur prend un joker pour la case (" + (i + 1) + "," + (j + 1) + ")" + "Saisissez la valeur de la case : ");
                int valeurSaisieH = saisirEntierMinMax(1, 9);
                if (!Ext32.verifjoker(j,i, valeurSaisieH, valPossibles)){
                    System.out.println("vous avez trichez");
                    return -99;
                }
                else {
                    gOrdi[i][j] = valeurSaisieH;
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                    penalty++;
                }

            }
        }
        /*else if (nbValPoss[i][j]>=1) {
            int val =uneValeur(valPossibles[i][j]);
            boolean[] ens=new boolean[10];
            Ext38.copieTabB(valPossibles[i][j],ens);
            while (val!=-1) {
                while (Ext38.testVall(gOrdi, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns, nbTroues, i, j, val, 1)) {
                    if(nbValPoss[i][j]>1) {
                        supprime(valPossibles[i][j], val);
                        nbValPoss[i][j]--;
                    }
                    Ut.afficherSL(nbValPoss[i][j]);
                    val = SudokuExtention.uneValeur(valPossibles[i][j]);
                    if (nbValPoss[i][j] == 1) {
                        gOrdi[i][j] = uneValeur(valPossibles[i][j]);
                        suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
                        return penalty;
                    }

                }
                if(uneValeur(ens)!=-1){
                    supprime(ens, uneValeur(ens));
                }
                val=uneValeur(ens);
            }
            if (nbValPoss[i][j] == 2) {
                int val2 = Ext31.uneValeurExt31(valPossibles[i][j], Ut.randomMinMax(0, 1));
                System.out.println("Pour la case située en (" + (i + 1) + "," + (j + 1) + ")");
                System.out.println("L'ordinateur choisit : " + val2);
                System.out.println("Le chiffre est-il correct ? Réponses possibles: 'oui' ou 'non'");
                String Humainr = Ut.saisirChaine();
                if (Humainr.equals("oui")) {
                    gOrdi[i][j] = val2;
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                } else {
                    System.out.println("Mauvaise réponse de l'ordinateur ");
                    System.out.println("L'ordinateur prend un joker pour la case (" + (i + 1) + "," + (j + 1) + ")" + "Saisissez la valeur de la case : ");
                    int valeurSaisieH = saisirEntierMinMax(1, 9);
                    gOrdi[i][j] = valeurSaisieH;
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                    penalty++;
                }
            }
            else if (nbValPoss[i][j]>1){
                System.out.println("L'ordinateur prend un joker pour la case (" + (i + 1) + "," + (j + 1) + ")" + "Saisissez la valeur de la case : ");
                int valeurSaisieH = saisirEntierMinMax(1, 9);
                if (Ext32.verifjoker(j,i, valeurSaisieH, valPossibles) != true){
                    System.out.println("vous avez trichez");
                    return -99;
                }
                else {
                    gOrdi[i][j] = valeurSaisieH;
                    suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                    penalty++;
                }

            }
            else {
                gOrdi[i][j]=uneValeur(valPossibles[i][j]);
            }
        }*/
        else {
            System.out.println("L'ordinateur prend un joker pour la case (" + (i + 1) + "," + (j + 1) + ")" + "Saisissez la valeur de la case : ");
            int valeurSaisieH = saisirEntierMinMax(1, 9);
            if (!Ext32.verifjoker(j,i, valeurSaisieH, valPossibles)){
                System.out.println("vous avez trichez");
                return -99;
            }
            else {
                gOrdi[i][j] = valeurSaisieH;
                suppValPoss(gOrdi, i, j, valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns);
                penalty++;
            }

        }
        //Ext33.pushTrous(tabTrous, trou);                          // Mets a jour le tableau de trous évidents ('tabTrous')
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
        boolean[][] ligPossibles =new boolean [9][10];
        int []nbLigPoss= new int [9];
        boolean[][] colPossibles =new boolean [9][10];
        int []nbColPoss= new int [9];
        boolean[][][] carPossibles =new boolean [3][3][10];
        int [][]nbCarPoss= new int [3][3];
        EE[][] matEns=new EE[9][9];
        int[][] tabTrous= new int[81][2];
        int nbTrous=initPartie(gSecret,gHumain, gOrdie,valPossibles, nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,tabTrous, matEns);
        int malusOrdi=0;
        int malusHumain=0;
        for (int i=0; i<nbTrous; i++){
            malusHumain+=tourHumain(gSecret, gHumain);
            malusOrdi+=tourOrdinateur(gOrdie,valPossibles,nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss, matEns,tabTrous,i);
            if (malusOrdi<0 ){
                return 2;
            }
            if (malusHumain<=-99){
                return 2;
            }
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

} // fin SudokuExtention



