public class Ext37 {
    public static void initEns(EE[][] matEns,int i, int j){
        int[] tabInit={1,2,3,4,5,6,7,8,9};
                matEns[i][j]=new EE(9,tabInit);
    }
    public static void videEns(EE[][] matEns, int i, int j){
        EE e= new EE(0);
        matEns[i][j]=new EE(e);
    }
    public static void testEns(EE[][] matEns, int i,int j, boolean[][][] valPossibles, int[][] nbValPoss) {
        int nbCol = 0;
        int nbLig = 0;
        int nbCarre = 0;
        if (matEns[i][j] != null) {
            for (int x = 0; x < matEns.length; x++) {
                if (matEns[x][j]!=null&&matEns[i][j].estInclus(matEns[x][j]) && x != i) {
                    nbCol++;
                }
            }
            for (int y = 0; y < matEns[0].length; y++) {
                if (!(matEns[i][j] == null)) {
                    if (matEns[i][y]!=null&&matEns[i][j].estInclus(matEns[i][y]) && y != j) {
                        nbLig++;
                    }
                }
            }
            int[] debc = SudokuBase.debCarre(3, i, j);
            for (int x = debc[0]; x < debc[0] + 3; x++) {
                for (int y = debc[1]; y < debc[1] + 3; y++) {
                    if (matEns[x][y]!=null&&matEns[i][j].estInclus(matEns[x][y]) && (x != i || y != j)) {
                        nbCarre++;
                    }
                }
            }
            if (matEns[i][j].getCardinal() == nbLig) {
                for (int x = 0; x < matEns.length; x++) {
                    if (matEns[x][j]!=null&&!matEns[i][j].estInclus(matEns[x][j]) && x != i) {
                        matEns[x][j] = new EE(matEns[x][j].difference(matEns[i][j]));
                        mjValPoss(matEns[x][j], valPossibles[x][j], nbValPoss[x][j]);
                    }
                }
            }
            if (matEns[i][j].getCardinal() == nbCol) {
                for (int y = 0; y < matEns[0].length; y++) {
                    if (matEns[i][y]!=null&&!matEns[i][j].estInclus(matEns[i][y]) && y != j) {
                        matEns[i][y] = new EE(matEns[i][y].difference(matEns[i][j]));
                        mjValPoss(matEns[i][y], valPossibles[i][y], nbValPoss[i][y]);
                    }
                }
            }
            if (matEns[i][j].getCardinal() == nbCarre) {
                for (int x = debc[0]; x < debc[0] + 3; x++) {
                    for (int y = debc[1]; y < debc[1] + 3; y++) {
                        if (matEns[x][y]!=null&&!matEns[i][j].estInclus(matEns[x][y]) && (x != i || y != j)) {
                            matEns[x][y] = new EE(matEns[x][y].difference(matEns[i][j]));
                            mjValPoss(matEns[x][y], valPossibles[x][y], nbValPoss[x][y]);
                        }
                    }
                }
            }
        }
    }
    public static void mjValPoss(EE e, boolean[] ens, int card){
        card=e.getCardinal();
        for(int i=0; i<e.getEnsTab().length;i++){
            SudokuExtention.supprime(ens,e.getEnsTab()[i]);
        }
    }

    public static void main(String[] args) {
        EE [][] e1=new EE[9][9];
        initEns(e1,1,1);
        System.out.println(e1[1][1]);
    }

}
