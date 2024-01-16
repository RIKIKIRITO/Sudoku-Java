public class Ext35 {
    public static void afficherTabB(boolean []tab){
        for (int i=0;i<tab.length;i++){
            Ut.afficher(tab[i]+", ");
        }
    }

    private static boolean verifTroueEvident(int [][]g,int i, int j){
        if(g[i][j]==0){
            return false;
        }
        int nb=g[i][j];
        g[i][j]=0;
        boolean[] valPossible;
        valPossible=SudokuBase.ensPlein(9);
        for(int x= 0; x<g.length; x++){
            if (g[x][j]>0&&x!=i){
                valPossible[g[x][j]]=false;
            }
        }
        for(int y=0; y<g[0].length; y++){
            if (g[i][y]>0&&y!=j){
                valPossible[g[i][y]]=false;
            }
        }
        int [] debc=SudokuBase.debCarre(3,i,j);
        for( int x=debc[0]; x<debc[0]+3; x++){
            for (int y=debc[1]; y<debc[1]+3; y++){
                if (g[x][y]>0&&(x!=i||y!=j)){
                    valPossible[g[x][y]]=false;
                }
            }
        }
        int b=0;
        for(int a=1; a<valPossible.length; a++){
            if (valPossible[a]){
                b++;
            }
            if (b>1){
                g[i][j]=nb;
                return false;
            }
        }
        return true;
    }
    private static boolean verifTroueEvident2( int[][]g,int [][]nbValpos, int i, int j){
        if(g[i][j]==0){
            return false;
        }
        int nb=g[i][j];
        g[i][j]=0;
        for(int x= 0; x<g.length; x++){
            if (nbValpos[x][j]>0&&x!=i){
                g[i][j]=nb;
                return false;
            }
        }
        for(int y=0; y<g[0].length; y++){
            if (nbValpos[i][y]>0&&y!=j){
                g[i][j]=nb;
                return false;
            }
        }
        int [] debc=new int [2];
        debc[0]=SudokuBase.debCarre(3,i,j)[0];
        debc[1]=SudokuBase.debCarre(3,i,j)[1];
        for( int x=debc[0]; x<debc[0]+3; x++){
            for (int y=debc[1]; y<debc[1]+3; y++){
                if (nbValpos[x][y]>0&&x!=i&&y!=j){
                    g[i][j]=nb;
                    return false;
                }
            }
        }
        nbValpos[i][j]++;
        return true;
    }
    public static int suppTRouEvid(int [][]g){
        int nb=0;
        for(int i=0;i<=58;i++){
            int x;
            int y;
            do{
                x=Ut.randomMinMax(0,8);
                y=Ut.randomMinMax(0,8);
            } while (g[x][y]==0);
            if(verifTroueEvident(g,x,y)){
                nb++;
            }
        }
        for (int i=0;i<g.length;i++){
            for (int j=0;j<g[0].length;j++){
                if (verifTroueEvident(g,i,j)){
                    nb++;
                }
            }
        }
        return nb;
    }

    public static void main(String[] args) {
        int [][] gSecret= new int[9][9];
        int [][] gHumain = new int[9][9];
        int [][]gOrdie = new int [9][9];
        boolean[][][] valPossibles =new boolean [9][9][10];
        int [][]nbValPoss= new int [9][9];

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
        //Ext34.transformation(sudoku);
        int nb=suppTRouEvid(sudoku);
        SudokuBase.afficheGrille(3,sudoku);
        Ut.sautLigne();
        Ut.afficherSL(nb);
        SudokuBase.initPleines(sudoku,valPossibles,nbValPoss);
        SudokuBase.initPossibles(sudoku,valPossibles,nbValPoss);
        SudokuBase.afficheGrille(3,nbValPoss);

    }
}
