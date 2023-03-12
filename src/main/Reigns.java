package main;

import java.util.ArrayList;
import java.util.Scanner;

package reigns.Jeu;

import reigns.Condition.Conditions;
import reigns.Condition.GestionCondition;
import reigns.Condition.TypeCondition;
import reigns.Jauges.Jauge;
import reigns.Jauges.TypeJauge;
import reigns.Personnage.Genre;
import reigns.Personnage.Personnage;
import reigns.Question.GestionQuestion;
import reigns.Question.Questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static reigns.Personnage.Genre.REINE;
import static reigns.Personnage.Genre.ROI;

public class Jeu {

    protected static Personnage personnage;
    protected static Questions questions;
    protected static boolean hasGotActive;

    public void InitJeu() {
        initBanqueQuestions();
        System.out.println("Création du personnage...");
        initPersonnage();
        System.out.println(personnage.getGenre().longRegne()
                + " " + personnage.getNom());

        personnage.AfficheJauges();
        hasGotActive = false;
        //Gestion Jeu
        int nbTours = 0;
        gestionJeu(nbTours);


        // fin du jeu
        System.out.println(
                personnage.getNom()
                        + " a perdu ! Son règne a duré "
                        + nbTours
                        + " tours");

    }
    protected void gestionJeu(int nbTours){
        while (!hasFinDuJeu()) {
            nbTours++;
            GestionQuestion question = questions.getQuestion(personnage);
            reponseQuestion(question);
            personnage.AfficheJauges();
        }
    }

    protected static void reponseQuestion(GestionQuestion question) {
        question.afficheQuestion(hasGotActive);
        // récupère la réponse
        Scanner scanner = new Scanner(System.in);
        String reponse = "";
        while (!reponse.equals("G") && !reponse.equals("D")) {
            System.out.println("Entrez la réponse (G ou D)");
            System.out.flush();
            reponse = scanner.nextLine();
        }
        // applique les malus
        if (reponse.equals("G")) {
            question.getEffetJaugeGauche().appliquerEffets(personnage, hasGotActive);
        } else {
            question.getEffetJaugeDroite().appliquerEffets(personnage, hasGotActive);
        }
    }

    //INIT JEU


