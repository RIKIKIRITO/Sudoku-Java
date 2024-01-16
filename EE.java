public class EE {

    // Bon courage ;)
    // Merci j'en aurais besoins :)

    private int[] ensTab;
    private int cardinal;

    public EE(int max) {
        this.ensTab = new int[max];
        this.cardinal = 0;
    }

    public EE(int max, int[] ensemble) {
        this(max);
        for (int i = 0; i < ensemble.length; i++) {
            this.ensTab[i] = ensemble[i];
        }
        this.cardinal = ensemble.length;
    }

    public EE(EE ensemble) {
        this(ensemble.ensTab.length, ensemble.ensTab);
    }

    public EE(int max, String elements) {
        this(max);
        String[] numbers = elements.split(" ");
        for(int i = 0; i < numbers.length; i++) this.ensTab[i] = Integer.parseInt(numbers[i]);
        cardinal = numbers.length;
    }

    public String toString () {
        String chaine = "{";
        for (int i = 0; i < this.cardinal; i++) {
            chaine += (this.ensTab[i]);
            if (i < this.cardinal - 1) {
                chaine += (", ");
            }
        }
        chaine += "}";
        return chaine;
    }

    public int getCardinal () {
        return this.cardinal;
    }
    public int[] getEnsTab(){
        return this.ensTab;
    }

    private int contientPratique (int element) {
        for (int i = 0; i < this.cardinal; i++) {
            if (this.ensTab[i] == element) return i;
        }
        return -1;
    }

    public boolean contient (int element) {
        return contientPratique(element) != -1;
    }

    /** pré-requis: e pas déja dans l'ensemble
     *  l'ensemble n'est pas plein
     */
    private void ajoutPratique (int e) {
        if (!contient(e) && this.cardinal < this.ensTab.length) {
            this.ensTab[this.cardinal] = e;
            this.cardinal += 1;
        }
    }

    /** pré-requis: 0 <= i <= this.cardinal
     */
    public int retraitPratique (int i) {
        int valeur = this.ensTab[i];
        for (int j = i ; j < this.cardinal - 1; j++) {
            this.ensTab[j] = this.ensTab[j+1];
        }
        cardinal -= 1;
        return valeur;
    }

    public Boolean estVide () {
        return this.cardinal == 0;
    }

    public int maxCardinal (EE ensemble1, EE ensemble2) {
        return Math.max(ensemble1.cardinal, ensemble2.cardinal);
    }

    public Boolean deborde () {
        return this.cardinal == this.ensTab.length;
    }

    public Boolean retraitElt (int element) {
        if (contient(element)) {
            this.retraitPratique(contientPratique(element));
            return true;
        }
        return false;
    }

    public int ajoutElt (int element) {
        if (!this.deborde()) {
            if (!contient(element)) {
                this.ajoutPratique(element);
                return 1;
            }
            return 0;
        }
        return -1;
    }

    public Boolean estInclus (EE ensemble) {
        boolean inclus = true;
        for (int i = 0; i < this.cardinal; i++) {
            if (!ensemble.contient(this.ensTab[i])) inclus = false;
        }
        return inclus;
    }

    public Boolean estEgal (EE ensemble) {
        return this.cardinal == ensemble.cardinal && this.estInclus(ensemble);
    }

    public Boolean estDisjoint (EE ensemble) {
        boolean disjoint = true;
        for (int i = 0; i < this.cardinal; i++) {
            if (ensemble.contient(this.ensTab[i])) disjoint = false;
        }
        return disjoint;
    }

    public EE intersection (EE ensemble) {
        EE ensembleInter = new EE(maxCardinal(this, ensemble));
        for (int i = 0; i < this.cardinal; i++) {
            if (ensemble.contient(this.ensTab[i])) ensembleInter.ajoutPratique(this.ensTab[i]);
        }
        return ensembleInter;
    }

    public EE reunion (EE ensemble) {
        EE ensembleReunion = new EE(this.cardinal + ensemble.cardinal);
        for (int i = 0; i < this.cardinal; i++) {
            ensembleReunion.ajoutElt(this.ensTab[i]);
        }
        for (int i = 0; i < ensemble.cardinal; i++) {
            ensembleReunion.ajoutElt(ensemble.ensTab[i]);
        }
        return ensembleReunion;
    }

    public EE difference (EE ensemble) {
        EE ensembleDiff = new EE(this.cardinal);
        for (int i = 0; i < this.cardinal; i++) {
            if (!ensemble.contient(this.ensTab[i])) {
                ensembleDiff.ajoutElt(this.ensTab[i]);
            }
        }
        return ensembleDiff;
    }
}