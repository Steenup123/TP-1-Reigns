package main;

import reigns.Jauges.Jauge;

import java.util.HashMap;

public class Personnage {

    protected String nom;
    protected Genre genre;

    protected HashMap<String, Jauge> jauges;

    public Personnage(String nom, Genre genre, HashMap<String, Jauge> jauges) {
        this.nom = nom;
        this.genre = genre;
        this.jauges = jauges;
    }

    /**
     * Affiche les jauges dans la console
     */
    public void AfficheJauges() {
        for (HashMap.Entry<String, Jauge> jauge : getJauges().entrySet()) {
            jauge.getValue().afficheJauge();
        }
        System.out.flush();
    }

    public String getNom() {
        return nom;
    }

    public Genre getGenre() {
        return genre;
    }

    public HashMap<String, Jauge> getJauges() {
        return jauges;
    }

}
