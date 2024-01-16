public class Ext38 {
    public static boolean tourOrdinateurSimple(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss,
                                     boolean[][] ligPossibles, int[]nbLigPoss,
                                     boolean[][] colPossibles,int []nbColPoss,
                                     boolean[][][] carPossibles,int[][] nbCarPoss,
                                     EE[][] matEns,
                                     int nbTroues, int nb) {
        //________________________________________________________________________________________________
        int penalty = 0;

        int[] trou = SudokuExtention.chercheTrou(gOrdi, nbValPoss); //Ext33.pull(tabTrous);                                   // Récupère les coordonées du dernier trou ajouté
        int i = trou[0];
        int j = trou[1];
        if (nbValPoss[i][j] == 1) {
            int val = SudokuExtention.uneValeur(valPossibles[i][j]);
            gOrdi[i][j] = val;
            SudokuExtention.suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
            return true;
        } else if (nbLigPoss[i] == 1) {
            gOrdi[i][j] = SudokuExtention.uneValeur(ligPossibles[i]);
            SudokuExtention.suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
            return true;
        } else if (nbColPoss[j] == 1) {
            gOrdi[i][j] = SudokuExtention.uneValeur(colPossibles[j]);
            SudokuExtention.suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
            return true;
        } else if (nbCarPoss[i / 3][j / 3] == 1) {
            gOrdi[i][j] = SudokuExtention.uneValeur(carPossibles[i / 3][j / 3]);
            SudokuExtention.suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
            return true;
        } else if (nbValPoss[i][j] >= 2) {
            int val = SudokuExtention.uneValeur(valPossibles[i][j]);
            boolean[] ens = new boolean[10];
            copieTabB(valPossibles[i][j], ens);
            while (val != -1) {
                while (Ext38.testVall(gOrdi,valPossibles,nbValPoss,ligPossibles,nbLigPoss,colPossibles,nbColPoss,carPossibles,nbCarPoss,matEns,nbTroues,i,j,val,nb)) {
                    if (nbValPoss[i][j] > 1) {
                        SudokuExtention.supprime(valPossibles[i][j], val);
                        nbValPoss[i][j]--;
                    }
                    Ut.afficherSL(nbValPoss[i][j]);
                    val = SudokuExtention.uneValeur(valPossibles[i][j]);
                    if (nbValPoss[i][j] == 1) {
                        gOrdi[i][j] = SudokuExtention.uneValeur(valPossibles[i][j]);
                        SudokuExtention.suppValPoss(gOrdi, i, j, valPossibles, nbValPoss, ligPossibles, nbLigPoss, colPossibles, nbColPoss, carPossibles, nbCarPoss, matEns);
                        return true;
                    }
                    return false;
                }

            }
            if (SudokuExtention.uneValeur(ens) != -1) {
                SudokuExtention.supprime(ens, SudokuExtention.uneValeur(ens));
            }
            val = SudokuExtention.uneValeur(ens);
        } else if(nbValPoss[i][j]==0){
            return false;
        }
        return true;
    }  // fin tourOrdinateur

    public static boolean testVall(int [][] gOrdi, boolean[][][] valPossibles, int [][]nbValPoss,
                                   boolean[][] ligPossibles, int[]nbLigPoss,
                                   boolean[][] colPossibles,int []nbColPoss,
                                   boolean[][][] carPossibles,int[][] nbCarPoss,
                                   EE[][] matEns,
                                   int nbTroueRest,
                                   int i,int j,int val,
                                   int nb1){

        int [][]gtest=new int [9][9];
        SudokuExtention.copieMatrice(gOrdi,gtest);
        boolean[][][] valPC=new boolean[9][9][10];
        copieMat3B(valPossibles,valPC);
        gtest[i][j]=val;
        int [][] nbVPC = new int [9][9];
        SudokuExtention.copieMatrice(nbValPoss,nbVPC);
        boolean[][]ligPC=new boolean[9][10];
        copieMatB(ligPossibles,ligPC);
        int[] nbLPC=new int[9];
        copieTab(nbLigPoss,nbLPC);
        boolean[][] colPC =new boolean[9][10];
        copieMatB(colPossibles,colPC);
        int [] nbCP=new int [9];
        copieTab(nbColPoss,nbCP);
        boolean[][][] carPC=new boolean[9][9][10];
        copieMat3B(carPossibles,carPC);
        int [][]nbCPC=new int [3][3];
        SudokuExtention.copieMatrice(nbCarPoss,nbCPC);
        EE [][] matEns2=new EE[9][9];
        copeMatEE(matEns,matEns2,gtest);
        int nb=nb1+1;
        if(nb>=81){
            return false;
        }
        for(int a=0;a<nbTroueRest-1; a++){
            if(!tourOrdinateurSimple(gtest,valPC,nbVPC,ligPC,nbLPC,colPC,nbCP,carPC,nbCPC,matEns2,i,nb)){
                return false;
            }
        }
        return true;
    }
    public static void copieTabB(boolean[] tab1, boolean [] tab2){
        for(int i=0;i<tab1.length;i++){
            tab2[i]=tab1[i];
        }
    }
    private static void copieMatB(boolean[][] mat1, boolean[][] mat2){
        for(int i=0;i<mat1.length;i++){
            copieTabB(mat1[i],mat2[i]);
        }
    }
    private static void copieMat3B(boolean[][][] mat1, boolean[][][] mat2){
        for(int i=0;i<mat1.length;i++){
            copieMatB(mat1[i],mat2[i]);
        }
    }
    private static void copieTab(int [] tab1, int [] tab2){
        for(int i=0; i<tab1.length;i++){
            tab2[i]=tab1[i];
        }
    }
    private static void copeMatEE(EE[][] mat1, EE[][] mat2,int[][] gtest){
        for(int i=0;i<mat1.length;i++){
            for(int j=0;j<mat1[0].length;j++){
                if(gtest[i][j]==0) {
                    mat2[i][j] = new EE(mat1[i][j]);
                }
            }
        }
    }
}
