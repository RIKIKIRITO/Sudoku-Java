public class Ext34 {

    private static void rotation90(int [][] g){
        int [][] g1= new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int i=0; i<g1.length;i++){
            for(int j=0;j<g1[0].length;j++){
                g[j][8-i]=g1[i][j];
            }
        }
    }
    private static void symetrieH(int [][]g){
        int [][]g1=new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int i=0; i<g1.length;i++){
            for(int j=0;j<g1[0].length;j++){
                g[8-i][j]=g1[i][j];
            }
        }
    }
    private static void symetrieV(int [][]g){
        int [][]g1=new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int i=0; i<g1.length;i++){
            for(int j=0;j<g1[0].length;j++){
                g[i][8-j]=g1[i][j];
            }
        }
    }
    private static void symetrieD(int [][]g){
        int [][]g1=new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int i=0; i<g1.length;i++){
            for(int j=0;j<g1[0].length;j++){
                g[j][i]=g1[i][j];
            }
        }
    }
    private static void echangeColonne(int [][]g,int j1, int j2){
        int [][] g1= new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int x=0;x<g[0].length;x++){
            g[x][j1]=g1[x][j2];
            g[x][j2]=g1[x][j1];
        }
    }
    private static void echangeLigne(int [][]g,int i1, int i2){
        int [][] g1= new int [9][9];
        SudokuBase.copieMatrice(g,g1);
        for(int x=0;x<g[0].length;x++){
            g[i1][x]=g1[i2][x];
            g[i2][x]=g1[i1][x];
        }
    }
    private static void creaGrille(int [][]g){
        int nb=Ut.randomMinMax(1,9);
        for(int j=0; j<g.length;j++){
            g[0][j]=nb;
            Ut.afficherSL(nb);
            nb++;
            if(nb>9){
                nb=1;
            }
        }

        for(int i=1; i<g.length;i++) {
            for (int j = 0; j < g[0].length; j++) {
                nb=g[i-1][j]+3;
                if(nb>9){
                    nb=nb%9;
                }
                g[i][j]=nb;
            }

        }
    }
    public static void transformation(int [][] g){
        for(int i=0; i<100;i++){
            int nb=Ut.randomMinMax(0,4);
            if(nb==0){
                rotation90(g);
            }
            else if (nb==1){
                symetrieH(g);
            }
            else if (nb==2){
                symetrieV(g);
            }
            else if(nb==3){
                symetrieD(g);
            }
            else {
                int a=Ut.randomMinMax(0,2);
                int b;
                do{
                    b=Ut.randomMinMax(0,2);
                }while (a==b);
                int carre=Ut.randomMinMax(0,2)*3;
                if(nb==4){
                    echangeLigne(g, a+carre,b+carre);
                }
                else {
                    echangeColonne(g,a+carre,b+carre);
                }

            }
        }
    }
    public static void grilleAlea(int [][]g){
        creaGrille(g);
        transformation(g);
    }


    public static void main(String[] args) {
        int [][] g= new int[9][9];
        //SudokuBase.initGrilleComplete(g);
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
        symetrieD(sudoku);
        SudokuBase.afficheGrille(3,sudoku);

        SudokuBase.afficheGrille(3,g);
    }
}
