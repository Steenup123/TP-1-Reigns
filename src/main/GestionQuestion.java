package com.fges.tp_solid.reigns.Question;








import com.fges.tp_solid.reigns.Condition.Conditions;
import com.fges.tp_solid.reigns.Condition.GestionCondition;
import com.fges.tp_solid.reigns.Effet.GestionEffet;
import com.fges.tp_solid.reigns.Effet.Effets;


public class GestionQuestion {

    protected String nomPnj;
    protected String question;
    protected String effetGauche;
    protected String effetDroite;
    protected Effets effetJaugeGauche;
    protected Effets effetJaugeDroite;
    protected Conditions conditions;

    public GestionQuestion(String nomPnj, String question, String effetGauche, String effetDroite,
                           Effets effetJaugeGauche, Effets effetJaugeDroite, Conditions conditions  ) {
        this.nomPnj = nomPnj;
        this.question = question;
        this.effetGauche = effetGauche;
        this.effetDroite = effetDroite;
        this.effetJaugeGauche = effetJaugeGauche;
        this.effetJaugeDroite = effetJaugeDroite;
        this.conditions = conditions;
    }


    public void afficheQuestion( boolean hasGotActive) {
        String result = "[" + nomPnj + "] "
                + question
                + "[G: " + effetGauche
                + ",D: " + effetDroite
                + "]";
        System.out.println(result);
        System.out.println("Effet G:" + getEffetJaugeGauche().afficheEffets(hasGotActive));
        System.out.println("Effet D:" + getEffetJaugeDroite().afficheEffets(hasGotActive));
        System.out.flush();
    }

    public void ajouteEffetGauche(GestionEffet gestionEffet) {
        getEffetJaugeGauche().ajouteEffet(gestionEffet);
    }

    public void ajouteEffetDroite(GestionEffet gestionEffet) {
        getEffetJaugeDroite().ajouteEffet(gestionEffet);
    }

    public void ajouteCondition(GestionCondition gestionCondition) {
        getConditions().ajouteCondition(gestionCondition);
    }


    public Effets getEffetJaugeGauche() {
        return effetJaugeGauche;
    }


    public Effets getEffetJaugeDroite() {
        return effetJaugeDroite;
    }

    public Conditions getConditions() {
        return conditions;
    }

}
