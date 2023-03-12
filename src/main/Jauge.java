package main;

public class Jauge {

    protected TypeJauge type;
    protected int valeur;

    public Jauge(TypeJauge type) {
        this.type = type;
        this.valeur = (int) (15 + Math.random() * (35 - 15));
    }

    public void afficheJauge() {
        String resultat = "[";
        // valeur : ####
        for (int i = 0; i < getValeur(); i++) {
            resultat += "#";
        }
        // on complète avec ____
        for (int i = 0; i < 50 - (getValeur() > 0 ? getValeur() : 0); i++) {
            resultat += "_";
        }
        resultat += "]  ";
        // affichage du nom
        resultat += getType().toString();
        System.out.println(resultat);
    }

    public boolean hasJaugePleine() {
        return getValeur() >= 50 || getValeur() <= 0;
    }

    public void changementValeur(int effetAppliquer) {
        this.setValeur(getValeur() + effetAppliquer);
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public TypeJauge getType() {
        return type;
    }

}
