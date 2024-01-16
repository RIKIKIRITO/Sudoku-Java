public class Ext32 {
    public static boolean verif(int i, int j, int[][] g) {
        int nb = g[i][j];
        if(g[i][j] == 0 ){
            return true;
        }
        for (int x = 0; x < g.length; x++) {

            if (g[x][j] > 0) {

                if (nb == g[x][j]&&x!=i) {
                    return false;
                }
            }

        }
        for (int y = 0; y < g[0].length; y++) {
            if (g[i][y] > 0) {
                if (nb == g[i][y]&&y!=j) {
                    return false;
                }
            }
        }
        int [] debc=new int [2];
        debc[0]=SudokuBase.debCarre(3,i,j)[0];
        debc[1]=SudokuBase.debCarre(3,i,j)[1];
        for (int x = debc[0]; x < debc[0] + 3; x++) {
            for (int y = debc[1]; y < debc[1] + 3; y++) {
                if(g[x][y] > 0){
                    if (nb == g[x][y]&&y!=j&&x!=i) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean verifjoker(int j, int i, int valsaisie, boolean[][][] valpossible) {

        if (valpossible[i][j][valsaisie]) {
            return true;
        } else {
            return false;
        }
    }
}
