public class Ext31 {
    public static int uneValeurExt31(boolean[] ens , int p ) {
        //_____________________________________________
        
        for (int i = 1; i < ens.length; i++) {
            if (ens[i]) {
                 return i;    
            }
        }
        return -1;
    }  // fin uneValeur

}