    private static void initPersonnage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom du personnage: ");
        System.out.flush();
        String nom = scanner.nextLine();
        System.out.println(
                "Faut-il vous appeler Roi ou Reine ? (1 pour Roi, 2 pour Reine)");
        int genre = scanner.nextInt();
        Genre genrePersonnage;
        if (genre == 1) {
            genrePersonnage = ROI;
        } else {
            genrePersonnage = REINE;
        }
        HashMap<String, Jauge> jauges = initJauge();
        Jeu.personnage = new Personnage(nom, genrePersonnage, jauges);
    }

    private static HashMap<String, Jauge> initJauge() {
        HashMap<String, Jauge> jauges = new HashMap<>();
        jauges.put("Clerge", new Jauge(TypeJauge.CLERGE));
        jauges.put("Peuple", new Jauge(TypeJauge.PEUPLE));
        jauges.put("Armee", new Jauge(TypeJauge.ARMEE));
        jauges.put("Finance", new Jauge(TypeJauge.FINANCE));
        return jauges;
    }

    private static void initBanqueQuestions() {
        ArrayList<GestionQuestion> questionsBanque = new ArrayList<>();
        GestionQuestion question1 = new GestionQuestion(
                "Main du roi",
                "Le peuple souhaite libérer les prisonniers",
                "Oui",
                "Non",
                new Effets(),
                new Effets(),
                new Conditions());
        question1.ajouteEffetGauche(new GestionEffet(TypeJauge.ARMEE, -5));
        question1.ajouteEffetGauche(new GestionEffet(TypeJauge.PEUPLE, +5));
        question1.ajouteEffetDroite(new GestionEffet(TypeJauge.PEUPLE, -7));
        questionsBanque.add(question1);
        GestionQuestion question2 = new GestionQuestion(
                "Paysan",
                "Il n'y a plus rien à manger",
                "Importer de la nourriture",
                "Ne rien faire",
                new Effets(),
                new Effets(),
                new Conditions());
        question2.ajouteEffetGauche(new GestionEffet(TypeJauge.FINANCE, -5));
        question2.ajouteEffetGauche(new GestionEffet(TypeJauge.PEUPLE, +5));
        question2.ajouteEffetDroite(new GestionEffet(TypeJauge.PEUPLE, -5));
        questionsBanque.add(question2);
        GestionQuestion question3 = new GestionQuestion(
                "Prêtre",
                "Les dieux sont en colère",
                "Faire un sacrifice",
                "Ne rien faire",
                new Effets(),
                new Effets(),
                new Conditions());
        question3.ajouteEffetGauche(new GestionEffet(TypeJauge.CLERGE, +5));
        question3.ajouteEffetGauche(new GestionEffet(TypeJauge.PEUPLE, -3));
        question3.ajouteEffetDroite(new GestionEffet(TypeJauge.CLERGE, -5));
        questionsBanque.add(question3);
        GestionQuestion question4 = new GestionQuestion(
                "Main du roi",
                "Le roi Baratheon rassemble son armée",
                "Le soutenir",
                "Rester neutre",
                new Effets(),
                new Effets(),
                new Conditions());
        question4.ajouteEffetGauche(new GestionEffet(TypeJauge.ARMEE, +3));
        question4.ajouteEffetGauche(new GestionEffet(TypeJauge.FINANCE, -3));
        question4.ajouteEffetGauche(new GestionEffet(TypeJauge.CLERGE, -3));
        question4.ajouteEffetDroite(new GestionEffet(TypeJauge.PEUPLE, +3));
        questionsBanque.add(question4);
        GestionQuestion question5 = new GestionQuestion(
                "Paysan",
                "Abondance de récoltes cette année",
                "Taxer énormément",
                "Taxer un tout petit peu",
                new Effets(),
                new Effets(),
                new Conditions());
        question5.ajouteEffetGauche(new GestionEffet(TypeJauge.FINANCE, +10));
        question5.ajouteEffetGauche(new GestionEffet(TypeJauge.PEUPLE, -5));
        question5.ajouteEffetDroite(new GestionEffet(TypeJauge.FINANCE, +1));
        question5.ajouteEffetDroite(new GestionEffet(TypeJauge.PEUPLE, -3));
        questionsBanque.add(question5);
        GestionQuestion question6 = new GestionQuestion(
                "Main du roi",
                "Les caisses sont vides...",
                "Augmenter les taxes",
                "Emprunter",
                new Effets(),
                new Effets(),
                new Conditions());
        question6.ajouteEffetGauche(new GestionEffet(TypeJauge.FINANCE, +10));
        question6.ajouteEffetGauche(new GestionEffet(TypeJauge.PEUPLE, -5));
        question6.ajouteEffetDroite(new GestionEffet(TypeJauge.FINANCE, +7));
        question6.ajouteEffetDroite(new GestionEffet(TypeJauge.PEUPLE, -3));
        question6.ajouteCondition(new GestionCondition(TypeJauge.PEUPLE, 10, TypeCondition.INFERIEUR));
        questionsBanque.add(question6);
        GestionQuestion question7 = new GestionQuestion(
                "Prêtre",
                "J'aimerai qu'on nous considère en tant que tel",
                "Construire un monastère",
                "Ecouter sans rien faire",
                new Effets(),
                new Effets(),
                new Conditions());
        question7.ajouteEffetGauche(new GestionEffet(TypeJauge.CLERGE, +5));
        question7.ajouteEffetGauche(new GestionEffet(TypeJauge.FINANCE, -5));
        question7.ajouteEffetDroite(new GestionEffet(TypeJauge.CLERGE, -5));
        question7.ajouteCondition(new GestionCondition(TypeJauge.CLERGE, 10, TypeCondition.INFERIEUR));
        question7.ajouteCondition(new GestionCondition(TypeJauge.FINANCE, 30, TypeCondition.SUPPERIEUR));
        questionsBanque.add(question7);
        questions = new Questions(questionsBanque);
    }

    //FIN JEU

    protected static boolean hasFinDuJeu() {
        for (HashMap.Entry<String, Jauge> jauge : Jeu.personnage.getJauges().entrySet()) {
            if (jauge.getValue().hasJaugePleine())
                return true;
        }
        return false;
    }
}
