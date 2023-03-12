package main;

import com.fges.tp_solid.reigns.Personnage.Personnage;

import java.util.ArrayList;
import java.util.List;

public class Questions {
    private final List<GestionQuestion> questions;

    public Questions(List<GestionQuestion> questions) {
        this.questions = questions;
    }

    public GestionQuestion getQuestion(Personnage personnage) {
        List<GestionQuestion> questionFiltred = new ArrayList<>();
        for (GestionQuestion question : getQuestions()) {
            if(question.getConditions().hasConditionsRespect(personnage)){
               questionFiltred.add(question);
            }
        }
        int numQuestion = (int) (Math.random() * questionFiltred.size());
        return questionFiltred.get(numQuestion);
    }

    public List<GestionQuestion> getQuestions() {
        return questions;
    }

}
