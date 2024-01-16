public class Ext36 {

    public static void initPleinesLCC(boolean[][] ligPossibles, int[]nbLigPoss,
                                      boolean[][] colPossibles,int []nbColPoss,
                                      boolean[][][] carPossibles,int[][] nbCarPoss,int i, int j){
        nbColPoss[j]=9;
        colPossibles[j]=SudokuBase.ensPlein(9);
        nbLigPoss[i]=9;
        ligPossibles[i]=SudokuBase.ensPlein(9);
        nbCarPoss[i/3][j/3]=9;
        carPossibles[i/3][j/3]=SudokuBase.ensPlein(9);
    }
    public static void  suppValPossLCC(int[][]g, boolean[][] ligPossibles, int[]nbLigPoss,
                                       boolean[][] colPossibles,int []nbColPoss,
                                       boolean[][][] carPossibles,int[][] nbCarPoss,int i, int j,int x,int y) {
        if(SudokuBase.supprime(colPossibles[y],g[i][j])){
            nbColPoss[y]--;
        }
        if(SudokuBase.supprime(ligPossibles[x],g[i][j])){
            nbLigPoss[x]--;
        }
        if(SudokuBase.supprime(carPossibles[x/3][y/3],g[i][j])){
            nbCarPoss[x/3][y/3]--;
        }
    }

}
