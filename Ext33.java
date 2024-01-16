public class Ext33 {
    public static void pushTrous(EE tabTrous, int[] coord) {
        tabTrous.ajoutElt(coord[0]);
        tabTrous.ajoutElt(coord[1]);
    }

    public static int[] pullTrous(EE tabTrous) {
        if (!tabTrous.estVide()) {
            int i = tabTrous.retraitPratique(tabTrous.getCardinal() - 1);
            int j = tabTrous.retraitPratique(tabTrous.getCardinal() - 1);
            return new int[]{i, j};
        } else {
            return null;                                                                         // Pile vide
        }
    }
}
